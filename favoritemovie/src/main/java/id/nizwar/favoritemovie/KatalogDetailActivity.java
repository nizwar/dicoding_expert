package id.nizwar.favoritemovie;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import id.nizwar.favoritemovie.helpers.Env;
import id.nizwar.favoritemovie.models.CUFavorite;
import id.nizwar.favoritemovie.models.Katalog;
import id.nizwar.favoritemovie.models.SQFavorite;

public class KatalogDetailActivity extends AppCompatActivity {
    private Katalog data;

    @SuppressLint({"SetTextI18n", "CheckResult"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog_detail);

        setSupportActionBar((Toolbar) findViewById(R.id.appToolbar));
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SQFavorite favorite = new SQFavorite(this);
        int id_fav = getIntent().getIntExtra("id_favorite", -1);
        if (id_fav != -1) {
            if (favorite.isAvailable(favorite.getReadableDatabase(), id_fav)) {
                data = favorite.getFavoriteById(id_fav);
                initialize();
            } else {
                Toast.makeText(this, getString(R.string.str_error), Toast.LENGTH_SHORT).show();
                finish();
            }
            return;
        }

        data = getIntent().getParcelableExtra("katalog");

        initialize();
    }

    public void initialize() {
        if (data != null) {
            ((TextView) findViewById(R.id.tvTitle)).setText(data.getTitle());
            ((TextView) findViewById(R.id.tvOverview)).setText(data.getOverview());
            ((TextView) findViewById(R.id.tvRilis)).setText(data.getReleaseDate());
            ((TextView) findViewById(R.id.tvScore)).setText(String.valueOf(data.getVoteAverage()));

            Picasso.get().load(Env.IMG_URL780 + data.getPosterPath()).into((ImageView) findViewById(R.id.imgCover));
            Picasso.get().load(Env.IMG_URL500 + data.getPosterPath()).into((ImageView) findViewById(R.id.gbrPoster));
        } else {
            Toast.makeText(this, getString(R.string.str_error), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite_menu, menu);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Cursor cursor = getContentResolver()
                    .query(
                            CUFavorite.FavoriteColumns.CONTENT_URI,
                            null,
                            "id_movie=? ",
                            new String[]{String.valueOf(data.getId())},
                            null,
                            null);
            assert cursor != null;
            if (cursor.getCount() > 0) {
                menu.getItem(0).setIcon(R.drawable.ic_favorite);
            }
            cursor.close();
        }
        return true;
    }

}
