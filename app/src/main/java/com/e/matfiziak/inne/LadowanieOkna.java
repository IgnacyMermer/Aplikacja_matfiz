package com.e.matfiziak.inne;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.e.matfiziak.R;

public class LadowanieOkna {
    Activity activity;
    AlertDialog alertDialog;
    public LadowanieOkna(Activity myActivity){
        this.activity = myActivity;
    }
    public void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.scustowany_preloader, null));
        builder.setCancelable(true);
        alertDialog = builder.create();
        alertDialog.show();
    }
    public void dismissDialog(){
        alertDialog.dismiss();;
    }
}
