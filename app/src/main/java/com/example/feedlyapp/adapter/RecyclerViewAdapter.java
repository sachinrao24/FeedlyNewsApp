package com.example.feedlyapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedlyapp.NewsItem;
import com.example.feedlyapp.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<NewsItem> newsItemArrayList;

    public RecyclerViewAdapter(ArrayList<NewsItem> newsItemArrayList) {
        this.newsItemArrayList = newsItemArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        String news_title = newsItemArrayList.get(position).getTitle();
        String news_category = newsItemArrayList.get(position).getCategory();
        String website_name = newsItemArrayList.get(position).getSite_name();
        int pop_count = newsItemArrayList.get(position).getPopularity_count();

        holder.setData(news_title, news_category, website_name, pop_count);
    }

    @Override
    public int getItemCount() {
        return newsItemArrayList==null ? 0 : newsItemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView category;
        private TextView site_name;
        private TextView popularity_count;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.news_title);
            category=itemView.findViewById(R.id.category);
            site_name=itemView.findViewById(R.id.site_name);
            popularity_count=itemView.findViewById(R.id.pop_count);
        }

        public void setData(String news_title, String news_category, String website_name, int pop_count) {
            title.setText(news_title);
            category.setText(news_category);
            site_name.setText(website_name);
            popularity_count.setText(String.valueOf(pop_count));
        }
    }
}
