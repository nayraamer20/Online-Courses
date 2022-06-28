package com.example.onlineshopping;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ProjectHolder>  {

    ArrayList<class_orderDetails> items;
    @Override
    public ProjectHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cartdesign,parent,false);
        CartAdapter.ProjectHolder projectHolder=new CartAdapter.ProjectHolder(view);
        return projectHolder;
    }
    public CartAdapter(ArrayList<class_orderDetails> cartitems) {
        this.items = cartitems;
    }
    public class ProjectHolder  extends  RecyclerView.ViewHolder{
        public TextView Name;
        public TextView price;
        public TextView totalprice;
        EditText edit;
        Button Edit,Remove;
        public ProjectHolder(View itemView)
        {
            super(itemView);
            Name=itemView.findViewById(R.id.productname);
            price=itemView.findViewById(R.id.productprice);
            totalprice=itemView.findViewById(R.id.totalprice);
            Edit=itemView.findViewById(R.id.edit);
            Remove=itemView.findViewById(R.id.remove);
            edit=itemView.findViewById(R.id.Edit);
            ProjectHolder holder=this;
            Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getBindingAdapterPosition();
                    class_orderDetails orderDetails=items.get(pos);
                    items.get(pos).Quantity= Integer.parseInt(edit.getText().toString());
                    int total_price_final= (int) (items.get(pos).Price*Integer.parseInt(edit.getText().toString()));
                    items.get(pos).TotalPrice=total_price_final;
                    notifyItemRangeChanged(pos, items.size());

                }
            });
            Remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Name.setText(" ");
                   price.setText(" ");
                   totalprice.setText(" ");
                   edit.setText(" ");
                }
            });
        }
    }
    @Override
    public void onBindViewHolder(@NonNull ProjectHolder holder, int position) {
        class_orderDetails orderDetails=items.get(position);
        holder.Name.setText(orderDetails.ProName);
        holder.price.setText(String.valueOf((int) orderDetails.Price));
        holder.totalprice.setText(String.valueOf( orderDetails.TotalPrice));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}