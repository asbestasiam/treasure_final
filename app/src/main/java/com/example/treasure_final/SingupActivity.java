package com.example.treasure_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.treasure_final.databinding.ActivitySingupBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SingupActivity extends AppCompatActivity {
    ActivitySingupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySingupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingupActivity.this,MainActivity.class));
                finish();
            }
        });
        binding.singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=binding.name.getText().toString();
                String email=binding.email.getText().toString();
                String password=binding.password.getText().toString();
                createAccount(name,email,password);
            }

            private void createAccount(String name, String email, String password) {
                FirebaseAuth fAuth=FirebaseAuth.getInstance();

                fAuth.createUserWithEmailAndPassword(email.trim(),password.trim())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                UserProfileChangeRequest profileChangeRequest=new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name).build();
                                FirebaseAuth.getInstance().getCurrentUser().updateProfile(profileChangeRequest);
                                Toast.makeText(SingupActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                binding.name.setText("");
                                binding.email.setText("");
                                binding.password.setText("");

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                               
                                Toast.makeText(SingupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }
}