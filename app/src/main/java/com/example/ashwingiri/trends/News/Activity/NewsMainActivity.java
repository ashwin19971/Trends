package com.example.ashwingiri.trends.News.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ashwingiri.trends.Holder.News_Holder.News;
import com.example.ashwingiri.trends.News.Activity.WebViewActivity;
import com.example.ashwingiri.trends.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NewsMainActivity extends Fragment {

    ArrayList<News> NewsArrayList=new ArrayList<>();
    ListView lvNews;
    String API_KEY = "8190df9eb51445228e397e4185311a66"; // ### YOUE NEWS API HERE ###

    String NEWS_SOURCE ="the-hindu";
    static final String KEY_AUTHOR = "author";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_URL = "url";
    static final String KEY_URLTOIMAGE = "urlToImage";
    static final String KEY_PUBLISHEDAT = "publishedAt";
    NewsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.activity_main_newslist, container, false);
        lvNews= rootView.findViewById(R.id.lvNews);
        adapter=new NewsAdapter();
        lvNews.setAdapter(adapter);
        return rootView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new DownloadNewsTask().execute();

    }



    private class DownloadNewsTask extends AsyncTask<Void,Void,ArrayList<News>> {

        @Override
        protected ArrayList<News> doInBackground(Void... voids) {
            ArrayList<News> news=new ArrayList<>();
            try {
                URL url=new URL("https://newsapi.org/v1/articles?source="+NEWS_SOURCE+"&sortBy=top&apiKey="+API_KEY);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();

                BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb=new StringBuilder();
                String buf=br.readLine();
                while (buf!=null){
                    sb.append(buf);
                    buf=br.readLine();
                }
                String data=sb.toString();
                JSONObject jsonResponse = new JSONObject(data);
                JSONArray postJsonArray = jsonResponse.optJSONArray("articles");

                for (int i = 0; i < postJsonArray.length(); i++) {
                    JSONObject jsonObject = postJsonArray.getJSONObject(i);
                    News temp = new News();

                    temp.setAuthor( jsonObject.optString(KEY_AUTHOR));
                    temp.setTitle( jsonObject.optString(KEY_TITLE));
                    temp.setDescription(jsonObject.optString(KEY_DESCRIPTION));
                    temp.setUrl(jsonObject.optString(KEY_URL));
                    temp.setUrlToImage(jsonObject.optString(KEY_URLTOIMAGE));
                    temp.setPublishedAt(jsonObject.optString(KEY_PUBLISHEDAT));
                    news.add(temp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return news;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {
            NewsArrayList.clear();
            NewsArrayList.addAll(news);
            adapter.notifyDataSetChanged();
            lvNews.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            startActivity(
                                    new Intent(
                                            getContext(), WebViewActivity.class).putExtra("url", NewsArrayList.get(i).getUrl()
                                    )
                            );
                        }
                    }
            );
        }
    }
    private class NewsAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return NewsArrayList.size();
        }

        @Override
        public News getItem(int i) {
            return NewsArrayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            if (convertView==null) {
                convertView=getLayoutInflater().inflate(R.layout.list_row_news,viewGroup,false);
            }
            News news = getItem(i);

            ((TextView) convertView.findViewById(R.id.tvNewsTitle)).setText(news.getTitle());
            Log.i("TAG", news.getTitle());
            ((TextView) convertView.findViewById(R.id.tvNewsDescription)).setText(news.getDescription());
            if(news.getUrlToImage().length() < 5)
            {
                (convertView.findViewById(R.id.lvImage)).setVisibility(View.GONE);
            }else{
                Picasso.with(getContext())
                        .load(news.getUrlToImage())
                        .resize(400,400)
                        .into((ImageView) convertView.findViewById(R.id.lvImage));
            }
            return convertView;

        }
    }
}