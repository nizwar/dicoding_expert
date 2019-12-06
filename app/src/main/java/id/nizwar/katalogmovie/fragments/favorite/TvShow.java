package id.nizwar.katalogmovie.fragments.favorite;

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

import id.nizwar.katalogmovie.R;
import id.nizwar.katalogmovie.adapters.CustomRecyclerAdapter;
import id.nizwar.katalogmovie.models.Katalog;
import id.nizwar.katalogmovie.models.SQFavorite;

public class TvShow extends Fragment {
    private ProgressBar pbLoading;
    private RecyclerView recMovie;
    private LinearLayout lnError;
    private LinearLayout lnEmpty;
    private SwipeRefreshLayout swiper;

    private SQFavorite sqFavorite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_reclist, container, false);
        recMovie = view.findViewById(R.id.recList);
        pbLoading = view.findViewById(R.id.pbLoading);
        lnError = view.findViewById(R.id.lnError);
        lnEmpty = view.findViewById(R.id.lnEmpty);

        recMovie.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        sqFavorite = new SQFavorite(getActivity());

        recMovie.setVisibility(View.GONE);
        lnError.setVisibility(View.GONE);
        lnEmpty.setVisibility(View.GONE);

        swiper = view.findViewById(R.id.swiper);
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiper.setRefreshing(false);
                getTVs();
            }
        });

        getTVs();
        return view;
    }

    private void getTVs() {
        pbLoading.setVisibility(View.VISIBLE);
        recMovie.setAdapter(null);
        ArrayList<Katalog> favorite = sqFavorite.getFavorite(1);
        if(favorite.size() > 0){
            recMovie.setAdapter(new CustomRecyclerAdapter(getActivity(), favorite, 1));
            recMovie.setVisibility(View.VISIBLE);
            lnError.setVisibility(View.GONE);
            lnEmpty.setVisibility(View.GONE);
        }else{
            recMovie.setVisibility(View.GONE);
            lnError.setVisibility(View.GONE);
            lnEmpty.setVisibility(View.VISIBLE);
        }
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        getTVs();
        super.onResume();
    }

}
