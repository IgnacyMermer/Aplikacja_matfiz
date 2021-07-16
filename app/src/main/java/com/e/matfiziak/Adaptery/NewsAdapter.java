package com.e.matfiziak.Adaptery;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.matfiziak.R;
import com.e.matfiziak.inne.News;

import java.util.List;
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Context context;
    List<News> lista;
    private NewsAdapter.OnItemClickListener mListener;
    public NewsAdapter(Context context, List<News> lista){
        this.context = context;
        this.lista = lista;
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(NewsAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_post, parent, false);
        return new NewsAdapter.ViewHolder(view, mListener);
    }
    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        News news = lista.get(position);
        holder.tekst.setText(news.getTekst());
        holder.link.setText(news.getLink());
        holder.zrodlo.setText(news.getZrodlo());
    }
    @Override
    public int getItemCount() {
        return lista.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tekst, zrodlo, link;
        public ViewHolder(@NonNull View itemView, final NewsAdapter.OnItemClickListener listener) {
            super(itemView);
            tekst = itemView.findViewById(R.id.tekstNewsa);
            link = itemView.findViewById(R.id.idNewsa);
            zrodlo = itemView.findViewById(R.id.zrodloNewsa);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(link.getText().toString()));
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}