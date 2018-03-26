package com.example.ashwingiri.trends.Jokes;
//""
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ashwingiri.trends.Holder.Jokes_Holder.Jokes;
import com.example.ashwingiri.trends.R;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class JokesMainActivity extends Fragment {

    ArrayList<Jokes> JokesArrayList;
    JokesAdapter adapter;
    int height;
    ListView lvJokes;
    public static final String URLs="url";
    public static final String ID="id";

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_jokes);
//        lvJokes=findViewById(R.id.lvJokes);
//        JokesArrayList=new ArrayList<>();
//        JokesArrayList.clear();
//        JokesArrayList.addAll(loadJSONFromAsset());
//        adapter=new JokesAdapter();
//        lvJokes.setAdapter(adapter);
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this activity_movie_fragment
        View rootView= inflater.inflate(R.layout.activity_main_jokes, container, false);
        lvJokes=rootView.findViewById(R.id.lvJokes);
        JokesArrayList=new ArrayList<>();
        JokesArrayList.clear();
        JokesArrayList.addAll(loadJSONFromAsset());
        adapter=new JokesAdapter();
        lvJokes.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public class JokesAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return JokesArrayList.size();
        }

        @Override
        public Jokes getItem(int i) {
            return JokesArrayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            if (convertView==null) {
                convertView=getLayoutInflater().inflate(R.layout.jokes_row,parent,false);
            }
            Jokes jokes = getItem(i);
            Picasso.with(getActivity().getApplicationContext())
                    .load("https://drive.google.com/uc?id="+jokes.getUrl())
                    .resize(680,500)
                    .into((ImageView) convertView.findViewById(R.id.ivJokes));
            return convertView;
        }
    }


    public ArrayList<Jokes> loadJSONFromAsset() {
        ArrayList<Jokes> tempList = new ArrayList<>();
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("Jokes_database.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        try {

            JSONArray m_jArry = new JSONArray(json);

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Jokes jokes = new Jokes();
                jokes.setId(jo_inside.getInt(ID));
                jokes.setUrl(jo_inside.getString(URLs));



                //Add your values in your `ArrayList` as below:
                tempList.add(jokes);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tempList;
    }
}


