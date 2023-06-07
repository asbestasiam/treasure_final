package com.example.treasure_final;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{
private Context context;
private List<CartModel> productModelList;

public CartAdapter(Context context) {
    this.context = context;
    productModelList=new ArrayList<>();
}
public void addproduct(CartModel productModel){
    productModelList.add(productModel);
    notifyDataSetChanged();
}


@NonNull
@Override
public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_row,parent,false);
    return new MyViewHolder(view);
}
@Override
public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    CartModel productModel=productModelList.get(position);
    try {
        holder.tittle.setText(ProductModel.getTitle().toString());
    }

    catch (NullPointerException ex) {
        ex.printStackTrace();
    }

    try
    {
        holder.qty.setText(productModel.getProductname.toString());
    }
    catch (NullPointerException ex) {
        ex.printStackTrace();
    }
    try {
        holder.price.setText(productModel.getProductPrice().toString());
    }
    catch (NullPointerException ex) {
        ex.printStackTrace();
    }

    Glide.with(context).load(productModel.getProductImage())
            .into(holder.img);


}

@Override
public int getItemCount() {
    return productModelList.size();
}

public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView tittle,qty,price;
    private ImageView img;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tittle=itemView.findViewById(R.id.tittle);
        qty=itemView.findViewById(R.id.qty);
        price=itemView.findViewById(R.id.price);
        img=itemView.findViewById(R.id.image);

    }
}

}
