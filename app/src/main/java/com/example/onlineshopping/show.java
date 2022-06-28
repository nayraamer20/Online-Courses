package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class show extends AppCompatActivity {
    TextView Address;
    TextView totalprice;
    Button submit;
    DB db;
    String currentDate;
    int finalprice;
    String add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        Address=findViewById(R.id.adrs);
        totalprice=findViewById(R.id.price);
        submit=findViewById(R.id.smt);
        db=new DB(this);
         add=getIntent().getExtras().getString("Address");
        Address.setText(add.toString());
        finalprice=0;
        for(int i=0;i<class_dbFn.cartItems.size();i++)
        {
            finalprice+=class_dbFn.cartItems.get(i).TotalPrice;
        }
        totalprice.setText(String.valueOf(finalprice+"L.E"));
        int customerid=(int) getIntent().getExtras().getInt("customer_define",0);
        db.SaveOrder(add,currentDate,customerid);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(show.this,Finish.class);
                startActivity(intent);
            }
        });

    }
}