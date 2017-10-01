package com.example.agussuhardi.sisteminformasiakprind;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Agus Suhardi on 30/9/2017.
 */

/*
activity utama untuk load
 */

public class MainActivity extends AppCompatActivity {

    //menentukan url tujuan yang disimpan dalam variable
    String loadUrl = "http://jack.akprind.ac.id/edu/index.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WebView webView = (WebView) findViewById(R.id.webView);

        //mengambil data yang dikirim dari aktivity sebelumnya yaitu Register Activity
        //data ini digunakan sebagai parameter
        Intent i = getIntent();
        String id = i.getStringExtra("url");

//load url beserta parameter dalam sebuah webView
        webView.loadUrl(loadUrl + "id=" + id);

//kedua aksi ini digunakan agar aplikasi tidak membuka browser external seperti chrome
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                getWindow().setTitle(title); //Set Activity tile to page title.
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });


    }


    //overaide methode ini digunakan untuk tombol back
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Keluar dari Aplikasi?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }


}
