package com.example.burin.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.renderscript.Sampler;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class EditTextDialog {

    public ArrayList showdialog(Context context, String Title, String Message){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialog.setView(dialogView);
        dialog.setTitle(Title);
        dialog.setMessage(Message);
        final ArrayList<Integer> x = new ArrayList<>();
        x.clear();
        final EditText editText = (EditText)dialogView.findViewById(R.id.custom_dialog_edittext) ;
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        dialog.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Integer a = Integer.valueOf(editText.getText().toString());
                x.add(a);
            }
        });

        dialog.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog abc = dialog.create();
        abc.show();
        return x;
    }
}
