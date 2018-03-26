package com.example.ashwingiri.trends.Movies.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ashwingiri.trends.Holder.Movie_Holder.Movies;
import com.example.ashwingiri.trends.R;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MoviesMainActivity extends Fragment {

    ArrayList<Movies> movies =new ArrayList<>();
    ListView lvMovie;
    String data;
    MovieAdapter adapter;
    URL url;

    public static final String POSTER_PATH="poster_path";
    public static final String OVERVIEW="overview";
    public static final String RELEASE_DATE="release_date";
    public static final String ID="id";
    public static final String TITLE="title";
    public static final String BACKDROP_PATH="backdrop_path";
    public static final String VOTE_AVERAGE= "vote_average";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this activity_movie_fragment
        View rootView= inflater.inflate(R.layout.activity_movie_fragment, container, false);
        lvMovie= rootView.findViewById(R.id.lvInTheaters);
        adapter=new MovieAdapter();
        lvMovie.setAdapter(adapter);
        return rootView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new DownloadMovieTask().execute();

    }
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        ArrayList<String> temp=new ArrayList<>();
//        temp.add(data);
//        temp.add(String.valueOf(i));
//        startActivity(new Intent(getContext(),Descriptions.class).putExtra("description",temp));
//
//    }

    public class DownloadMovieTask extends AsyncTask<Void,Void,ArrayList<Movies>> {

        ArrayList<Movies> moviesAsync =new ArrayList<>();

        @Override
        protected ArrayList<Movies> doInBackground(Void... voids) {
            try {
                url=new URL("https://api.themoviedb.org/3/movie/now_playing?api_key=3b4c65c3780fc1ef44ec5500b186d833&language=en-US&page=1");
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb=new StringBuilder();
                String buf=br.readLine();
                while (buf!=null){
                    sb.append(buf);
                    buf=br.readLine();
                }
                data=sb.toString();
                JSONObject jsonResponse=new JSONObject(data);
                JSONArray movieJsonArray=jsonResponse.optJSONArray("results");
                for (int i = 0; i < movieJsonArray.length(); i++) {
                    JSONObject movieJsonObject = movieJsonArray.getJSONObject(i);
                    Movies movies = new Movies();
                    movies.setBackdrop_path(movieJsonObject.getString(BACKDROP_PATH));
                    movies.setId(movieJsonObject.getString(ID));
                    movies.setOverview(movieJsonObject.getString(OVERVIEW));
                    movies.setPoster_path(movieJsonObject.getString(POSTER_PATH));
                    movies.setTitle(movieJsonObject.getString(TITLE));
                    movies.setRelease_date(movieJsonObject.getString(RELEASE_DATE));
                    movies.setVote_average(movieJsonObject.getString(VOTE_AVERAGE));
                    moviesAsync.add(movies);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return moviesAsync;
        }

        @Override
        protected void onPostExecute(ArrayList<Movies> movies) {
            MoviesMainActivity.this.movies.clear();
            MoviesMainActivity.this.movies.addAll(movies);
            adapter.notifyDataSetChanged();
        }

    }


    public class MovieAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return movies.size();
        }

        @Override
        public Movies getItem(int i) {
            return movies.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            if (convertView==null) {
                convertView=getLayoutInflater().inflate(R.layout.movie_row,viewGroup,false);
            }
            final Movies temp_movies = getItem(i);
            ((TextView) convertView.findViewById(R.id.tvMovieTitle)).setText(temp_movies.getTitle());
            ((TextView) convertView.findViewById(R.id.tvMovieRating)).setText(String.format("Rating: %s", temp_movies.getVote_average()));
            ((TextView) convertView.findViewById(R.id.tvMovieReleased)).setText(String.format("Released: %s", temp_movies.getRelease_date()));

            if(temp_movies.getPoster_path().length() < 5)
            {
                (convertView.findViewById(R.id.ivImage)).setVisibility(View.GONE);
            }else{
                Picasso.with(getContext())
                        .load("http://image.tmdb.org/t/p/w400/"+ temp_movies.getPoster_path())
                        .resize(400, 400)
                        .into((ImageView) convertView.findViewById(R.id.ivImage));
            }

            convertView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getContext(),Descriptions.class).putExtra("description", temp_movies));
                        }
                    }
            );
            return convertView;
        }
    }

}