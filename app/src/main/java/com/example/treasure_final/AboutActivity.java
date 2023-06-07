package com.example.treasure_final;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.treasure_final.databinding.AboutBinding;

public class AboutActivity extends AppCompatActivity {
    AboutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}

