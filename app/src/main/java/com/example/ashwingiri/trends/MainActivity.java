//NEWS API KEY: 3b4c65c3780fc1ef44ec5500b186d833
//https://github.com/iamfreee/yamda

// You can generate holders from pojo generation.You have to just right click on new option and click on pojo generator
//<a href="https://drive.google.com/uc?export=view&id=XXX"><img src="https://drive.google.com/uc?export=view&id=XXX" style="width: 500px; max-width: 100%; height: auto" title="Click for the larger version." /></a>
//Right click on image file which is in drive and click on sharable link and get the id from that sharable link
//     .load("https://drive.google.com/uc?id=1W3vhQx3gVKrju3veRTwV79ipqyNFaWQH")
// https://drive.google.com/uc?export=download&id=1W3vhQx3gVKrju3veRTwV79ipqyNFaWQH
// https://groups.google.com/forum/#!forum/bpitplacementbatch2019
package com.example.ashwingiri.trends;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.ashwingiri.trends.Jokes.JokesMainActivity;
import com.example.ashwingiri.trends.Movies.Activity.MoviesMainActivity;
import com.example.ashwingiri.trends.News.Activity.NewsMainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new NewsMainActivity(),"News");
        adapter.addFragment(new MoviesMainActivity(), "Movies");
        adapter.addFragment(new JokesMainActivity(),"Jokes");

        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
