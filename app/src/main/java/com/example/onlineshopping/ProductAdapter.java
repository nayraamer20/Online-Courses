package com.example.onlineshopping;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProjectHolder> {

   ArrayList<products>myproducts;
   ArrayList<products>products;
   Context context;


    public class ProjectHolder  extends  RecyclerView.ViewHolder{
        public TextView ProductName;
        public TextView Productprice;
        ImageButton imageButton;


        public ProjectHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            ProductName=itemView.findViewById(R.id.name);
            Productprice=itemView.findViewById(R.id.Price);
            imageButton=itemView.findViewById(R.id.image);
             products=new ArrayList<products>();
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                int pos=getBindingAdapterPosition();
                products product=myproducts.get(pos);
                    Intent intent=new Intent(itemView.getContext(),Product_details.class);
                    intent.putExtra("Pro_id",product.getPro_id());
                    intent.putExtra("Pro_Name",product.getProName());
                    intent.putExtra("Pro_Price",product.getPrice());
                    intent.putExtra("Pro_Quantity",product.getPro_quantity());
                    context.startActivity(intent);
                }
            });
        }
    }

    public  ProductAdapter (ArrayList<products>products)
    {
        myproducts=products;
    }
    @NonNull
    @Override
    public ProjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card,parent,false);
    ProjectHolder projectHolder=new ProjectHolder(view);
        return projectHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ProjectHolder holder, int position) {
            products product=myproducts.get(position);
            holder.ProductName.setText(product.getProName());
            holder.Productprice.setText(String.valueOf((int) product.getPrice()));

    }

    @Override
    public int getItemCount() {
        return myproducts.size();
    }

}
