package com.example.expen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

public class ScanBarcodeActivity extends AppCompatActivity  {
    private Button scanBtn, addtocartBtn;
    private TextView nameTxt, priceTxt, barcode;
    private String name, price, line;
    JSONObject barcodejson = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);
        scanBtn = findViewById(R.id.scan_button);
        barcode = findViewById(R.id.scan_content);
        addtocartBtn = findViewById(R.id.add_to_cart_button);
        nameTxt = findViewById(R.id.name);
        priceTxt = findViewById(R.id.price);
        Log.i("CSV", "in read data method");
        InputStreamReader is = new InputStreamReader(getResources().openRawResource(R.raw.barcodedata)) ;
        BufferedReader reader = new BufferedReader(is);
        try {
            while ((line = reader.readLine()) != null) {
                Log.i("CSV", line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        name = null;
        price = null;
        scanBtn.setOnClickListener(this::onClick);
        addtocartBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //add to cart command later
            }
        });
    }
    private String requestData(String urlstring) {

        try {
            final String[] response = new String[1];
            final CountDownLatch latch = new CountDownLatch(1);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Log.d("START", "Starting GET");
                        URL url = new URL(urlstring);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setConnectTimeout(5000);
                        connection.setReadTimeout(5000);
                        connection.connect();
                        Log.d("INFO", urlstring);
                        Log.d("INFO", Integer.toString(connection.getResponseCode()));
                        Log.d("INFO", connection.getResponseMessage());
                        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String content = "", line;
                        while ((line = rd.readLine()) != null) {
                            content += line + "\n";
                        }
                        response[0] = content;
                        Log.d("SUCCESS", response[0]);
                        latch.countDown();
                    } catch (Exception ex) {
                        Log.d("ERROR", "Error Processing Get Request...");
                        for (int i = 0; i < ex.getStackTrace().length; i++) {
                            Log.d("ERROR", ex.getStackTrace()[i].toString());
                        }
                        latch.countDown();
                    }
                }

            }).start();
            latch.await();
            return response[0];
        } catch (Exception ex) {
            return "";
        }


    }
    public void onClick(View v){
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String barcodelink = requestData("https://api.npoint.io/e538dfc7831909d9491c");
            Log.d("TEXTWTF",barcodelink);
            JSONObject barcodekey = null;
            try {
                barcodejson = new JSONObject(barcodelink);

                Iterator<String> iter = barcodejson.keys();

                String key = new String();
                //This should be the iterator you want.
                while(iter.hasNext()){
                    key = iter.next();
                    barcode.setText("Barcode: " + scanContent);
                    Log.d("scanContent", scanContent);
                    if (scanContent.equals(key)){

                        name = barcodejson.getJSONObject(key).getString("name");
                        Log.d("name", name);
                        price = barcodejson.getJSONObject(key).getString("price");
                        Log.d("price", price);

                    } else {
                        Log.d("key", key);
                    }

                }

                if (name!= null){
                    nameTxt.setText("Product name:" + name);
                    priceTxt.setText("Price: $"+ price);

                }else{
                    nameTxt.setText("Product not found");
                    priceTxt.setText("Price not found");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }




        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
