package com.example.clickcart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.clickcart.R;
import com.example.clickcart.adapters.AddressAdapter;
import com.example.clickcart.adapters.PaymentAdapter;
import com.example.clickcart.models.AddressModel;
import com.example.clickcart.models.PaymentModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PaymentChooseActivity extends AppCompatActivity implements PaymentAdapter.SelectedPayment{

    Button addPayment,makePayment,homeBtn,pickUp;
    RecyclerView recyclerView;
    private List<PaymentModel> paymentModelList;
    private PaymentAdapter paymentAdapter;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    Toolbar toolbar;
    String mPayment = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_choose);

        toolbar = findViewById(R.id.pay_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addPayment = findViewById(R.id.add_payment_details);
        makePayment = findViewById(R.id.make_payment);
        homeBtn = findViewById(R.id.returnHome);
        pickUp = findViewById(R.id.pickUp);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.payment_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        paymentModelList = new ArrayList<>();
        paymentAdapter = new PaymentAdapter(getApplicationContext(),paymentModelList,this);
        recyclerView.setAdapter(paymentAdapter);

        firestore.collection("UserPayment").document(auth.getCurrentUser().getUid()).collection("PaymentDetails").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {

                        PaymentModel paymentModel = doc.toObject(PaymentModel.class);
                        paymentModelList.add(paymentModel);
                        paymentAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        addPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentChooseActivity.this,PaymentDetailsActivity.class));
            }
        });

        makePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentChooseActivity.this, "Payment Done!", Toast.LENGTH_SHORT).show();
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentChooseActivity.this,HomeActivity.class));
            }
        });

        pickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentChooseActivity.this,PickupActivity.class));
            }
        });
    }

    @Override
    public void setPayment(String payment) {
        mPayment = payment;
    }


}