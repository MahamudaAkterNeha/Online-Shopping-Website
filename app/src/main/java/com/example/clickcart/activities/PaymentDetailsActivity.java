package com.example.clickcart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clickcart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PaymentDetailsActivity extends AppCompatActivity {

    EditText name,cardType,mmyy,cardNumber,cvc;
    Toolbar toolbar;
    Button addPayment;


    FirebaseFirestore firestore;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        toolbar = findViewById(R.id.add_payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        name = findViewById(R.id.ad_name);
        cardType = findViewById(R.id.ad_cardType);
        mmyy = findViewById(R.id.ad_mmyy);
        cardNumber = findViewById(R.id.ad_cardNumber);
        cvc = findViewById(R.id.ad_cvc);
        addPayment = findViewById(R.id.add_payment);

        addPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = name.getText().toString();
                String userCardType = cardType.getText().toString();
                String userMmyy = mmyy.getText().toString();
                String userCardNumber = cardNumber.getText().toString();
                String userCvc = cvc.getText().toString();

                String final_details = "";

                if (!userName.isEmpty()) {
                    final_details += userName+"; ";
                }
                if (!userCardType.isEmpty()) {
                    final_details += userCardType+"; ";
                }
                if (!userMmyy.isEmpty()) {
                    final_details += userMmyy+"; ";
                }
                if (!userCardNumber.isEmpty()) {
                    final_details += userCardNumber+"; ";
                }
                if (!userCvc.isEmpty()) {
                    final_details += userCvc;
                }
                if (!userName.isEmpty() && !userCardType.isEmpty() && !userMmyy.isEmpty() && !userCardNumber.isEmpty() && !userCvc.isEmpty()) {

                    Map<String,String> map = new HashMap<>();
                    map.put("userPaymentDetails", final_details);

                    firestore.collection("UserPayment").document(auth.getCurrentUser().getUid()).collection("PaymentDetails").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(PaymentDetailsActivity.this, "Payment Details Added!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(PaymentDetailsActivity.this,PaymentChooseActivity.class));

                            }
                        }
                    });
                } else {

                    Toast.makeText(PaymentDetailsActivity.this, "Kindly Fill Up All The Fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}