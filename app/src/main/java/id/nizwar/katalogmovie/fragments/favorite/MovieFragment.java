package id.nizwar.katalogmovie.fragments.favorite;

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

import java.util.ArrayList;

import id.nizwar.katalogmovie.PencarianActivity;
import id.nizwar.katalogmovie.R;
import id.nizwar.katalogmovie.adapters.CustomRecyclerAdapter;
import id.nizwar.katalogmovie.helpers.DataObserver;
import id.nizwar.katalogmovie.models.Katalog;

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

        view.findViewById(R.id.floatButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PencarianActivity.class);
                intent.putExtra("jenis", 0);
                intent.putExtra("online", 0);
                startActivity(intent);
            }
        });

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
        new DataObserver.LoadKatalogAsync(getActivity(), 0,null, new DataObserver.LoadKatalogsCallback() {
            @Override
            public void preExecute() {
                showLoading();
            }

            @Override
            public void postExecute(ArrayList<Katalog> katalogs) {
                if (katalogs.size() > 0) {
                    recMovie.setAdapter(new CustomRecyclerAdapter(getActivity(), katalogs, 0));
                    showList();
                } else {
                    showEmpty();
                }
            }
        }).execute();
    }

//    private void getMoviesOld() {
//        pbLoading.setVisibility(View.VISIBLE);
//        recMovie.setAdapter(null);
//
//        ArrayList<Katalog> favorite = sqFavorite.getFavorite(0, null);
//        if (favorite.size() > 0) {
//            recMovie.setAdapter(new CustomRecyclerAdapter(getActivity(), favorite, 0));
//            showList();
//        } else {
//            showEmpty();
//        }
//    }

    @Override
    public void onResume() {
        getMovies();
        super.onResume();
    }

    private void showEmpty() {
        swiper.setRefreshing(false);
        lnError.setVisibility(View.GONE);
        pbLoading.setVisibility(View.GONE);
        lnEmpty.setVisibility(View.VISIBLE);
        recMovie.setVisibility(View.GONE);
    }

    private void showLoading() {
        swiper.setRefreshing(false);
        lnError.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
        lnEmpty.setVisibility(View.GONE);
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
