package com.example.burin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterDromeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_drome);

        Button EnterRegisButton = (Button)findViewById(R.id.Button_Enter_Register_Drome);
        EnterRegisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterDromeActivity.this, Main.class);
                startActivity(intent);
            }
        });

        TextView TextViewRegisterDrome = (TextView)findViewById(R.id.TextView_Register_Drome);
        TextViewRegisterDrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterDromeActivity.this,RegisterAdminActivity.class);
                startActivity(intent);
            }
        });

    }

}
