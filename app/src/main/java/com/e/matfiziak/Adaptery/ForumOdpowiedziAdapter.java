package com.e.matfiziak.Adaptery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.matfiziak.R;
import com.e.matfiziak.inne.Odpowiedzi;
import com.e.matfiziak.inne.PhotoForum;
import com.e.matfiziak.inne.WyswietlanieZdjecia;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ForumOdpowiedziAdapter extends RecyclerView.Adapter<ForumOdpowiedziAdapter.ViewHolder> {
    private Context mContext;
    private List<Odpowiedzi> mOdpowiedzi;
    public ForumOdpowiedziAdapter(Context context, List<Odpowiedzi> odpowiedzi) {
        this.mContext = context;
        this.mOdpowiedzi = odpowiedzi;
    }
    @NonNull
    @Override
    public ForumOdpowiedziAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.odpowiedzi_item, parent, false);
        return new ForumOdpowiedziAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ForumOdpowiedziAdapter.ViewHolder holder, int position) {
        final Odpowiedzi odpowiedzi = mOdpowiedzi.get(position);
        holder.username.setText(odpowiedzi.getUsername());
        holder.odpowiedzi.setText(odpowiedzi.getOdpowiedz());
        holder.idPost.setText(odpowiedzi.getId());
        holder.dodatkoweInf.setText(odpowiedzi.getDodatkoweInf());
        holder.liczbaLike.setText(odpowiedzi.getIloscLike());
        final List<String> tab = odpowiedzi.getListaLajkujacych();
        final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        if(fUser==null){
            holder.pom=false;
            holder.polubTo.setTextColor(Color.parseColor("#8F8F8F"));
        }
        else {
            for (String a : tab) {
                if (fUser.getUid().equals(a)) {
                    holder.pom = false;
                    holder.polubTo.setTextColor(Color.parseColor("#8F8F8F"));
                    break;
                }
            }
        }
        if(odpowiedzi.getIloscZdjec()>=1){
            holder.pierwszeZdjecie.setVisibility(View.VISIBLE);
        }
        if(odpowiedzi.getIloscZdjec()>=2){
            holder.drugieZdjecie.setVisibility(View.VISIBLE);
        }
        if(odpowiedzi.getIloscZdjec()>=3){
            holder.trzecieZdjecie.setVisibility(View.VISIBLE);
        }
        if(odpowiedzi.getIloscZdjec()>=4){
            holder.czwarteZdjecie.setVisibility(View.VISIBLE);
        }
        if(odpowiedzi.getIloscZdjec()>=5){
            holder.piateZdjecie.setVisibility(View.VISIBLE);
        }
        holder.pierwszeZdjecie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("PhotoForumOdpowiedzi");
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                            if(photoForum.getIdPost().equals(holder.idPost.getText().toString())){
                                Intent i = new Intent(mContext, WyswietlanieZdjecia.class);
                                i.putExtra("id",photoForum.getImageURL());
                                mContext.startActivity(i);
                                break;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
        holder.drugieZdjecie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("PhotoForumOdpowiedzi");
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                            if(photoForum.getIdPost().equals(holder.idPost.getText().toString())&&holder.index==1){
                                Intent i = new Intent(mContext, WyswietlanieZdjecia.class);
                                i.putExtra("id",photoForum.getImageURL());
                                mContext.startActivity(i);
                                holder.index=0;
                                break;
                            }
                            else if(photoForum.getIdPost().equals(holder.idPost.getText().toString())){
                                holder.index++;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
        holder.trzecieZdjecie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("PhotoForumOdpowiedzi");
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                            if(photoForum.getIdPost().equals(holder.idPost.getText().toString())&&holder.index==2){
                                Intent i = new Intent(mContext, WyswietlanieZdjecia.class);
                                i.putExtra("id",photoForum.getImageURL());
                                mContext.startActivity(i);
                                holder.index=0;
                                break;
                            }
                            else if(photoForum.getIdPost().equals(holder.idPost.getText().toString())){
                                holder.index++;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
        holder.czwarteZdjecie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("PhotoForumOdpowiedzi");
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                            if(photoForum.getIdPost().equals(holder.idPost.getText().toString())&&holder.index==3){
                                Intent i = new Intent(mContext, WyswietlanieZdjecia.class);
                                i.putExtra("id",photoForum.getImageURL());
                                mContext.startActivity(i);
                                holder.index=0;
                                break;
                            }
                            else if(photoForum.getIdPost().equals(holder.idPost.getText().toString())){
                                holder.index++;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
        holder.piateZdjecie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("PhotoForumOdpowiedzi");
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            PhotoForum photoForum = snapshot.getValue(PhotoForum.class);
                            if(photoForum.getIdPost().equals(holder.idPost.getText().toString())&&holder.index==4){
                                Intent i = new Intent(mContext, WyswietlanieZdjecia.class);
                                i.putExtra("id",photoForum.getImageURL());
                                mContext.startActivity(i);
                                holder.index=0;
                                break;
                            }
                            else if(photoForum.getIdPost().equals(holder.idPost.getText().toString())){
                                holder.index++;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });
        holder.polubTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.pom){
                    holder.polubTo.setTextColor(Color.parseColor("#8F8F8F"));
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ForumOdpowiedzi");
                    if(holder.liczbaLike.getText().toString().equals("")){
                        reference.child(odpowiedzi.getId()).child("iloscLike").setValue("1");
                        holder.liczbaLike.setText("1");
                    }
                    else{
                        int a = Integer.parseInt(holder.liczbaLike.getText().toString());
                        a+=1;
                        holder.liczbaLike.setText(""+a);
                        reference.child(odpowiedzi.getId()).child("iloscLike").setValue(""+a);
                    }
                    tab.add(fUser.getUid());
                    reference.child(odpowiedzi.getId()).child("listaLajkujacych").setValue(tab);
                    holder.pom=false;
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mOdpowiedzi.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dodatkoweInf, liczbaLike, odpowiedzi, username,idPost, polubTo;
        public ImageButton pierwszeZdjecie, drugieZdjecie, trzecieZdjecie, czwarteZdjecie, piateZdjecie;
        boolean pom;
        int index=0;
        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            liczbaLike = itemView.findViewById(R.id.liczbaLike);
            idPost = itemView.findViewById(R.id.idPost);
            odpowiedzi = itemView.findViewById(R.id.odpowiedz);
            dodatkoweInf = itemView.findViewById(R.id.dodatkoweInfOdp);
            polubTo = itemView.findViewById(R.id.polubToOdp);
            pom=true;
            index=0;
            pierwszeZdjecie = itemView.findViewById(R.id.pierwszeZdjecie);
            drugieZdjecie = itemView.findViewById(R.id.drugieZdjecie);
            trzecieZdjecie = itemView.findViewById(R.id.trzecieZdjecie);
            czwarteZdjecie = itemView.findViewById(R.id.czwarteZdjecie);
            piateZdjecie = itemView.findViewById(R.id.piateZdjecie);
        }
    }
}
