package com.ftg2021.paymentgateway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PaymentStatus extends AppCompatActivity {

    TextView paymentStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_status);

        paymentStatus=findViewById(R.id.payStatus);


        Intent intent = getIntent();

        int status=intent.getIntExtra("status",0);

        if(status==1)
            paymentStatus.setText("Payment Successful");
        else
            paymentStatus.setText("Payment Failed");
    }
}