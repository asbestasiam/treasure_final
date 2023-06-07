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

import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

    public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder>{
    private Context context;
    private List<ProductModel> productModelList;

    public ProductAdapter(Context context) {
        this.context = context;
        productModelList=new ArrayList<>();
    }
    public void addproduct(ProductModel productModel){
        productModelList.add(productModel);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductModel productModel=productModelList.get(position);
        try {
            holder.tittle.setText(ProductModel.getTitle().toString());
        }

        catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        try
        {
            holder.desc.setText(productModel.getDescription().toString());
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        try {
            holder.price.setText(productModel.getPrice().toString());
        }
        catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        Glide.with(context).load(productModel.getImage())
                .into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra("model",productModel);
                context.startActivities(new Intent[]{intent});
            }
        });
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tittle,desc,price;
        private ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tittle=itemView.findViewById(R.id.tittle);
            desc=itemView.findViewById(R.id.description);
            price=itemView.findViewById(R.id.price);
            img=itemView.findViewById(R.id.image);

        }
    }

}
