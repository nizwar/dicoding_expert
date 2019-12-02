package id.nizwar.katalogmovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.nizwar.katalogmovie.models.Katalog;

public class KatalogDetailActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog_detail);

        setSupportActionBar((Toolbar) findViewById(R.id.appToolbar));
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Katalog data = getIntent().getParcelableExtra("katalog");
        if (data != null) {
            ((TextView) findViewById(R.id.tvTitle)).setText(data.getTitle());
            ((TextView) findViewById(R.id.tvOverview)).setText(data.getOverview());
            ((TextView) findViewById(R.id.tvRilis)).setText("" + data.getRilis());
            ((TextView) findViewById(R.id.tvScore)).setText("" + data.getScore());
            ((ImageView) findViewById(R.id.imgCover)).setImageResource(data.getPosterID());
            ((ImageView) findViewById(R.id.gbrPoster)).setImageResource(data.getPosterID());
        } else {
            Toast.makeText(this, "Sepertinya ada yang tidak beres", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
