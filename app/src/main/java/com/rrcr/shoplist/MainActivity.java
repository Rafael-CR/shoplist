package com.rrcr.shoplist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.rrcr.shoplist.app.scanner.ui.ScanCodeScreen;
import com.rrcr.shoplist.scanner.ScannerBarcode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initValues();
    }

    private void initValues() {
        MaterialButton escanearCodigo = findViewById(R.id.btnEscanearProducto);
        escanearCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScanCodeScreen.class));
            }
        });
    }
}
