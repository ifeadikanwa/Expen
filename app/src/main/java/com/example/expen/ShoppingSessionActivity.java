package com.example.expen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ShoppingSessionActivity extends AppCompatActivity {
    private FloatingActionButton scanFab;
    private Activity activity;
    private String scannedUPC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_session);

        setupGlobals();
        scanOnFABClicked();
    }

    private void setupGlobals() {
        scanFab = findViewById(R.id.fab_scan);
        activity = this;
    }

    private void scanOnFABClicked() {
        scanFab.setOnClickListener(v -> {
            IntentIntegrator scanIntegrator = new IntentIntegrator(activity);
            scanIntegrator.initiateScan();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        getScanResultAndUtilize(scanResult);
    }

    private void getScanResultAndUtilize(IntentResult scanResult) {
        if (scanResult != null) {
            scannedUPC = scanResult.getContents();

            // TODO: DO SOMETHING WITH SCANNED UPC
            // TODO: AND REMOVE TOASTS
            Toast.makeText(this, "UPC: " + scannedUPC, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "ERR: SCAN FAILED", Toast.LENGTH_LONG).show();
        }
    }
}