package com.e.matfiziak.Fragments;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.e.matfiziak.R;
public class Kalkulator1 extends Fragment {
    TextView znak;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.kalkulator1, container, false);
        final EditText pierwszyDuzy = view.findViewById(R.id.pierwszyDuzy);
        final EditText drugiDuzy = view.findViewById(R.id.drugiDuzy);
        final EditText pierwszyLicznik = view.findViewById(R.id.pierwszyLicznik);
        final EditText drugiLicznik = view.findViewById(R.id.drugiLicznik);
        final EditText pierwszyMianownik = view.findViewById(R.id.pierwszyMianownik);
        final EditText drugiMianownik = view.findViewById(R.id.drugiMianownik);
        znak = view.findViewById(R.id.znakDodawania);
        final TextView duzyWynik = view.findViewById(R.id.trzeciDuzy);
        final TextView licznikWynik = view.findViewById(R.id.trzeciLicznik);
        final TextView mianownikWynik = view.findViewById(R.id.trzeciMianownik);
        Button buttonWynik = view.findViewById(R.id.buttonObliczKalkulator);
        Button buttonCzysc = view.findViewById(R.id.buttonCzyscKalkulator);
        znak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(znak);
                v.showContextMenu();
            }
        });
        buttonCzysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pierwszyDuzy.setText("");
                drugiDuzy.setText("");
                pierwszyLicznik.setText("");
                drugiLicznik.setText("");
                pierwszyMianownik.setText("");
                drugiMianownik.setText("");
                duzyWynik.setText("");
                znak.setText("+");
                licznikWynik.setText("");
                mianownikWynik.setText("");
            }
        });
        buttonWynik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int licznik1=0, licznik2=0, mianownik1=0, mianownik2=0, a,b=0,c;
                if((!pierwszyLicznik.getText().toString().equals(""))&&(!pierwszyMianownik.getText().toString().equals(""))){
                    licznik1 = Integer.parseInt(pierwszyLicznik.getText().toString());
                    mianownik1 = Integer.parseInt(pierwszyMianownik.getText().toString());
                    if(!pierwszyDuzy.getText().toString().equals("")){
                        a = Integer.parseInt(pierwszyDuzy.getText().toString());
                        a = a*mianownik1;
                        licznik1+=a;
                    }
                }
                if((!drugiLicznik.getText().toString().equals(""))&&(!drugiMianownik.getText().toString().equals(""))){
                    licznik2 = Integer.parseInt(drugiLicznik.getText().toString());
                    mianownik2 = Integer.parseInt(drugiMianownik.getText().toString());
                    if(!drugiDuzy.getText().toString().equals("")){
                        a = Integer.parseInt(drugiDuzy.getText().toString());
                        a = a*mianownik2;
                        licznik2+=a;
                    }
                }
                int d,e;
                if(mianownik1>mianownik2){
                    c = mianownik1/mianownik2;
                    b = mianownik2*c;
                    c=c-b;
                    if(c==0){
                        b=mianownik2;
                        d = mianownik1/mianownik2;
                        licznik1 = licznik1 * d;
                    }
                    else{
                        b=mianownik1*mianownik2;
                        licznik2 = licznik2 * mianownik1;
                        licznik1 = licznik1 * mianownik2;
                    }
                }
                else{
                    if(mianownik2%mianownik1==0){
                        b=mianownik2;
                        d = mianownik2/mianownik1;
                        licznik1 = licznik1 * d;
                    }
                    else{
                        b=mianownik1*mianownik2;
                        licznik2 = licznik2 * mianownik1;
                        licznik1 = licznik1 * mianownik2;
                    }
                }
                int wynik = 0, wynik2;
                if(znak.getText().toString().equals("+")){
                    wynik = licznik1+licznik2;
                }
                else if(znak.getText().toString().equals("-")){
                    wynik = licznik1-licznik2;
                }
                else if(znak.getText().toString().equals("*")){
                    b=b*b;
                    wynik = licznik1*licznik2;
                }
                else if(znak.getText().toString().equals("/")){
                    int temp;
                    temp = licznik2;
                    licznik2 = b;
                    b=b*temp;
                    wynik = licznik1*licznik2;
                }
                wynik2 = wynik%b;
                e=b;
                if(wynik2==0){
                    licznikWynik.setText("");
                    mianownikWynik.setText("");
                    View kreska = view.findViewById(R.id.kreskaTrzeci);
                    kreska.setBackgroundColor(Color.parseColor("#2B2A2A"));
                }
                else {
                    if(b%wynik2==0){
                        b=b/wynik2;
                        wynik2=1;
                    }
                    else if((b%2==0)&&(wynik2%2==0)){
                        b=b/2;
                        wynik2=wynik2/2;
                    }
                    else if((b%3==0)&&(wynik2%3==0)){
                        b=b/3;
                        wynik2=wynik2/3;
                    }
                    else if((b%5==0)&&(wynik2%5==0)){
                        b=b/5;
                        wynik2=wynik2/5;
                    }
                    else if((b%7==0)&&(wynik2%7==0)){
                        b=b/7;
                        wynik2=wynik2/7;
                    }
                    else if((b%11==0)&&(wynik2%11==0)){
                        b=b/11;
                        wynik2=wynik2/11;
                    }
                    else if((b%13==0)&&(wynik2%13==0)){
                        b=b/13;
                        wynik2=wynik2/13;
                    }
                    licznikWynik.setText(""+wynik2);
                    mianownikWynik.setText(""+b);
                    View kreska = view.findViewById(R.id.kreskaTrzeci);
                    kreska.setBackgroundColor(Color.WHITE);
                }
                wynik2 = wynik / e;
                if(wynik2!=0){
                    duzyWynik.setText(""+wynik2);
                }
                else{
                    duzyWynik.setText("");
                }
                if((licznikWynik.getText().toString().equals(""))&&(mianownikWynik.getText().toString().equals(""))&&(duzyWynik.getText().toString().equals(""))){
                    duzyWynik.setText("0");
                }
            }
        });
        return view;
    }
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Wybierz działanie");
        getActivity().getMenuInflater().inflate(R.menu.dzialania_kalkulator,menu);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.dodawanie:
                znak.setText("+");
                break;
            case R.id.odejmowanie:
                znak.setText("-");
                break;
            case R.id.mnozenie:
                znak.setText("*");
                break;
            case R.id.dzielenie:
                znak.setText("/");
                break;
        }
        return true;
    }
}
