package com.davish.nsscemag.modelAdapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.davish.nsscemag.R;
import com.davish.nsscemag.models.SmallData;

import java.util.List;

public class SmallAdapter extends RecyclerView.Adapter<SmallAdapter.SmallViewHolder> {


    private List<SmallData> movieList;
    private Context context;

    public SmallAdapter(List<SmallData> list, Context context) {
        this.movieList = list;
        this.context = context;


    }


    @Override
    public SmallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new SmallViewHolder(LayoutInflater.from(context).inflate(R.layout.small_data, parent, false));
    }

    @Override
    public void onBindViewHolder(final SmallViewHolder holder, final int position) {

        SmallData movie = movieList.get(position);


        holder.textViewTitle.setText(movie.getName());

        int[] colors = {Color.parseColor("#" + movie.getsColor()), Color.parseColor("#" + movie.geteColor())};


        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT, colors);
        gd.setCornerRadius(20f);
        holder.cardView.setBackground(gd);


    }

    @Override
    public int getItemCount() {

        return movieList.size();

    }


    public class SmallViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private CardView cardView;

        public SmallViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textview);
            cardView = itemView.findViewById(R.id.cardview);

        }


    }
}