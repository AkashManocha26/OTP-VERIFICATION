package com.example.otpverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class EnterNumber extends AppCompatActivity {
    private EditText mobile;
    private Button button;
    Button cross;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_number);


        cross = findViewById(R.id.cross_btn);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EnterNumber.this,MainActivity.class );
                startActivity(intent);
            }
        });
        mobile=findViewById(R.id.editText);
        button=findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mobile.getText().toString().trim().isEmpty()){
                    if(mobile.getText().toString().trim().length()==10){

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + mobile.getText().toString(),
                                30,
                                TimeUnit.SECONDS,
                                EnterNumber.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        Toast.makeText(EnterNumber.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        Intent intent= new Intent(EnterNumber.this,EnterOtp.class );
                                        String ph_number=mobile.getText().toString();
                                        intent.putExtra("key_number",ph_number);
                                        intent.putExtra("key_otp",otp);
                                        startActivity(intent);
                                    }
                                }
                        );
                    }
                    else{
                        Toast.makeText(EnterNumber.this,"Please enter correct number", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(EnterNumber.this,"Please enter number", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

}