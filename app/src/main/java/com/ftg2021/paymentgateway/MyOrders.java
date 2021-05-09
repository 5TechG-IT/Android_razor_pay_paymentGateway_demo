package com.ftg2021.paymentgateway;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrders extends AppCompatActivity {

    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        fetchOrders();
    }

    //fetching all categories

    private void fetchOrders()
    {
        String data="";


        try {

            final String savedata = data;

            String URL = "https://api.razorpay.com/v1/orders";

            requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try
                    {
                        //   Toast.makeText(getContext(),"Fetching cats data", Toast.LENGTH_SHORT).show();

                        JSONObject jsonObject = new JSONObject(response);

                        JSONArray jsonArray = jsonObject.getJSONArray("items");


                        //JSONArray jsonArray =new  JSONArray(response["data"]);

                        Log.i("CATS","NO DATA FOUND");






                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject object = jsonArray.getJSONObject(i);

                            int orderId      = object.getInt("id");


                            Toast.makeText(getApplicationContext(), "id:"+orderId, Toast.LENGTH_SHORT).show();

                        }







                    } catch (Exception e) {
                           Toast.makeText(getApplicationContext(), "Some Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                           Log.d("status", e.getMessage());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                       Toast.makeText(getApplicationContext(), "Error1: " + error.toString(), Toast.LENGTH_SHORT).show();
                       Log.i("status",error.toString());

                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }



                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return savedata == null ? null : savedata.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        //Log.v("Unsupported Encoding while trying to get the bytes", data);
                        return null;
                    }
                }

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    HashMap<String, String> headers = new HashMap<>();

                    String credentials = "rzp_test_qKpDKcmzdSrLmT" + ":" + "Tb4dbmTFnUiq804xwyud21Rn";
                    String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

                    headers.put("User-Agent","Bing Search Client for Android");
                    headers.put("Content-Type", "application/json");
           //         headers.put("Authorization", "Basic " + base64EncodedCredentials);

                    headers.put("Authorization", "Basic cnpwX3Rlc3RfcUtwREtjbXpkU3JMbVQ6VGI0ZGJtVEZuVWlxODA0eHd5dWQyMVJu");
                    return params;
                }

                //Pass Your Parameters here
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> params = new HashMap<String, String>();

                    String credentials = "rzp_test_qKpDKcmzdSrLmT" + ":" + "Tb4dbmTFnUiq804xwyud21Rn";
                    String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("User-Agent","Bing Search Client for Android");
                    headers.put("Content-Type", "application/json");
                   // headers.put("Authorization", "Basic " + base64EncodedCredentials);
                    headers.put("Authorization", "Basic cnpwX3Rlc3RfcUtwREtjbXpkU3JMbVQ6VGI0ZGJtVEZuVWlxODA0eHd5dWQyMVJu");

                    return params;
                }
            };
            requestQueue.add(stringRequest);

        }catch (Exception e){
              Log.d("ERROR",e.getMessage());
              Toast.makeText(getApplicationContext(), "Error4: " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}