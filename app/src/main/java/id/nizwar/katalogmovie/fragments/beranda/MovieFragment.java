package id.nizwar.katalogmovie.fragments.beranda;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
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

import id.nizwar.katalogmovie.helpers.Env;
import id.nizwar.katalogmovie.PencarianActivity;
import id.nizwar.katalogmovie.R;
import id.nizwar.katalogmovie.adapters.CustomRecyclerAdapter;
import id.nizwar.katalogmovie.models.Katalog;
import id.nizwar.katalogmovie.models.KatalogMovieAttrb;

public class MovieFragment extends Fragment {
    private ProgressBar pbLoading;
    private RecyclerView recMovie;
    private LinearLayout lnError;
    private LinearLayout lnEmpty;
    private SwipeRefreshLayout swiper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_reclist, container, false);
        recMovie = view.findViewById(R.id.recList);
        pbLoading = view.findViewById(R.id.pbLoading);
        lnError = view.findViewById(R.id.lnError);
        lnEmpty = view.findViewById(R.id.lnEmpty);

<<<<<<< HEAD:app/src/main/java/id/nizwar/katalogmovie/fragments/beranda/MovieFragment.java
        view.findViewById(R.id.floatButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PencarianActivity.class);
                intent.putExtra("jenis", 0);
                intent.putExtra("online", 1);
                startActivity(intent);
            }
        });
=======
        recMovie.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        recMovie.setVisibility(View.GONE);
        lnError.setVisibility(View.GONE);
        lnEmpty.setVisibility(View.GONE);
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76:app/src/main/java/id/nizwar/katalogmovie/fragments/beranda/Movie.java

        recMovie.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        swiper = view.findViewById(R.id.swiper);
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiper.setRefreshing(false);
                getMovies();
            }
        });



        getMovies();
        return view;
    }

    private void getMovies() {
<<<<<<< HEAD:app/src/main/java/id/nizwar/katalogmovie/fragments/beranda/MovieFragment.java
        showLoading();
=======
        lnError.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
        recMovie.setVisibility(View.GONE);
        lnEmpty.setVisibility(View.GONE);
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76:app/src/main/java/id/nizwar/katalogmovie/fragments/beranda/Movie.java
        StringRequest request = new StringRequest(
                Request.Method.GET,
                Env.BASE_URL("movie", null),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        swiper.setRefreshing(false);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsResults = jsonObject.getJSONArray("results");
                            ArrayList<Katalog> listMovie = new ArrayList<>();
                            for (int i = 0; i < jsResults.length(); i++) {
                                listMovie.add(new KatalogMovieAttrb(jsResults.getJSONObject(i)));
                            }
                            if (getActivity() != null)
                                recMovie.setAdapter(new CustomRecyclerAdapter(getActivity(), listMovie, 0));
<<<<<<< HEAD:app/src/main/java/id/nizwar/katalogmovie/fragments/beranda/MovieFragment.java
                            if (jsResults.length() > 0) {
                                showList();
                            } else {
                                showEmpty();
                            }
                        } catch (JSONException e) {
                            showError();
=======
                            lnError.setVisibility(View.GONE);
                            pbLoading.setVisibility(View.GONE);
                            if(jsResults.length() > 0 ) {
                                lnEmpty.setVisibility(View.GONE);
                                recMovie.setVisibility(View.VISIBLE);
                            }else{
                                lnEmpty.setVisibility(View.VISIBLE);
                                recMovie.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            lnError.setVisibility(View.VISIBLE);
                            pbLoading.setVisibility(View.GONE);
                            recMovie.setVisibility(View.GONE);
                            lnEmpty.setVisibility(View.GONE);
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76:app/src/main/java/id/nizwar/katalogmovie/fragments/beranda/Movie.java
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
<<<<<<< HEAD:app/src/main/java/id/nizwar/katalogmovie/fragments/beranda/MovieFragment.java
                        showError();
=======
                        swiper.setRefreshing(false);
                        lnError.setVisibility(View.VISIBLE);
                        pbLoading.setVisibility(View.GONE);
                        lnEmpty.setVisibility(View.GONE);
                        recMovie.setVisibility(View.GONE);
>>>>>>> f06c9266104ac9b6156f5b30189cda8740b14d76:app/src/main/java/id/nizwar/katalogmovie/fragments/beranda/Movie.java
                    }
                }
        );

        if (getActivity() != null)
            Volley.newRequestQueue(getActivity()).add(request);
    }

    private void showError() {
        swiper.setRefreshing(false);
        lnError.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.GONE);
        lnEmpty.setVisibility(View.GONE);
        recMovie.setVisibility(View.GONE);
    }

    private void showLoading() {
        swiper.setRefreshing(false);
        lnError.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
        lnEmpty.setVisibility(View.GONE);
        recMovie.setVisibility(View.GONE);
    }

    private void showEmpty() {
        swiper.setRefreshing(false);
        lnError.setVisibility(View.GONE);
        pbLoading.setVisibility(View.GONE);
        lnEmpty.setVisibility(View.VISIBLE);
        recMovie.setVisibility(View.GONE);
    }

    private void showList() {
        swiper.setRefreshing(false);
        lnError.setVisibility(View.GONE);
        pbLoading.setVisibility(View.GONE);
        lnEmpty.setVisibility(View.GONE);
        recMovie.setVisibility(View.VISIBLE);
    }

}
