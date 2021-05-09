package com.ftg2021.paymentgateway;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {


    Button pay,orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pay=findViewById(R.id.payButton);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });


        orders=findViewById(R.id.ordersbtn);
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MyOrders.class));
            }
        });
    }

    public void startPayment() {


        Checkout checkout=new Checkout();
        checkout.setKeyID("rzp_test_7VnrHtXzfJDHM5");
        Checkout.preload(getApplicationContext());

        /**
         * Set your logo here
         */
       // checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razor pay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Android Workshop Demo");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "60000");//pass amount in currency subunits
            options.put("prefill.email", "akshayjadhav@gmail.com");
            options.put("prefill.contact","8956895689");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("Error", "Error in starting Razor pay Checkout"+e.getMessage());
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this,"payment success"+s,Toast.LENGTH_SHORT).show();

        try {

            Intent intent=new Intent(MainActivity.this,PaymentStatus.class);
            intent.putExtra("status",1);

            startActivity(intent);

        }catch (Exception e){}

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this,"payment failed",Toast.LENGTH_SHORT).show();

        try {

            Intent intent=new Intent(MainActivity.this,PaymentStatus.class);
            intent.putExtra("status",0);

            startActivity(intent);

        }catch (Exception e){}
    }
}