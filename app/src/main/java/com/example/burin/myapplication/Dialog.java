package com.example.burin.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Dialog {

    public void alert(Context context,String Title, String Message){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.dialog_alert, null);
        dialog.setView(dialogView);

        TextView textView = (TextView)dialogView.findViewById(R.id.dialog_textview);
        CardView cardView = (CardView)dialogView.findViewById(R.id.dialog_cardview);

        textView.setText(Message);

        //dialog.setTitle(Title);
        //dialog.setMessage(Message);
        /*dialog.setPositiveButton("รับทราบ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


*/
        final AlertDialog abc = dialog.create();
        abc.show();

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abc.cancel();
            }
        });


    }

    public void pro(Context context){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.dialog_progress, null);
        dialog.setView(dialogView);

        //dialog.setTitle(Title);
        //dialog.setMessage(Message);
        /*dialog.setPositiveButton("รับทราบ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


*/
        final AlertDialog abc = dialog.create();
        abc.show();


    }

}
