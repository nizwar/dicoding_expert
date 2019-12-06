package id.nizwar.katalogmovie;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import id.nizwar.katalogmovie.models.Katalog;
import id.nizwar.katalogmovie.models.SQFavorite;

public class KatalogDetailActivity extends AppCompatActivity {
    private SQFavorite favorite;
    private Katalog data;
    private int jenis;

    @SuppressLint({"SetTextI18n", "CheckResult"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog_detail);

        setSupportActionBar((Toolbar) findViewById(R.id.appToolbar));
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        favorite = new SQFavorite(this);

        data = getIntent().getParcelableExtra("katalog");
        jenis = getIntent().getIntExtra("jenis", 0);
        if (data != null) {
            ((TextView) findViewById(R.id.tvTitle)).setText(data.getTitle());
            ((TextView) findViewById(R.id.tvOverview)).setText(data.getOverview());
            ((TextView) findViewById(R.id.tvRilis)).setText("" + data.getReleaseDate());
            ((TextView) findViewById(R.id.tvScore)).setText("" + data.getVoteAverage());

            Picasso.get().load("https://image.tmdb.org/t/p/w780" + data.getPosterPath()).into((ImageView) findViewById(R.id.imgCover));
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + data.getPosterPath()).into((ImageView) findViewById(R.id.gbrPoster));
        } else {
            Toast.makeText(this, getString(R.string.str_error), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite_menu, menu);
        if (favorite.isAvailable(favorite.getReadableDatabase(), data.getId()))
            menu.getItem(0).setIcon(R.drawable.ic_favorite);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.mnFavorite)
            if (favorite.isAvailable(favorite.getReadableDatabase(), data.getId())) {
                favorite.deleteFavorite(data.getId());
                item.setIcon(R.drawable.ic_favorite_empty);
            } else {
                favorite.setFavorite(data, jenis);
                item.setIcon(R.drawable.ic_favorite);
            }
        return super.onOptionsItemSelected(item);
    }
}
