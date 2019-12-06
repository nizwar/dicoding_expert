package id.nizwar.katalogmovie.fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Locale;

import id.nizwar.katalogmovie.broadcast.DailyReminder;
import id.nizwar.katalogmovie.broadcast.ReleaseToday;
import id.nizwar.katalogmovie.R;

@SuppressLint({"ValidFragment"})
public class SettingPref extends PreferenceFragment {
    SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.spref);
        preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals("daily_reminder")) {
                    DailyReminder.setDailyReminder(getActivity(), sharedPreferences.getBoolean(key, false));
                }
                if (key.equals("release_reminder")) {
                    ReleaseToday.setReleaseToday(getActivity(), sharedPreferences.getBoolean(key, false));
                }
                initLanguage();
                getPreferenceScreen().removeAll();
                addPreferencesFromResource(R.xml.spref);
            }
        };
    }

    @Override
    public void onResume() {
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);
        super.onResume();
    }

    @Override
    public void onPause() {
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
        super.onPause();
    }

    void initLanguage() {
        SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean customLang = spref.getBoolean("custom_language", false);
        if (customLang) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                String language = spref.getString("language", "in"); //Default indonesia
                Log.e("Language", language);
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
}