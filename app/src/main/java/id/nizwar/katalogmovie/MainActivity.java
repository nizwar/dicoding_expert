package id.nizwar.katalogmovie;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Locale;

import id.nizwar.katalogmovie.fragments.Movie;
import id.nizwar.katalogmovie.fragments.TvShow;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private FragmentAdapter fragmentAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.appToolbar));


        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        initEverything();
    }

    void initEverything() {
        initLanguage();
        reloadAdapter();
    }

    void initLanguage() {
        SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean customLang = spref.getBoolean("custom_language", false);
        if (customLang) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                String language = spref.getString("language", "in"); //Default indonesia
                Configuration config = getResources().getConfiguration();
                if (language.equals("in")) {
                    config.setLocale(new Locale("in", "id"));
                } else if (language.equals("en")) {
                    config.setLocale(Locale.ENGLISH);
                }
                getResources().updateConfiguration(config, getResources().getDisplayMetrics());
            }
        }

    }

    void initFragAdapter() {
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), 0);
        fragmentAdapter.addFragments(getString(R.string.str_movie), new Movie());
        fragmentAdapter.addFragments(getString(R.string.str_tv_show), new TvShow());
    }

    void reloadAdapter() {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.popup_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnAboutMe:
                new AlertDialog.Builder(this)
                        .setTitle("Dicoding Submission 3")
                        .setMessage("Ditulis oleh Moch. Nizwar Syafuan\nNote : Pesan ini emang sengaja ga di translate :p")
                        .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
                break;
            case R.id.mnSettings:
                startActivityForResult(new Intent(this, SettingsActivity.class), 1);
                break;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        initEverything();
        super.onActivityResult(requestCode, resultCode, data);
    }
}
