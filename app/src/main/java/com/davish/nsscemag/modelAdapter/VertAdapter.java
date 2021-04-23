package com.davish.nsscemag.modelAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.davish.nsscemag.R;
import com.davish.nsscemag.models.BigData;

import java.util.List;


public class VertAdapter extends RecyclerView.Adapter<VertAdapter.HomeViewHolder> {

    public static SharedPreferences.OnSharedPreferenceChangeListener myPrefListner;

    int yes=0,no=0;
    TextView t;
    private Context context;
    private List<BigData> data;
    private HorizAdapter horizontalAdapter;
    private RecyclerView.RecycledViewPool recycledViewPool;


    public VertAdapter(List<BigData> data, Context context) {
        this.data = data;
        this.context = context;

        recycledViewPool = new RecyclerView.RecycledViewPool();

    }


    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View theView = LayoutInflater.from(context).inflate(R.layout.row_layout_home, parent, false);

        t = parent.findViewById(R.id.tv_movie_category);

        return new HomeViewHolder(theView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeViewHolder holder, final int position) {

        holder.textViewCategory.setText(data.get(position).getCategory());
        Log.d("arc",data.get(position).getArticleList().toString());
        horizontalAdapter = new HorizAdapter(data.get(position).getArticleList(), context);
        holder.recyclerViewHorizontal.setAdapter(horizontalAdapter);
        holder.recyclerViewHorizontal.setRecycledViewPool(recycledViewPool);


    }


    @Override
    public int getItemCount() {
        return data.size();

    }


    public class HomeViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerViewHorizontal;
        private TextView textViewCategory;

        private LinearLayoutManager horizontalManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        public HomeViewHolder(View itemView) {
            super(itemView);

            textViewCategory = itemView.findViewById(R.id.tv_movie_category);

            recyclerViewHorizontal = itemView.findViewById(R.id.home_recycler_view_horizontal);
            recyclerViewHorizontal.setHasFixedSize(true);
            recyclerViewHorizontal.setNestedScrollingEnabled(false);
            recyclerViewHorizontal.setLayoutManager(horizontalManager);
            recyclerViewHorizontal.setItemAnimator(new DefaultItemAnimator());



        }


    }


}
