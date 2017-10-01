package com.example.agussuhardi.sisteminformasiakprind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Agus Suhardi on 30/9/2017.
 */

/*
activity untuk memuka url register/daftar
 */

public class SendRegisterActivity extends AppCompatActivity {

    //membuat object webView
    WebView openPageRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_register);

        //variable dengan isi url tujuan tanpa parameter
        final String url = "http://bp2ai.akprind.ac.id/bp2ai/app/index.php?";

        //mendapatkan parameter dari aktivity sebelumnya yaitu RegisterActivity
        Intent i = getIntent();
        String param = i.getStringExtra("url");

        openPageRegister = (WebView) findViewById(R.id.webViewOpenPageRegister);

        //url tujuan digabungkan dengan parameter yang telah di dapat dari activity sebelumnya yaitu registerActivity, kemudian di load menggunakan webView
        openPageRegister.loadUrl(url + param);


        //kedua aksi ini digunakan agar aplikasi tidak membuka browser external seperti chrome
        openPageRegister.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                getWindow().setTitle(title); //Set Activity tile to page title.
            }
        });

        openPageRegister.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });


    }
}
