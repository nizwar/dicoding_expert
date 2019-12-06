package id.nizwar.katalogmovie;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import id.nizwar.katalogmovie.fragments.SettingPref;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setSupportActionBar((Toolbar) findViewById(R.id.appToolbar));
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction().replace(R.id.fragContainer, new SettingPref()).commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
<<<<<<< HEAD
        onBackPressed();
=======
        startActivity(new Intent(this, MainActivity.class));
        finish();
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
        super.onBackPressed();
    }
}
