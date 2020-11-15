package com.example.expen;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.opencsv.CSVReader;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class ScanBarcodeActivity extends AppCompatActivity  {
    private Button scanBtn, addtocartBtn;
    private TextView contentTxt, name, price;
    private String namelookup;
    private String pricelookup;

    ArrayList<String> storedContent = new ArrayList<String>();
    ArrayList<String> contentArray = new ArrayList<String>();
    ArrayList<String> barcodeData = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);
        scanBtn = findViewById(R.id.scan_button);
        addtocartBtn = findViewById(R.id.add_to_cart_button);
        contentTxt = findViewById(R.id.scan_content);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        ArrayList<String> storedContent = new ArrayList<String>();
        readData();
        scanBtn.setOnClickListener(this::onClick);
        addtocartBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //add to cart command later
            }
        });

    }
    public void onClick(View v){
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }
    private void readData() {
        InputStream is = getResources().openRawResource(R.raw.barcodedata);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

    }

    


    @SuppressLint("SetTextI18n")
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            barcodeData = storedContent;
            contentTxt.setText("Barcode: " + scanContent);


            String searchText = scanContent;
            for (String each : barcodeData) {
                if (each.matches(searchText)) {
                    contentArray.add(each);
                }
            }
            namelookup = contentArray.get(0);
            pricelookup = contentArray.get(0);
            String productname = namelookup;
            String productprice = pricelookup;

            if (namelookup != null) {
                name.setText("Product Name:"+ productname);
                price.setText("Price: $" + productprice );
            } else { name.setText("Product Not Found");
                price.setText("Product Not Found");}


        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}