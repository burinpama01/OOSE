package com.example.burin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_admin);

        ImageView BackRegisAdmin = (ImageView)findViewById(R.id.Back_Register_Admin);
        BackRegisAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final EditText name = (EditText)findViewById(R.id.register_admin_edittext_name);
        final EditText password = (EditText)findViewById(R.id.register_admin_edittext_password);
        final EditText re_password = (EditText)findViewById(R.id.register_admin_edittext_reenter_password);


        Button ButtonRegisterAdminDrome = (Button)findViewById(R.id.Button_Register_Admin_Drome);
        ButtonRegisterAdminDrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((name.getText().toString().length() > 0) || (password.getText().toString().length() > 0) || (re_password.getText().toString().length() > 0)){
                    if(password.getText().toString().contentEquals(re_password.getText().toString())){
                        Intent intent = new Intent(RegisterAdminActivity.this,AddFloorActivity.class);
                        intent.putExtra("NAME",name.getText().toString());
                        intent.putExtra("PASSWORD",password.getText().toString());
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterAdminActivity.this,"รหัสผ่านไม่ตรงกัน",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterAdminActivity.this,"ข้อมูลไม่ครบ",Toast.LENGTH_SHORT).show();
                }




            }
        });
    }

}
