package id.nizwar.katalogmovie.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import id.nizwar.katalogmovie.R;
import id.nizwar.katalogmovie.adapters.CustomRecyclerAdapter;
import id.nizwar.katalogmovie.models.KatalogMovieAttrb;

public class Movie extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_reclist, container, false);
        RecyclerView recMovie = view.findViewById(R.id.recList);
        recMovie.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        if (getActivity() != null)
            recMovie.setAdapter(new CustomRecyclerAdapter(getActivity(), KatalogMovieAttrb.dummy(getActivity())));
        return view;
    }

}
