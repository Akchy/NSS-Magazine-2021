package com.davish.nsscemag.modelAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.davish.nsscemag.DetailedArticle;
import com.davish.nsscemag.R;
import com.davish.nsscemag.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;


public class HorizAdapter extends RecyclerView.Adapter<HorizAdapter.MovieViewHolder> {


    private List<Article> articleList;
    private Context context;

    public HorizAdapter(List<Article> list, Context context) {
        this.articleList = list;
        this.context = context;


    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.row_layout_hor, parent, false));
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {

        final Article article = articleList.get(position);


        holder.author.setText(article.getAuthor());

        holder.textViewTitle.setText(article.getTitle());

        Picasso.with(context).
                load(R.drawable.bg)
                .into(holder.imageViewMovie);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailedArticle.class);
                i.putExtra("article_name", article.getAuthor());
                i.putExtra("article_title", article.getTitle());
                i.putExtra("article_desc", article.getDesc());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {

        return articleList.size();

    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle, author;
        private ImageView imageViewMovie;
        private CardView cardView;


        public MovieViewHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.bottom);
            textViewTitle = itemView.findViewById(R.id.title);
            imageViewMovie = itemView.findViewById(R.id.image_view_movie);
            cardView = itemView.findViewById(R.id.card_view_home);
        }


    }
}