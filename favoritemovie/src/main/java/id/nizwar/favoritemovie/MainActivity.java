package id.nizwar.favoritemovie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import id.nizwar.favoritemovie.fragments.favorite.MovieFragment;
import id.nizwar.favoritemovie.fragments.favorite.TvShowFragment;
import id.nizwar.favoritemovie.helpers.DataObserver;
import id.nizwar.favoritemovie.models.CUFavorite;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private FragmentAdapter fragmentAdapter;
    private TabLayout tabLayout;
    private MovieFragment movie;
    private TvShowFragment tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        movie = new MovieFragment();
        tvShow = new TvShowFragment();



        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        DataObserver myObserver = new DataObserver(handler);
        this.getContentResolver().registerContentObserver(CUFavorite.FavoriteColumns.CONTENT_URI, true, myObserver);
        initFragAdapter();
    }


    private void initFragAdapter() {
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), 0);
        fragmentAdapter.addFragments(getString(R.string.str_movie), movie);
        fragmentAdapter.addFragments(getString(R.string.str_tv_show), tvShow);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public class FragmentAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> fragTitle = new ArrayList<>();

        FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        void addFragments(String title, Fragment fragment) {
            fragments.add(fragment);
            fragTitle.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragTitle.get(position);
        }
    }
}
