package com.e.matfiziak.Adaptery;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.matfiziak.ForumPackage.OtworzPost;
import com.e.matfiziak.R;
import com.e.matfiziak.inne.Post;

import java.util.List;
public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ViewHolder> {
    private Context mContext;
    private List<Post> mPost;
    public ForumAdapter(Context context, List<Post> users) {
        this.mContext = context;
        this.mPost = users;
    }
    @NonNull
    @Override
    public ForumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.post_item, parent, false);
        return new ForumAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Post post = mPost.get(position);
        holder.username.setText(post.getUsername());
        holder.tytul.setText(post.getTytul());
        holder.tresc.setText(post.getTresc());
        holder.numerZad.setText(post.getNumerZad());
        holder.idPost.setText(post.getId());
        if(post.getLiczbaKomentarzy()!=null){
            if(!post.getLiczbaKomentarzy().equals("")){
                holder.liczbaKomentarzy.setText(holder.liczbaKomentarzy.getText().toString()+" "+post.getLiczbaKomentarzy());
            }
            else{
                holder.liczbaKomentarzy.setText("");
            }
        }
        else{
            holder.liczbaKomentarzy.setText("");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(mContext, OtworzPost.class);
                i.putExtra("id",post.getId());
                mContext.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount(){
        return mPost.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView liczbaKomentarzy, numerZad, tresc, tytul, username,idPost;
        public ViewHolder(View itemView){
            super(itemView);
            username = itemView.findViewById(R.id.username);
            liczbaKomentarzy = itemView.findViewById(R.id.liczbaKomentarzy);
            numerZad= itemView.findViewById(R.id.numerZa);
            tresc = itemView.findViewById(R.id.tresc);
            tytul = itemView.findViewById(R.id.tytul);
            idPost = itemView.findViewById(R.id.idPost);
        }
    }
}