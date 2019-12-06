package id.nizwar.katalogmovie.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import id.nizwar.katalogmovie.R;
import id.nizwar.katalogmovie.fragments.favorite.Movie;
import id.nizwar.katalogmovie.fragments.favorite.TvShow;

public class Favorite extends Fragment {

    private ViewPager viewPager;
    private FragmentAdapter fragmentAdapter;
    private TabLayout tabLayout;
    private Movie movie;
    private TvShow tvShow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_container, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        movie = new Movie();
        tvShow = new TvShow();

        reloadAdapter();
        return view;
    }

    private void initFragAdapter() {
        if (getActivity() != null) {
            fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), 0);
            fragmentAdapter.addFragments(getString(R.string.str_movie), movie);
            fragmentAdapter.addFragments(getString(R.string.str_tv_show), tvShow);
        }
    }

    private void reloadAdapter() {
        initFragAdapter();
        viewPager.setAdapter(null);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    class FragmentAdapter extends FragmentPagerAdapter {
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
