package id.nizwar.katalogmovie;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;
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

import id.nizwar.katalogmovie.helpers.Env;
import id.nizwar.katalogmovie.models.CUFavorite;
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
<<<<<<< HEAD
        int id_fav = getIntent().getIntExtra("id_favorite", -1);
        if (id_fav != -1) {
            if (favorite.isAvailable(favorite.getReadableDatabase(), id_fav)) {
                data = favorite.getFavoriteById(id_fav);
                jenis = data.getJenis();
                initialize();
            } else {
                Toast.makeText(this, getString(R.string.str_error), Toast.LENGTH_SHORT).show();
                finish();
            }
            return;
        }

        data = getIntent().getParcelableExtra("katalog");
        jenis = getIntent().getIntExtra("jenis", 0);

        initialize();
    }

    public void initialize() {
=======

        data = getIntent().getParcelableExtra("katalog");
        jenis = getIntent().getIntExtra("jenis", 0);
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
        if (data != null) {
            ((TextView) findViewById(R.id.tvTitle)).setText(data.getTitle());
            ((TextView) findViewById(R.id.tvOverview)).setText(data.getOverview());
            ((TextView) findViewById(R.id.tvRilis)).setText(data.getReleaseDate());
            ((TextView) findViewById(R.id.tvScore)).setText(String.valueOf(data.getVoteAverage()));

<<<<<<< HEAD
            Picasso.get().load(Env.IMG_URL780 + data.getPosterPath()).into((ImageView) findViewById(R.id.imgCover));
            Picasso.get().load(Env.IMG_URL500 + data.getPosterPath()).into((ImageView) findViewById(R.id.gbrPoster));
=======
            Picasso.get().load("https://image.tmdb.org/t/p/w780" + data.getPosterPath()).into((ImageView) findViewById(R.id.imgCover));
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + data.getPosterPath()).into((ImageView) findViewById(R.id.gbrPoster));
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
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
<<<<<<< HEAD
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
=======
        if (favorite.isAvailable(favorite.getReadableDatabase(), data.getId()))
            menu.getItem(0).setIcon(R.drawable.ic_favorite);

>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
<<<<<<< HEAD
        if (item.getItemId() == R.id.mnFavorite)
            if (favorite.isAvailable(favorite.getReadableDatabase(), data.getId())) {
                getContentResolver().delete(CUFavorite.FavoriteColumns.CONTENT_URI
                        , "id_movie = ?"
                        , new String[]{String.valueOf(data.getId())});

//                favorite.deleteFavorite(data.getId());
                item.setIcon(R.drawable.ic_favorite_empty);
            } else {
                ContentValues contentValues = new ContentValues();
                contentValues.put("id_movie", data.getId());
                contentValues.put("jenis", jenis);
                contentValues.put("title", data.getTitle());
                contentValues.put("overview", data.getOverview());
                contentValues.put("vote_average", data.getVoteAverage());
                contentValues.put("release_date", data.getReleaseDate());
                contentValues.put("original_title", data.getOriginalTitle());
                contentValues.put("backdrop_path", data.getBackdropPath());
                contentValues.put("poster_path", data.getPosterPath());
                getContentResolver().insert(CUFavorite.FavoriteColumns.CONTENT_URI, contentValues);

//                favorite.setFavorite(data, jenis);
=======

        if (item.getItemId() == R.id.mnFavorite)
            if (favorite.isAvailable(favorite.getReadableDatabase(), data.getId())) {
                favorite.deleteFavorite(data.getId());
                item.setIcon(R.drawable.ic_favorite_empty);
            } else {
                favorite.setFavorite(data, jenis);
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76
                item.setIcon(R.drawable.ic_favorite);
            }
        return super.onOptionsItemSelected(item);
    }
}
