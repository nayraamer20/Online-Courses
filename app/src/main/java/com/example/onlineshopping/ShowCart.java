package com.example.onlineshopping;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ShowCart extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter MyAdapter;
    private RecyclerView.LayoutManager MyManager;
    Button check;
    Button location;
    EditText address;
    String add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_choose_items);
        recyclerView=findViewById(R.id.cartview);
        check=findViewById(R.id.finish);
        location = findViewById(R.id.getlocation);
        MyManager=new LinearLayoutManager(this);
        MyManager=new LinearLayoutManager(getApplicationContext());
        MyAdapter=new CartAdapter(class_dbFn.cartItems);
        recyclerView.setLayoutManager(MyManager);
        recyclerView.setAdapter(MyAdapter);
        address=findViewById(R.id.address);
         add=address.getText().toString();
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowCart.this, show.class);
                intent.putExtra("Address",address.getText().toString());
                startActivity(intent);
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                String loc = "";
                intent.putExtra("location",loc);
                startActivityForResult(intent,1);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String loc = data.getStringExtra("location");
        address.setText(loc);
    }
}