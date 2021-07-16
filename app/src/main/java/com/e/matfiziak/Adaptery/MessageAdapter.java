package com.e.matfiziak.Adaptery;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.e.matfiziak.Czaty.Chat2;
import com.e.matfiziak.Czaty.Czat;
import com.e.matfiziak.R;
import com.e.matfiziak.inne.Chat;
import com.e.matfiziak.inne.Photo;
import com.e.matfiziak.inne.User;
import com.e.matfiziak.inne.WyswietlanieZdjecia;
import com.e.matfiziak.inne.dane;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    private Context mContext;
    private List<Chat> mChat;
    public TextView odpowiedz_tekst;
    public RelativeLayout odpowiedzLayout;
    private String imageUrl;
    FirebaseUser fuser;
    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public MessageAdapter(Context context, List<Chat> chats) {
        this.mContext = context;
        this.mChat = chats;
    }
    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT){
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view, mListener);
        }
        else if(viewType==2){
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right2, parent, false);
            return new MessageAdapter.ViewHolder(view, mListener);
        }
        else if(viewType==3){
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left2, parent, false);
            return new MessageAdapter.ViewHolder(view, mListener);
        }
        else if(viewType==4){
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right3, parent, false);
            return new MessageAdapter.ViewHolder(view, mListener);
        }
        else if(viewType==5){
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left3, parent, false);
            return new MessageAdapter.ViewHolder(view, mListener);
        }
        else{
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view, mListener);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Chat chat = mChat.get(position);
        holder.data.setVisibility(View.VISIBLE);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(chat.getSender());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                holder.nick.setText(user.getUsername());
                if(mChat.get(position).getLike()!=0){
                    holder.liczbaLikow.setVisibility(View.VISIBLE);
                    holder.liczbaLikow.setText(""+chat.getLike());
                }
                if(chat.getIloscZdjec()>0){
                    holder.pierwszeZdjecie.setVisibility(View.VISIBLE);
                }
                else{
                    holder.pierwszeZdjecie.setVisibility(View.GONE);
                }
                if(chat.getIloscZdjec()>1){
                    holder.drugieZdjecie.setVisibility(View.VISIBLE);
                }
                else{
                    holder.drugieZdjecie.setVisibility(View.GONE);
                }
                if(chat.getIloscZdjec()>2){
                    holder.trzecieZdjecie.setVisibility(View.VISIBLE);
                }
                else{
                    holder.trzecieZdjecie.setVisibility(View.GONE);
                }
                if(chat.getIloscZdjec()>3){
                    holder.czwarteZdjecie.setVisibility(View.VISIBLE);
                }
                else{
                    holder.czwarteZdjecie.setVisibility(View.GONE);
                }
                if(chat.getIloscZdjec()>4){
                    holder.piateZdjecie.setVisibility(View.VISIBLE);
                }
                else{
                    holder.piateZdjecie.setVisibility(View.GONE);
                }
                if(user.getImageUrl()==null){
                    user.setImageUrl("default");
                }
                if(user.getImageUrl().equals("default")){
                    holder.profile_image.setImageResource(R.mipmap.ic_launcher);
                }
                else{
                    Glide.with(mContext).load(user.getImageUrl()).into(holder.profile_image);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
        holder.show_message.setText(chat.getMessage());
        LocalDateTime localDateTime = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            localDateTime = LocalDateTime.now();
        }
        if(localDateTime!= null){
            StringBuilder stringBuilder = new StringBuilder(chat.getData().substring(8,10));
            int pom = Integer.parseInt(stringBuilder.toString());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if(pom==localDateTime.getDayOfMonth()){
                    holder.data.setText("Dzisiaj "+chat.getData().substring(11));

                }
                else if(pom+1==localDateTime.getDayOfMonth()){
                    holder.data.setText("Wczoraj "+chat.getData().substring(11));
                }
                else if(pom+2==localDateTime.getDayOfMonth()){
                    holder.data.setText("Przedwczoraj "+chat.getData().substring(11));
                }
                else{
                    holder.data.setText(chat.getData());
                }
            }
            else{
                holder.data.setText(chat.getData());
            }
        }
        else{
            holder.data.setText(chat.getData());
        }
        if(imageUrl==null){
            imageUrl="default";
        }
        if(imageUrl.equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Glide.with(mContext).load(imageUrl).into(holder.profile_image);
        }
    }
    @Override
    public int getItemCount() {
        return mChat.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView show_message;
        public TextView username;
        public ImageView profile_image;
        public TextView odpowiedz_tekst;
        public RelativeLayout odpowiedzLayout;
        public TextView nick;
        public TextView data;
        public TextView odpowiedz, like, liczbaLikow;
        ImageView pierwszeZdjecie,drugieZdjecie,trzecieZdjecie,czwarteZdjecie,piateZdjecie;
        public ViewHolder(final View itemView, final OnItemClickListener listener){
            super(itemView);
            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profileImage);
            nick = itemView.findViewById(R.id.nick);
            odpowiedz = itemView.findViewById(R.id.options);
            data = itemView.findViewById(R.id.data);
            liczbaLikow = itemView.findViewById(R.id.liczbaLikow);
            like = itemView.findViewById(R.id.like);
            pierwszeZdjecie = itemView.findViewById(R.id.pierwszeZdjecie);
            drugieZdjecie = itemView.findViewById(R.id.drugieZdjecie);
            trzecieZdjecie = itemView.findViewById(R.id.trzecieZdjecie);
            czwarteZdjecie = itemView.findViewById(R.id.czwarteZdjecie);
            piateZdjecie = itemView.findViewById(R.id.piateZdjecie);
            pierwszeZdjecie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    otworzZdjecie(0, getAdapterPosition());
                }
            });
            drugieZdjecie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    otworzZdjecie(1, getAdapterPosition());
                }
            });
            trzecieZdjecie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    otworzZdjecie(2, getAdapterPosition());
                }
            });
            czwarteZdjecie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    otworzZdjecie(3, getAdapterPosition());
                }
            });
            piateZdjecie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    otworzZdjecie(4, getAdapterPosition());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if(listener!=null){
                        Czat czat = new Czat();
                        final Chat2 chat2 = new Chat2();
                        if(czat.getTab()==chat2.ktoraKlasa) {
                            final PopupMenu popupMenu = new PopupMenu(mContext, v);
                            popupMenu.inflate(R.menu.popup_menu);
                            final Menu menu = popupMenu.getMenu();
                            DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Chats");
                            reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                        Chat chat = snapshot.getValue(Chat.class);
                                        if(chat.getLista()!=null&&fuser!=null){
                                            for(int i=0;i <chat.getLista().size();i++){
                                                if(chat.getLista().get(i).equals(fuser.getUid())&&(chat.getId().equals(mChat.get(getAdapterPosition()).getId()))){
                                                    MenuItem menuItem = menu.getItem(0);
                                                    menuItem.setEnabled(false);
                                                    break;
                                                }
                                            }
                                        }
                                        else{
                                            MenuItem menuItem = menu.getItem(0);
                                            menuItem.setEnabled(false);
                                        }
                                    }
                                    popupMenu.show();
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {}
                            });
                            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {
                                    if (item.getItemId() == R.id.polubToMenu) {
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");
                                        List<String> listaLajkujacych = mChat.get(getAdapterPosition()).getLista();
                                        if(listaLajkujacych==null){
                                            listaLajkujacych = new ArrayList<>();
                                        }
                                        liczbaLikow.setVisibility(View.VISIBLE);
                                        chat2.booleanEventListener = false;
                                        if(liczbaLikow.getText().toString().equals("")){
                                            liczbaLikow.setText("1");
                                            reference.child(mChat.get(getAdapterPosition()).getId()+"/like").setValue(1);
                                        }
                                        else {
                                            int a = Integer.parseInt(liczbaLikow.getText().toString());
                                            a += 1;
                                            reference.child(mChat.get(getAdapterPosition()).getId() + "/like").setValue(a);
                                            liczbaLikow.setText("" + a);
                                        }
                                        listaLajkujacych.add(fuser.getUid());
                                        FirebaseDatabase.getInstance().getReference("Chats").child(mChat.get(getAdapterPosition()).getId()).child("lista").setValue(listaLajkujacych);
                                    }
                                    else {
                                        Chat2 chat2 = new Chat2();
                                        chat2.odpowiedzLayout.setVisibility(View.VISIBLE);
                                        int position = getAdapterPosition();
                                        dane dane1 = new dane();if(mChat.get(position).getPosition()==-1){
                                            chat2.odpowiedz_tekst.setText(mChat.get(position).getMessage());
                                            dane1.odpowiedz = 0;
                                            dane1.odpowiedzPozycja = position;
                                        }
                                        else{
                                            chat2.odpowiedz_tekst.setText(mChat.get(position).getMessage());
                                            dane1.odpowiedz = 1;
                                            dane1.odpowiedzPozycja = position;
                                        }
                                        dane1.position = position;
                                    }
                                    return false;
                                }
                            });
                        }
                    }
                }
            });
        }
    }
    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if(fuser!=null) {
            if ((mChat.get(position).getSender().equals(fuser.getUid())) && (mChat.get(position).getOdpowiedz() == -1)) {
                return MSG_TYPE_RIGHT;
            } else if ((mChat.get(position).getSender().equals(fuser.getUid())) && (mChat.get(position).getOdpowiedz() == 0)) {
                return 2;
            } else if ((mChat.get(position).getSender().equals(fuser.getUid())) && (mChat.get(position).getOdpowiedz() == 1)) {
                return 4;
            } else if (mChat.get(position).getOdpowiedz() == -1) {
                return MSG_TYPE_LEFT;
            } else if (mChat.get(position).getOdpowiedz() == 0) {
                return 3;
            } else {
                return 5;
            }
        }
        else{
            if (mChat.get(position).getOdpowiedz() == -1) {
                return MSG_TYPE_LEFT;
            } else if (mChat.get(position).getOdpowiedz() == 0) {
                return 3;
            } else {
                return 5;
            }
        }
    }
    public void otworzZdjecie(final int numer, int position){
        final int[] index = {0};
        final boolean[] lala = {true};
        dane dane1 = new dane();
        dane1.gdzieScroll=false;
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Photo");
        final String idWiad = mChat.get(position).getZdjecie();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot :dataSnapshot.getChildren()){
                    Photo photo = snapshot.getValue(Photo.class);
                    if(idWiad==null||photo.getIdWiad()==null){
                        break;
                    }
                    if (photo.getIdWiad().equals(idWiad)&& index[0] ==numer&&lala[0]) {
                        Intent i = new Intent(mContext, WyswietlanieZdjecia.class);
                        i.putExtra("id", photo.getImageURL());
                        dane dane1 = new dane();
                        Czat czat = new Czat();
                        dane1.index = czat.getTab();
                        mContext.startActivity(i);
                        break;
                    }
                    if(photo.getIdWiad().equals(idWiad)) {
                        index[0]++;
                    }
                }
                lala[0] =false;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
}