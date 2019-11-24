package id.nizwar.katalogmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.nizwar.katalogmovie.models.KatalogAttrb;

public class KatalogDetailActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog_detail);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        KatalogAttrb data = getIntent().getParcelableExtra("katalog");
        if (data != null) {
            ((TextView) findViewById(R.id.tvTitle)).setText(data.getTitle());
            ((TextView) findViewById(R.id.tvOverview)).setText(data.getOverview());
            ((TextView) findViewById(R.id.tvRilis)).setText(getString(R.string.str_rilis_pada) + data.getRilis());
            ((TextView) findViewById(R.id.tvScore)).setText(getString(R.string.str_score) + data.getScore());
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
