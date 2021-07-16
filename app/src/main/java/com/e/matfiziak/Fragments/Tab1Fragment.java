package com.e.matfiziak.Fragments;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.e.matfiziak.inne.FunkcjePrzelicznikowe;
import com.e.matfiziak.R;

public class Tab1Fragment extends Fragment {
    private Button btnNavFrag1;
    TextView temperatura1;
    TextView temperatura2;
    TextView powierzchnia1;
    TextView tekst;
    TextView tekst2;
    TextView dlugosc1;
    TextView dlugosc2;
    TextView powierzchnia2;
    TextView objetosc1;
    TextView objetosc2;
    TextView czas2;
    TextView czas1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment1_layout, container, false);
        ScrollView scrollView = view.findViewById(R.id.rellayoutMiddle);
        AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        tekst = view.findViewById(R.id.masa1);
        tekst2 = view.findViewById(R.id.masa2);
        Button buttonMasa = view.findViewById(R.id.buttonMasa);
        final EditText masa1 = view.findViewById(R.id.masa1Pole);
        final FunkcjePrzelicznikowe funkcjePrzelicznikowe = new FunkcjePrzelicznikowe();
        final TextView wynikMasa = view.findViewById(R.id.masa2Pole);
        try {
            tekst2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerForContextMenu(tekst2);
                    ktory = "drugi";
                    v.showContextMenu();

                }
            });
            tekst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ktory = "pierwszy";
                    registerForContextMenu(tekst);
                    v.showContextMenu();
                }
            });
            buttonMasa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String a = masa1.getText().toString();
                    Double masa1Int = 0.0;
                    masa1Int = Double.parseDouble(a);
                    Double c = 0.0;
                    if (co3.equals("Miligram")) {
                        c=masa1Int/1000;
                    } else if (co3.equals("Gram")) {
                        c = masa1Int;
                    } else if (co3.equals("Dekagram")) {
                        c = masa1Int*10;
                    } else if (co3.equals("Kilogram")) {
                        c = masa1Int*1000;
                    } else if (co3.equals("Tona")) {
                        c = masa1Int*1000000;
                    }
                    if (co2.equals("Miligram")) {
                        c=c*1000;
                    } else if (co2.equals("Dekagram")) {
                        c=c/10;
                    } else if (co2.equals("Kilogram")) {
                        c=c/1000;
                    } else if (co2.equals("Tona")) {
                        c=c/1000000;
                    }
                    String x = funkcjePrzelicznikowe.intowanie(c);
                    wynikMasa.setText(x);
                }
            });
        } catch (Exception ex) {
            Log.i("wynik", ex.getMessage().toString());
        }
        dlugosc1 = view.findViewById(R.id.dlugosc1);
        dlugosc2 = view.findViewById(R.id.dlugosc2);
        Button buttonDlugosc = view.findViewById(R.id.buttonDlugosc);
        final EditText dlugosc1Pole = view.findViewById(R.id.dlugosc1Pole);
        final TextView wynikDlugosc = view.findViewById(R.id.dlugosc2Pole);
        try {
            dlugosc1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerForContextMenu(dlugosc1);
                    ktory = "pierwszyDlugosc";
                    v.showContextMenu();
                }
            });
            dlugosc2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerForContextMenu(dlugosc2);
                    ktory = "drugiDlugosc";
                    v.showContextMenu();
                }
            });
            buttonDlugosc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Double b = 0.0;
                        Double c = 0.0;
                        String a = dlugosc1Pole.getText().toString();
                        Double dlugosc1Int = Double.parseDouble(a);
                        if (co03.equals("Milimetr")) {
                            b = dlugosc1Int/1000;
                        } else if (co03.equals("Centymetr")) {
                            b = dlugosc1Int/100;
                        } else if (co03.equals("Decymetr")) {
                            b = dlugosc1Int/10;
                        } else if (co03.equals("Metr")) {
                            b = dlugosc1Int;
                        } else if (co03.equals("Kilometr")) {
                            b = dlugosc1Int*1000;
                        } else if (co03.equals("Mila")) {
                            b = dlugosc1Int*1609.3;
                        }
                        if (co02.equals("Milimetr")) {
                            b = b*1000;
                        } else if (co02.equals("Centymetr")) {
                            b = b*100;
                        } else if (co02.equals("Decymetr")) {
                            b = b*10;
                        } else if (co02.equals("Kilometr")) {
                            b=b/1000;
                        } else if (co02.equals("Mila")) {
                            b=b/1609.3;
                        }
                        String x = funkcjePrzelicznikowe.intowanie(b);
                        wynikDlugosc.setText(x);
                    }
                    catch (Exception ex) {

                    }
                }
            });
        } catch (Exception ex) {
            Log.i("wynik", ex.getMessage().toString());
        }
        powierzchnia1 = view.findViewById(R.id.powierzchnia1);
        powierzchnia2 = view.findViewById(R.id.powierzchnia2);
        Button buttonPowierzchnia = view.findViewById(R.id.buttonPowierzchnia);
        final EditText powierzchnia1Pole = view.findViewById(R.id.powierzchnia1Pole);
        final TextView wynikPowierzchnia = view.findViewById(R.id.powierzchnia2Pole);
        try {
            powierzchnia1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerForContextMenu(powierzchnia1);
                    ktory = "pierwszyPowierzchnia";
                    v.showContextMenu();
                }
            });
            powierzchnia2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerForContextMenu(powierzchnia2);
                    ktory = "drugiPowierzchnia";
                    v.showContextMenu();
                }
            });
            buttonPowierzchnia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Double b = 0.0;
                        Double c = 0.0;
                        String a = powierzchnia1Pole.getText().toString();
                        Double dlugosc1Int = Double.parseDouble(a);
                        if (co05.equals("Milimetr")) {
                            b = dlugosc1Int/1000000;
                        } else if (co05.equals("Centymetr")) {
                            b = dlugosc1Int/10000;
                        } else if (co05.equals("Decymetr")) {
                            b = dlugosc1Int/100;
                        } else if (co05.equals("Metr")) {
                            b = dlugosc1Int;
                        } else if (co05.equals("Ar")) {
                            b = dlugosc1Int*100;
                        } else if (co05.equals("Hektar")) {
                            b = dlugosc1Int*10000;
                        } else if (co05.equals("Kilometr")) {
                            b = dlugosc1Int*1000000;
                        } else if (co05.equals("Mila")) {
                            b = dlugosc1Int*2589846.5;
                        }
                        if (co04.equals("Milimetr")) {
                            b=b*1000000;
                        } else if (co04.equals("Centymetr")) {
                            b=b*10000;
                        } else if (co04.equals("Decymetr")) {
                            b=b*100;
                        } else if (co04.equals("Ar")) {
                            b=b/100;
                        } else if (co04.equals("Hektar")) {
                            b=b/10000;
                        } else if (co04.equals("Kilometr")) {
                            b=b/1000000;
                        } else if (co04.equals("Mila")) {
                            b=b/2589846.5;
                        }
                        String x = funkcjePrzelicznikowe.intowanie(b);
                        wynikPowierzchnia.setText(x);
                    }
                    catch (Exception ex) {
                        Log.i("wynik", ex.getMessage().toString());
                    }

                }
            });
        } catch (Exception ex) {
            Log.i("wynik", ex.getMessage().toString());
        }
        objetosc1 = view.findViewById(R.id.objetosc1);
        objetosc2 = view.findViewById(R.id.objetosc2);
        Button buttonObjetosc = view.findViewById(R.id.buttonObjetosc);
        final EditText objetosc1Pole = view.findViewById(R.id.objetosc1Pole);
        final TextView wynikObjetosc = view.findViewById(R.id.objetosc2Pole);
        try {
            objetosc1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerForContextMenu(objetosc1);
                    ktory = "pierwszyObjetosc";
                    v.showContextMenu();
                }
            });
            objetosc2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerForContextMenu(objetosc2);
                    ktory = "drugiObjetosc";
                    v.showContextMenu();
                }
            });
            buttonObjetosc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Double b = 0.0;
                        Double c = 0.0;
                        String a = objetosc1Pole.getText().toString();
                        Double dlugosc1Int = Double.parseDouble(a);
                        if (co07.equals("Milimetr")) {
                            b = dlugosc1Int/1000000000;
                        } else if (co07.equals("Centymetr")) {
                            b = dlugosc1Int/1000000;
                        } else if (co07.equals("Decymetr")) {
                            b = dlugosc1Int/1000;
                        } else if (co07.equals("Metr")) {
                            b = dlugosc1Int;
                        } else if (co07.equals("Kilometr")) {
                            b = dlugosc1Int*1000000000;
                        } else if (co07.equals("ft")) {
                            b = dlugosc1Int*35.3;
                        }
                        if (co06.equals("Milimetr")) {
                            b=b*1000000000;
                        } else if (co06.equals("Centymetr")) {
                            b=b*1000000;
                        } else if (co06.equals("Decymetr")) {
                            b=b*1000;
                        } else if (co06.equals("ft")) {
                            b=b/35.3;
                        } else if (co06.equals("Kilometr")) {
                            b=b/1000000000;
                        }
                        String x = funkcjePrzelicznikowe.intowanie(b);
                        wynikObjetosc.setText(x);
                    }
                    catch (Exception ex) {
                        Log.i("wynik", ex.getMessage().toString());
                    }

                }
            });
        } catch (Exception ex) {
            Log.i("wynik", ex.getMessage().toString());
        }
        temperatura1 = view.findViewById(R.id.temperatura1);
        temperatura2 = view.findViewById(R.id.temperatura2);
        Button buttonTemperatura = view.findViewById(R.id.buttonTemperatura);
        final EditText temperatura1Pole = view.findViewById(R.id.temperatura1Pole);
        final TextView wynikTemperatura = view.findViewById(R.id.temperatura2Pole);
        try {
            temperatura1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerForContextMenu(temperatura1);
                    ktory = "pierwszyTemperatura";
                    v.showContextMenu();
                }
            });
            temperatura2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerForContextMenu(temperatura2);
                    ktory = "drugiTemperatura";
                    v.showContextMenu();
                }
            });
            buttonTemperatura.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Double b = 0.0;
                        Double c = 0.0, d, e = null;
                        String a = temperatura1Pole.getText().toString();
                        Double dlugosc1Int = Double.parseDouble(a);
                        if (co09.equals("Celsjusz")) {
                            if (co08.equals("Celsjusz")) {
                                e = dlugosc1Int;
                            } else if (co08.equals("Fahrenheit")) {
                                e = dlugosc1Int * 9 / 5 + 32;
                            } else if (co08.equals("Kalwin")) {
                                e = dlugosc1Int + 273.15;
                            }
                        } else if (co09.equals("Fahrenheit")) {
                            if (co08.equals("Celsjusz")) {
                                e = (dlugosc1Int - 32) * 5 / 9;
                            } else if (co08.equals("Fahrenheit")) {
                                e = dlugosc1Int;
                            } else if (co08.equals("Kalwin")) {
                                e = (dlugosc1Int - 32) * 5 / 9;
                                e = e + 273.15;
                            }
                        } else if (co09.equals("Kalwin")) {
                            if (co08.equals("Celsjusz")) {
                                e = dlugosc1Int - 273.15;
                            } else if (co08.equals("Fahrenheit")) {
                                e = dlugosc1Int - 273.15;
                                e = e * 9 / 5 + 32;
                            } else if (co08.equals("Kalwin")) {
                                e = dlugosc1Int;
                            }
                        }
                        String x = funkcjePrzelicznikowe.intowanie(e);
                        wynikTemperatura.setText(x);
                    }
                    catch (Exception ex) {
                        Log.i("wynik", ex.getMessage().toString());
                    }
                }
            });
        } catch (Exception ex) {
            Log.i("wynik", ex.getMessage().toString());
        }
        czas1 = view.findViewById(R.id.czas1);
        czas2 = view.findViewById(R.id.czas2);
        Button buttonCzas = view.findViewById(R.id.buttonCzas);
        final EditText czas1Pole = view.findViewById(R.id.czas1Pole);
        final TextView wynikCzas = view.findViewById(R.id.czas2Pole);
        try {
            czas1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerForContextMenu(czas1);
                    ktory = "pierwszyCzas";
                    v.showContextMenu();
                }
            });
            czas2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerForContextMenu(czas2);
                    ktory = "drugiCzas";
                    v.showContextMenu();
                }
            });
            buttonCzas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        wynikCzas.setText("");
                        int ab = 0;
                        Double b = 0.0;
                        Double c = 0.0, d, e = null;
                        String a = czas1Pole.getText().toString();
                        Double dlugosc1Int = Double.parseDouble(a);
                        if (co11.equals("Milisekunda")) {
                            b = dlugosc1Int/60000;
                        } else if (co11.equals("Sekunda")) {
                            b = dlugosc1Int/60;
                        } else if (co11.equals("Minuta")) {
                            b = dlugosc1Int;
                        } else if (co11.equals("Godzina")) {
                            b = dlugosc1Int*60;
                        } else if (co11.equals("Doba")) {
                            b = dlugosc1Int*1440;
                        }
                        if (co12.equals("Milisekunda")) {
                            b = b*60000;
                        }
                        else if(co12.equals("Sekunda")){
                            b=b*60;
                        }
                        else if(co12.equals("Godzina")){
                            b=b/60;
                        }
                        else if (co12.equals("Doba")) {
                            b=b/1440;
                        }
                        String x = funkcjePrzelicznikowe.intowanie(b);
                        wynikCzas.setText(x);
                    } catch (Exception ex) {
                        Log.i("wynik", ex.getMessage().toString());
                    }

                }
            });
        } catch (Exception ex) {
            Log.i("wynik", ex.getMessage().toString());
        }
        return view;
    }
    public void zmiana(String co){
        if(ktory.equals("pierwszy")){
            tekst.setText(co);
            co3=co;
        }
        else if(ktory.equals("drugi")){
            tekst2.setText(co);
            co2=co;
        }
        else if(ktory.equals("pierwszyDlugosc")){
            dlugosc1.setText(co);
            co03 = co;
        }
        else if(ktory.equals("drugiDlugosc")){
            dlugosc2.setText(co);
            co02=co;
        }
        else if(ktory.equals("pierwszyPowierzchnia")){
            co = co.substring(0,co.length()-1);
            co05=co;
            if((!co.equals("Ar"))&&(!co.equals("Hektar"))){
                powierzchnia1.setText(Html.fromHtml(co+"<sup><small>2</small></sup>"));
            }
            else {
                powierzchnia1.setText(co);
            }
        }
        else if(ktory.equals("drugiPowierzchnia")){
            co = co.substring(0, co.length() - 1);
            co04 = co;
            if((!co.equals("Ar"))&&(!co.equals("Hektar"))){
                powierzchnia2.setText(Html.fromHtml(co+"<sup><small>2</small></sup>"));
            }
            else {
                powierzchnia2.setText(co);
            }
        }
        else if(ktory.equals("pierwszyObjetosc")){
            co=co.substring(0,co.length()-2);
            co07=co;
            objetosc1.setText(Html.fromHtml(co+"<sup><small>3</small></sup>"));
        }
        else if(ktory.equals("drugiObjetosc")){
            co=co.substring(0,co.length()-2);
            co06=co;
            objetosc2.setText(Html.fromHtml(co+"<sup><small>3</small></sup>"));
        }
        else if(ktory.equals("pierwszyTemperatura")){
            co09=co;
            temperatura1.setText(co);
        }
        else if(ktory.equals("drugiTemperatura")){
            co08=co;
            temperatura2.setText(co);
        }
        else if(ktory.equals("pierwszyCzas")){
            co11=co;
            czas1.setText(co);
        }
        else if(ktory.equals("drugiCzas")){
            co12=co;
            czas2.setText(co);
        }
    }
    String coDlugosc1, coDlugosc2;
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Wybierz jednostkę");
        if(v.getId()==R.id.masa1||v.getId()==R.id.masa2){
            getActivity().getMenuInflater().inflate(R.menu.example_menu, menu);
        }
        else if (v.getId()==R.id.dlugosc1||v.getId()==R.id.dlugosc2){
            getActivity().getMenuInflater().inflate(R.menu.dlugosc_menu,menu);
        }
        else if(v.getId()==R.id.powierzchnia1||v.getId()==R.id.powierzchnia2){
            getActivity().getMenuInflater().inflate(R.menu.powierzchnia_menu,menu);
        }
        else if(v.getId()==R.id.objetosc1||v.getId()==R.id.objetosc2){
            getActivity().getMenuInflater().inflate(R.menu.example2_menu,menu);
        }
        else if (v.getId()==R.id.temperatura1||v.getId()==R.id.temperatura2){
            getActivity().getMenuInflater().inflate(R.menu.czas_menu,menu);
        }
        else if(v.getId()==R.id.czas1||v.getId()==R.id.czas2){
            getActivity().getMenuInflater().inflate(R.menu.temperatura_menu,menu);
        }
    }
    String ktory;
    String co,co2,co3,co0,co01,co02,co03,co04,co05,co06,co07,co08,co09,co11,co12;
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.option2:
                co="Gram";
                zmiana(co);
                return true;
            case R.id.option3:
                co="Dekagram";
                zmiana(co);
                return true;
            case R.id.option4:
                co="Kilogram";
                zmiana(co);
                return true;
            case R.id.option5:
                co="Tona";
                zmiana(co);
                return true;
            case R.id.Milimetr:
                co0="Milimetr";
                zmiana(co0);
                return true;
            case R.id.Centymetr:
                co0="Centymetr";
                zmiana(co0);
                return true;
            case R.id.Decymetr:
                co0="Decymetr";
                zmiana(co0);
                return true;
            case R.id.Metr:
                co0="Metr";
                zmiana(co0);
                return true;
            case R.id.Kilometr:
                co0="Kilometr";
                zmiana(co0);
                return true;
            case R.id.Mila:
                co0="Mila";
                zmiana(co0);
                return true;
            case R.id.MilimetrKwadrat:
                co0="MilimetrK";
                zmiana(co0);
                return true;
            case R.id.CentymetrKwadrat:
                co0="CentymetrK";
                zmiana(co0);
                return true;
            case R.id.DecymetrKwadrat:
                co0="DecymetrK";
                zmiana(co0);
                return true;
            case R.id.MetrKwadrat:
                co0="MetrK";
                zmiana(co0);
                return true;
            case R.id.Ar:
                co0="Ary";
                zmiana(co0);
                return true;
            case R.id.Hektar:
                co0="Hektary";
                zmiana(co0);
                return true;
            case R.id.KilometrKwadrat:
                co0="KilometrK";
                zmiana(co0);
                return true;
            case R.id.MilaKwadrat:
                co0="MilaK";
                zmiana(co0);
                return true;
            case R.id.milimetrSzescian:
                co0="MilimetrSz";
                zmiana(co0);
                return true;
            case R.id.centymetrSzescian:
                co0="CentymetrSz";
                zmiana(co0);
                return true;
            case R.id.decymetrSzescian:
                co0="DecymetrSz";
                zmiana(co0);
                return true;
            case R.id.metrSzescian:
                co0="MetrSz";
                zmiana(co0);
                return true;
            case R.id.ftSzescian:
                co0="ftSz";
                zmiana(co0);
                return true;
            case R.id.kilometrSzescian:
                co0="KilometrSz";
                zmiana(co0);
                return true;
            case R.id.celsjusz:
                co0="Celsjusz";
                zmiana(co0);
                return true;
            case R.id.fahrenheit:
                co0="Fahrenheit";
                zmiana(co0);
                return true;
            case R.id.kalwin:
                co0="Kalwin";
                zmiana(co0);
                return true;
            case R.id.milisekunda:
                co0="Milisekunda";
                zmiana(co0);
                return true;
            case R.id.sekunda:
                co0="Sekunda";
                zmiana(co0);
                return true;
            case R.id.minuta:
                co0="Minuta";
                zmiana(co0);
                return true;
            case R.id.godzina:
                co0="Godzina";
                zmiana(co0);
                return true;
            case R.id.doba:
                co0="Doba";
                zmiana(co0);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}