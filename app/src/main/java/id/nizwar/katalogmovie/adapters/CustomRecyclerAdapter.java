package id.nizwar.katalogmovie.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.nizwar.katalogmovie.KatalogDetailActivity;
import id.nizwar.katalogmovie.R;
import id.nizwar.katalogmovie.models.Katalog;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ListViewHolder> {
    private Context context;
    private ArrayList<Katalog> data;
    private int jenis;

    public CustomRecyclerAdapter(Context context, ArrayList<Katalog> data, int jenis) {
        this.context = context;
        this.data = data;
        this.jenis = jenis;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_listitem, parent, false);
        return new ListViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "CheckResult"})
    @Override
    public void onBindViewHolder(@NonNull ListViewHolder viewHolder, final int position) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + data.get(position).getBackdropPath()).into(viewHolder.imgMovie);
        viewHolder.tvMovieTitle.setText(data.get(position).getTitle());
        if (data.get(position).getOverview() != null)
            if (data.get(position).getOverview().length() > 65) {
                viewHolder.tvMovieOverview.setText(("" + data.get(position).getOverview()).substring(0, 61) + context.getString(R.string.str_ellips));
            } else {
                viewHolder.tvMovieOverview.setText(data.get(position).getOverview());
            }
        viewHolder.tvMovieScoreAndRelease.setText(context.getString(R.string.str_score) + data.get(position).getVoteAverage() + ", " + data.get(position).getReleaseDate());
        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KatalogDetailActivity.class);
                intent.putExtra("katalog", (Parcelable) data.get(position));
                intent.putExtra("jenis", jenis);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout item;
        TextView tvMovieTitle, tvMovieOverview, tvMovieScoreAndRelease;
        ImageView imgMovie;

        ListViewHolder(@NonNull View convertView) {
            super(convertView);
            imgMovie = convertView.findViewById(R.id.gbrPoster);
            item = convertView.findViewById(R.id.relItem);
            tvMovieOverview = convertView.findViewById(R.id.tvOverview);
            tvMovieTitle = convertView.findViewById(R.id.tvJudulMovie);
            tvMovieScoreAndRelease = convertView.findViewById(R.id.tvDetailMore);
        }
    }
}