////    private EditText etSearch;
////    private ListView lvMainActivity;
////    private ArrayList<Item> mItemArrayList = new ArrayList<>();
////    private MyAdapter adapter;
////
////
////    DrawerLayout mDrawerLayout;
////  //  ImageButton mButton;
////    @Override
////    public void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////
////
////        etSearch = findViewById(R.id.etSearch);
////        lvMainActivity = findViewById(R.id.lvMainActivity);
////
////        mDrawerLayout = findViewById(R.id.drawer);
////      //  mButton = findViewById(R.id.ibMenu);
//////        mButton.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View view) {
//////                mDrawerLayout.openDrawer(GravityCompat.START);
//////            }
//////        });
//////
//////        NavigationView navigationView = findViewById(R.id.navigation);
//////        navigationView.setNavigationItemSelectedListener(this);
////
////
////        mItemArrayList.add(
////                new Item(
////                        "NEWS",
////                        R.drawable.news,
////                        "#2D68C4"
////                )
////        );
////        mItemArrayList.add(
////                new Item(
////                        "MUSIC",
////                        R.drawable.music,
////                        "#9F00C5"
////                )
////        );
////        mItemArrayList.add(
////                new Item("GAMES",
////                        R.drawable.games,
////                        "#9F8170"
////                )
////        );
////        mItemArrayList.add(
////                new Item("APPS",
////                        R.drawable.apps,
////                        "#A4C639"
////                )
////        );
////        mItemArrayList.add(
////                new Item("JOKES",
////                        R.drawable.jokes,
////                        "#FF7F00"
////                )
////        );
////        mItemArrayList.add(
////                new Item("MOVIES",
////                        R.drawable.movies,
////                        "#e62117"
////                )
////        );
////        adapter = new MyAdapter();
////        lvMainActivity.setAdapter(adapter);
////
////
////        etSearch.addTextChangedListener(new TextWatcher() {
////
////            @Override
////            public void onTextChanged(CharSequence s, int start, int before, int count) {
////                adapter.getFilter().filter(s.toString());
////            }
////
////            @Override
////            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
////            }
////
////            @Override
////            public void afterTextChanged(Editable s) {
////            }
////        });
////    }
////
////    @Override
////    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////
////        switch(item.getItemId()) {
////
////            case R.id.Profile:
////                startActivity(new Intent(this, YourProfile.class));
////                break;
////
////            case R.id.home:
////                mDrawerLayout.closeDrawer(GravityCompat.START);
////                break;
////
////            case R.id.app:
////                startActivity(new Intent(this, AboutApp.class));
////                break;
////
////            case R.id.me:
////                startActivity(new Intent(this, AboutDeveloper.class));
////                break;
////
////            case R.id.share:
////                startActivity(new Intent(this, Share.class));
////                break;
////
////            case R.id.feedback:
////                startActivity(new Intent(this, Feedback.class));
////                break;
////
////            case R.id.help:
////                startActivity(new Intent(this, Help.class));
////                break;
////        };
////        mDrawerLayout.closeDrawer(GravityCompat.START);
////        return true;
////    }
////
////    @Override
////    public void onBackPressed() {
////        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
////            mDrawerLayout.closeDrawer(GravityCompat.START);
////        } else {
////            super.onBackPressed();
////        }
////    }
//
//    public class MyAdapter extends BaseAdapter implements Filterable {
//
//        private ArrayList<Item> mOriginalValues=mItemArrayList;
//        private ArrayList<Item> mDisplayedValues=mItemArrayList;
//        @Override
//        public int getCount() {
//            return mDisplayedValues.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return mDisplayedValues.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//
//            if (convertView==null) {
//                convertView=getLayoutInflater().inflate(R.layout.main_activity_row,parent,false);
//            }
//
//            ((TextView) convertView.findViewById(R.id.tvMainActivityItem)).setText(mDisplayedValues.get(position).item);
//            ((ImageView) convertView.findViewById(R.id.ivMainActivityItem)).setImageResource(mDisplayedValues.get(position).resource_image);
//            (convertView.findViewById(R.id.tvMainActivityItem)).setBackgroundColor(Color.parseColor(mDisplayedValues.get(position).background_colour));
//
//            (convertView.findViewById(R.id.llContainer)).setOnClickListener(
//                    new View.OnClickListener() {
//                        public void onClick(View v) {
//                            switch (mDisplayedValues.get(position).item){
//                                case "NEWS":
//                                    startActivity(
//                                            new Intent(
//                                                    MainActivity.this,
//                                                    NewsFragmentMainActivity.class
//                                            )
//                                    );
//                                    break;
//                                case "MUSIC":
//                                    startActivity(
//                                            new Intent(
//                                                    MainActivity.this,
//                                                    WebViewActivity.class
//                                            ).putExtra(
//                                                    "url",
//                                                    "https://gaana.com/playlist/gaana-dj-latest-bollywood-hits"
//                                            )
//                                    );
//                                    break;
//                                case "GAMES":
//                                    startActivity(
//                                            new Intent(
//                                                    MainActivity.this,
//                                                    WebViewActivity.class
//                                            ).putExtra(
//                                                    "url",
//                                                    "https://beebom.com/best-offline-games-android-device/"
//                                            )
//                                    );
//
//                                    break;
//                                case "APPS":
//                                    startActivity(
//                                            new Intent(
//                                                    getApplicationContext(),
//                                                    WebViewActivity.class
//                                            ).putExtra(
//                                                    "url",
//                                                    "https://www.pastemagazine.com/articles/2017/04/the-50-essential-android-apps-2017.html"
//                                            )
//                                    );
//                                    break;
//                                case "JOKES":
//                                    startActivity(
//                                            new Intent(
//                                                    MainActivity.this,
//                                                    JokesMainActivity.class
//                                            )
//                                    );
//                                    break;
//                                case "MOVIES":
//                                    startActivity(
//                                            new Intent(
//                                                    MainActivity.this,
//                                                    MovieMainActivity.class
//                                            )
//                                    );
//                                    break;
//                            }
//                        }
//                    }
//            );
//
//            return convertView;
//        }
//
//        @Override
//        public Filter getFilter() {
//            Filter filter = new Filter() {
//
//                @SuppressWarnings("unchecked")
//                @Override
//                protected void publishResults(CharSequence constraint,FilterResults results) {
//
//                    mDisplayedValues = (ArrayList<Item>) results.values;
//                    notifyDataSetChanged();
//                }
//
//                @Override
//                protected FilterResults performFiltering(CharSequence constraint) {
//                    FilterResults results = new FilterResults();
//                    ArrayList<Item> FilteredArrList = new ArrayList<>();
//
//                    if (mOriginalValues == null) {
//                        mOriginalValues = new ArrayList<>(mDisplayedValues);
//                    }
//
//                    if (constraint == null || constraint.length() == 0) {
//
//                        results.count = mOriginalValues.size();
//                        results.values = mOriginalValues;
//                    } else {
//                        constraint = constraint.toString().toLowerCase();
//                        for (int i = 0; i < mOriginalValues.size(); i++) {
//                            String data = mOriginalValues.get(i).item;
//                            if (data.toLowerCase().startsWith(constraint.toString())) {
//                                FilteredArrList.add(
//                                        new Item(
//                                                mOriginalValues.get(i).item,
//                                                mOriginalValues.get(i).resource_image,
//                                                mOriginalValues.get(i).background_colour
//                                        )
//                                );
//                            }
//                        }
//                        results.count = FilteredArrList.size();
//                        results.values = FilteredArrList;
//                    }
//                    return results;
//                }
//            };
//            return filter;
//        }
//    }
//}
//
//
//////3b4c65c3780fc1ef44ec5500b186d833
//////https://github.com/iamfreee/yamda
////package com.example.ashwingiri.trends;
////
////import android.content.Intent;
////import android.os.Bundle;
////import android.support.v7.app.AppCompatActivity;
////import android.view.View;
////import android.widget.TextView;
////import com.example.ashwingiri.trends.Movies.Activity.MovieMainActivity;
////import com.example.ashwingiri.trends.News.Activity.NewsFragmentMainActivity;
////import com.example.ashwingiri.trends.News.Activity.WebViewActivity;
////
////public class JokesMainActivity extends AppCompatActivity implements View.OnClickListener {
////
////    TextView tvApps,tvMusic,tvJokes,tvMovies,tvNews,tvGames;
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////        tvApps=findViewById(R.id.tvApps);
////        tvMusic=findViewById(R.id.tvMusic);
////        tvJokes=findViewById(R.id.tvJokes);
////        tvMovies=findViewById(R.id.tvMovies);
////        tvNews=findViewById(R.id.tvNews);
////        tvGames=findViewById(R.id.tvGames);
////        tvApps.setOnClickListener(this);
////        tvMusic.setOnClickListener(this);
////        tvJokes.setOnClickListener(this);
////        tvMovies.setOnClickListener(this);
////        tvNews.setOnClickListener(this);
////        tvGames.setOnClickListener(this);
////    }
////
////    @Override
////    public void onClick(View v) {
////        switch(v.getId()){
////            case R.id.tvApps:
////                Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
////                i.putExtra("url", "https://www.pastemagazine.com/articles/2017/04/the-50-essential-android-apps-2017.html");
////                startActivity(i);
////
////                break;
////            case R.id.tvMusic:
////                Intent i1 = new Intent(JokesMainActivity.this, WebViewActivity.class);
////                i1.putExtra("url", "https://gaana.com/playlist/gaana-dj-latest-bollywood-hits");
////                startActivity(i1);
////
////                break;
////            case R.id.tvJokes:
////                Intent i2 = new Intent(JokesMainActivity.this, WebViewActivity.class);
////                i2.putExtra("url", "https://www.jokescoff.com/sort/jokes/funny-jokes");
////                startActivity(i2);
////                break;
////            case R.id.tvMovies:
////                Intent i3 = new Intent(JokesMainActivity.this, MovieMainActivity.class);
////                startActivity(i3);
////                break;
////            case R.id.tvNews:
////                startActivity(new Intent(this, NewsFragmentMainActivity.class));
////                break;
////            case R.id.tvGames:
////                Intent i4 = new Intent(JokesMainActivity.this, WebViewActivity.class);
////                i4.putExtra("url","https://beebom.com/best-offline-games-android-device/");
////                startActivity(i4);
////
////                break;
////
////        }
////    }
////}
