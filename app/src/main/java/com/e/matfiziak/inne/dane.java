package com.e.matfiziak.inne;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;
public class dane {
    public static String nick="";
    public static String imageUrl = "default";
    public static int position = 0;
    public static int odpowiedz = -1;
    public static String id = "";
    public static List<Photo> listaZdj;
    public static List<PhotoForum> listaZdjForum;
    public static boolean gdzieScroll=true;
    public static int index=-1;
    public static int iloscZdjecForum=0;
    public static String idPost;
    public void usuwanieZdjec(){
        if(listaZdj!=null) {
            for (int i = 0; i < listaZdj.size(); i++) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Photo").child(listaZdj.get(i).getId());
                reference.removeValue();
            }
        }
    }
    public static int odpowiedzPozycja=-1;
}