package id.nizwar.katalogmovie;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import id.nizwar.katalogmovie.adapters.CustomRecyclerAdapter;
import id.nizwar.katalogmovie.helpers.DataObserver;
import id.nizwar.katalogmovie.helpers.Env;
import id.nizwar.katalogmovie.models.Katalog;
import id.nizwar.katalogmovie.models.KatalogMovieAttrb;
import id.nizwar.katalogmovie.models.KatalogTvShowAttrb;

public class PencarianActivity extends AppCompatActivity {
    private RecyclerView recList;
    private ProgressBar pbLoading;
    private LinearLayout lnError;
    private LinearLayout lnEmpty;
    private SwipeRefreshLayout swiper;

    private int jenis, online;
    private String cariTerakhir;
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarian);
        setSupportActionBar((Toolbar) findViewById(R.id.appToolbar));
        pbLoading = findViewById(R.id.pbLoading);
        lnError = findViewById(R.id.lnError);
        lnEmpty = findViewById(R.id.lnEmpty);
        swiper = findViewById(R.id.swiper);

        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cari(cariTerakhir);
            }
        });

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        jenis = bundle.getInt("jenis", 0);
        online = bundle.getInt("online", 0);
        recList = findViewById(R.id.recList);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        query = bundle.getString("query", null);
        cari(query);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.mnPencarian).getActionView();
        assert searchManager != null;
        searchManager.setOnDismissListener(new SearchManager.OnDismissListener() {
            @Override
            public void onDismiss() {
                cari(null);
            }
        });
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setQuery(query, false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.trim().equals("")){
                    cariTerakhir = null;
                    cari(null);
                    return true;
                }
                cari(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setMaxWidth(Integer.MAX_VALUE);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void cari(String query) {
        showLoading();
        if (online == 1) {
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    Env.BASE_URL(jenis == 1 ? "tv" : "movie", query),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            swiper.setRefreshing(false);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsResults = jsonObject.getJSONArray("results");
                                ArrayList<Katalog> listMovie = new ArrayList<>();
                                if (jenis == 0) {
                                    for (int i = 0; i < jsResults.length(); i++) {
                                        listMovie.add(new KatalogMovieAttrb(jsResults.getJSONObject(i)));
                                    }
                                } else if (jenis == 1) {
                                    for (int i = 0; i < jsResults.length(); i++) {
                                        listMovie.add(new KatalogTvShowAttrb(jsResults.getJSONObject(i)));
                                    }
                                }
                                recList.setAdapter(new CustomRecyclerAdapter(PencarianActivity.this, listMovie, jenis));
                                if (jsResults.length() > 0) {
                                    showList();
                                } else {
                                    showEmpty();
                                }
                            } catch (JSONException e) {
                                showError();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            swiper.setRefreshing(false);
                            showError();
                        }
                    }
            );

            Volley.newRequestQueue(this).add(request);
        } else {
            new DataObserver.LoadKatalogAsync(this, jenis, query, new DataObserver.LoadKatalogsCallback() {
                @Override
                public void preExecute() {
                    showLoading();
                }

                @Override
                public void postExecute(ArrayList<Katalog> katalogs) {
                    if (katalogs.size() > 0) {
                        recList.setAdapter(new CustomRecyclerAdapter(PencarianActivity.this, katalogs, jenis));
                        showList();
                    } else {
                        showEmpty();
                    }
                }
            }).execute();
        }
    }

    private void showError() {
        swiper.setRefreshing(false);
        lnError.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.GONE);
        lnEmpty.setVisibility(View.GONE);
        recList.setVisibility(View.GONE);
    }

    private void showLoading() {
        swiper.setRefreshing(false);
        lnError.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
        lnEmpty.setVisibility(View.GONE);
        recList.setVisibility(View.GONE);
    }

    private void showEmpty() {
        swiper.setRefreshing(false);
        lnError.setVisibility(View.GONE);
        pbLoading.setVisibility(View.GONE);
        lnEmpty.setVisibility(View.VISIBLE);
        recList.setVisibility(View.GONE);
    }

    private void showList() {
        swiper.setRefreshing(false);
        lnError.setVisibility(View.GONE);
        pbLoading.setVisibility(View.GONE);
        lnEmpty.setVisibility(View.GONE);
        recList.setVisibility(View.VISIBLE);
    }

}
