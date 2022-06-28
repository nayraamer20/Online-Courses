package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {
    Button cars,dresses,bags,mobile;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        cars=findViewById(R.id.cars);
        dresses=findViewById(R.id.dress);
        mobile=findViewById(R.id.mobile);
        bags=findViewById(R.id.bags);




        cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(Main.this,ProductShow.class);
                intent.putExtra("Cat_Id",1);
                startActivity(intent);
            }
        });

        dresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(Main.this,ProductShow.class);
                intent.putExtra("Cat_Id",2);
                startActivity(intent);
            }
        });
        bags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(Main.this,ProductShow.class);
                intent.putExtra("Cat_Id",3);
                startActivity(intent);
            }
        });
        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(Main.this,ProductShow.class);
                intent.putExtra("Cat_Id",4);
                startActivity(intent);
            }
        });


    }
}