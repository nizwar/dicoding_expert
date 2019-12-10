package id.nizwar.katalogmovie;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

import id.nizwar.katalogmovie.fragments.Beranda;
import id.nizwar.katalogmovie.fragments.Favorite;
import id.nizwar.katalogmovie.helpers.DataObserver;
import id.nizwar.katalogmovie.models.CUFavorite;

public class MainActivity extends AppCompatActivity {
    private Beranda beranda;
    private Favorite favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.appToolbar));

        beranda = new Beranda();
        favorite = new Favorite();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer, beranda).commit();

        BottomNavigationView btmNav = findViewById(R.id.btmNav);
        btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mnBeranda:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer, beranda).commit();
                        break;
                    case R.id.mnFavorite:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer, favorite).commit();
                        break;
                }
                return true;
            }
        });

        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        DataObserver myObserver = new DataObserver(handler);
        this.getContentResolver().registerContentObserver(CUFavorite.FavoriteColumns.CONTENT_URI, true, myObserver);
        initEverything();
    }

    void initEverything() {
        beranda = new Beranda();
        favorite = new Favorite();
        initLanguage();
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
                        .setTitle("Dicoding Submission Akhir")
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
                startActivity(new Intent(this, SettingsActivity.class));
                finish();
                break;
        }
        return false;
    }

}
