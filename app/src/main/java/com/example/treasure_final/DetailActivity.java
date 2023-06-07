package com.example.treasure_final;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.treasure_final.databinding.ActivityDetailBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.treasure_final.Payment;


import java.util.UUID;

public class DetailActivity extends AppCompatActivity {


        ActivityDetailBinding binding;
    private ProductModel productModel;
    private Button paymentButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        productModel = (ProductModel) intent.getSerializableExtra("model");

        binding.Title.setText(productModel.getTitle());
        binding.description.setText(productModel.getDescription());
        binding.price.setText(productModel.getPrice());
        Glide.with(binding.getRoot())
                .load(productModel.getImage())
                .into(binding.imageView);
        binding.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheet();
                //       addToCart();
            }
        });



        //On CLick Payment Button
        paymentButton = findViewById(R.id.paybtn);
        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Payment payment = new Payment(DetailActivity.this);
                payment.startPayment();
            }
        });
    }


    private void showBottomSheet() {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(DetailActivity.this).inflate(R.layout.bottom_layout, (LinearLayout) findViewById(R.id.main_layout), false);
        bottomSheetDialog.setContentView(view);
        EditText qty = view.findViewById(R.id.qty);
        Button btn = view.findViewById(R.id.checkDut);
        bottomSheetDialog.show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quantity = qty.getText().toString();
                addToCart(quantity);
                bottomSheetDialog.cancel();
            }
        });
    }

    private void addToCart(String qty) {
        String id = UUID.randomUUID().toString();
        CartModel cartmodel = new CartModel(id, productModel.getTitle(), productModel.getImage(), productModel.getPrice(), qty, FirebaseAuth.getInstance().getUid());
        FirebaseFirestore.getInstance()
                .collection("cart")
                .document(id)
                .set(cartmodel);
        Toast.makeText(this, "Added To Cart", Toast.LENGTH_SHORT).show();

    }


}