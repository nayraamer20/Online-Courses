package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Login extends AppCompatActivity {
    EditText name;
    EditText password;
    Button login;
    TextView forgot;
    DB db;
    String currentDate;
    CheckBox r;
    SharedPreferences p;
    class_customer customer;
    com.example.onlineshopping.order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        name=findViewById(R.id.user);
        password=findViewById(R.id.password);
        login=findViewById(R.id.sign_in);
        r=findViewById(R.id.remember);
        forgot=findViewById(R.id.forget);
        p=getSharedPreferences("Saved_Data",MODE_PRIVATE);
        db=new DB(this);
        SharedPreferences.Editor edit= p.edit();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=name.getText().toString();
                String pass=password.getText().toString();
                if(user.equals("")||password.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter the fields",Toast.LENGTH_LONG).show();
                }
                else {
                    boolean check=db.checkUserName_Pass(user,pass);
                    if (check == true) {
                        if (r.isChecked()) {
                            edit.putString("username", user);
                            edit.putString("password", pass);
                            edit.apply();
                            Toast.makeText(getApplicationContext(),"Data about Customer Saved",Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(),Main.class);
                            customer=new class_customer();
                            int id=customer.CustID;
                            order=new order(customer.CustID,customer.CustID,currentDate);
                            Intent i=new Intent(Login.this,Product_details.class);
                            Intent j=new Intent(Login.this, show.class);
                            i.putExtra("customer_define",id);
                            j.putExtra("Order_id",order.OrdID);
                            startActivity(intent);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Invalid Data...Please make Register", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Paassword.class);
                startActivity(intent);
            }
        });

        data();
    }
    private void data() {
        SharedPreferences sp=getSharedPreferences("Saved_Data",MODE_PRIVATE);
        String n =sp.getString("username","");
        name.setText(n);
        String p =sp.getString("password","");
        password.setText(p);

    }
}