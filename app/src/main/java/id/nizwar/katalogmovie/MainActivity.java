package id.nizwar.katalogmovie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import id.nizwar.katalogmovie.models.KatalogAttrb;

public class MainActivity extends AppCompatActivity {

    ArrayList<KatalogAttrb> dataMovie = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMovie();

        ListView listKatalog = findViewById(R.id.listKatalog);
        listKatalog.setAdapter(new CustomAdapter(this, dataMovie));
    }


    void initMovie() {
        dataMovie.add(new KatalogAttrb(R.drawable.poster_a_star, 75, "A Star Is Born", "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.", "October 3, 2018"));
        dataMovie.add(new KatalogAttrb(R.drawable.poster_aquaman, 68, "Aquaman", "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.", "December 7, 2018"));
        dataMovie.add(new KatalogAttrb(R.drawable.poster_avengerinfinity, 83, "Avenger Infinity War", "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.", "April 25, 2018"));
        dataMovie.add(new KatalogAttrb(R.drawable.poster_birdbox, 69, "Bird Box", "Five years after an ominous unseen presence drives most of society to suicide, a survivor and her two children make a desperate bid to reach safety.", "December 13, 2018"));
        dataMovie.add(new KatalogAttrb(R.drawable.poster_bohemian, 80, "Bohemian Rhapsody", "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.", "October 24, 2018"));
        dataMovie.add(new KatalogAttrb(R.drawable.poster_bumblebee, 65, "Bumblebee", "On the run in the year 1987, Bumblebee finds refuge in a junkyard in a small Californian beach town. Charlie, on the cusp of turning 18 and trying to find her place in the world, discovers Bumblebee, battle-scarred and broken. When Charlie revives him, she quickly learns this is no ordinary yellow VW bug.", "December 15, 2018"));
        dataMovie.add(new KatalogAttrb(R.drawable.poster_creed, 54, "Assasins Creed", "Through unlocked genetic memories that allow him to relive the adventures of his ancestor in 15th century Spain, Callum Lynch discovers he's a descendant of the secret 'Assassins' society. After gaining incredible knowledge and skills, he is now poised to take on the oppressive Knights Templar in the present day.", "December 21, 2016"));
        dataMovie.add(new KatalogAttrb(R.drawable.poster_dragon, 77, "How to Train Your Dragon: The Hidden World", "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.", "January 3, 2019"));
        dataMovie.add(new KatalogAttrb(R.drawable.poster_dragonball, 58, "Dragon Ball", "The young warrior Son Goku sets out on a quest, racing against time and the vengeful King Piccolo, to collect a set of seven magical orbs that will grant their wielder unlimited power.", "April 5, 2009"));
        dataMovie.add(new KatalogAttrb(R.drawable.poster_glass, 66, "Glass", "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.", "January 16, 2019"));
        dataMovie.add(new KatalogAttrb(R.drawable.poster_hunterkiller, 64, "Hunter Killer", "Captain Glass of the USS Arkansas discovers that a coup d'état is taking place in Russia, so he and his crew join an elite group working on the ground to prevent a war.", "October 19, 2018"));
    }


    class CustomAdapter extends ArrayAdapter<KatalogAttrb> {
        final Context context;
        final ArrayList<KatalogAttrb> data;

        CustomAdapter(@NonNull Context context, ArrayList<KatalogAttrb> data) {
            super(context, R.layout.lay_listitem, data);
            this.context = context;
            this.data = data;
        }

        @SuppressLint("SetTextI18n")
        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.lay_listitem, parent, false);
                viewHolder.imgMovie = convertView.findViewById(R.id.gbrPoster);
                viewHolder.item = convertView.findViewById(R.id.relItem);
                viewHolder.tvMovieOverview = convertView.findViewById(R.id.tvOverview);
                viewHolder.tvMovieTitle = convertView.findViewById(R.id.tvJudulMovie);
                viewHolder.tvMovieScoreAndRelease = convertView.findViewById(R.id.tvDetailMore);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.imgMovie.setImageResource(data.get(position).getPosterID());
            viewHolder.tvMovieTitle.setText(data.get(position).getTitle());
            viewHolder.tvMovieOverview.setText(data.get(position).getOverview().substring(0, 61) + getString(R.string.str_ellips));
            viewHolder.tvMovieScoreAndRelease.setText("Score " + data.get(position).getScore() + ", " + data.get(position).getRilis());
            viewHolder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, KatalogDetailActivity.class);
                    intent.putExtra("katalog", data.get(position));
                    startActivity(intent);
                }
            });

            return convertView;
        }

        class ViewHolder {
            RelativeLayout item;
            TextView tvMovieTitle, tvMovieOverview, tvMovieScoreAndRelease;
            ImageView imgMovie;
        }
    }
}
