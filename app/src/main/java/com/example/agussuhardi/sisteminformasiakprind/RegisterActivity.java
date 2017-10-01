package com.example.agussuhardi.sisteminformasiakprind;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Agus Suhardi on 30/9/2017.
 */

/*
activity untuk proses login dan melakukan pendaftaran. activity ini akan di load pada saat pertama kali aplikasi di jalankan
 */

public class RegisterActivity extends AppCompatActivity {

    //object2 untuk terhubung dengan xml
    Button btnRegister, btnLogin;
    EditText namaLengkap, email, hp, password, editLogin;

    //variable yang digunakan didefinisikan terlebih dahulu
    String sNamaLengkap, sEmail, sHp, sPassword, fullUrl, sLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //memanggil methode yang diperlukan
        //untuk keterangan methode scrol/lihat ke bawah
        connectionXml();
        loadData();

        //aksi ketika button daftar di klick
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sNamaLengkap = namaLengkap.getText().toString().trim();
                sEmail = email.getText().toString().trim();
                sHp = hp.getText().toString().trim();
                sPassword = password.getText().toString().trim();

                if (sNamaLengkap.equals("") || sEmail.equals("") || sHp.equals("") || sPassword.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Data Harus di isi lengkap", Toast.LENGTH_SHORT).show();
                } else {

                    fullUrl = "namalengkap=" + sNamaLengkap + "&email=" + sEmail + "&hp=" + sHp + "&password=" + sPassword;
                    //kirimpakn data menggunaan intent ke activity selanjutnya(SendRegisterActivity)
                    Intent i = new Intent(RegisterActivity.this, SendRegisterActivity.class);
                    i.putExtra("url", fullUrl);
                    startActivity(i);
                }
            }
        });


        //aksi ketika btton Login di click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!editLogin.getText().toString().isEmpty()) {

                    sLogin = editLogin.getText().toString();
                    saveData(); //kegunaan methode saveData silakan di baca dibawah
                    Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                    i.putExtra("url", sLogin);
                    startActivity(i);
                    finish();
                }
            }
        });

    }

    //methode untuk menghubungkan object2 java dengan object2 xml
    void connectionXml() {
        btnRegister = (Button) findViewById(R.id.btnRegister);
        namaLengkap = (EditText) findViewById(R.id.namaLengkap);
        email = (EditText) findViewById(R.id.email);
        hp = (EditText) findViewById(R.id.hp);
        password = (EditText) findViewById(R.id.password);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        editLogin = (EditText) findViewById(R.id.editLogin);
    }


    //methode ini digunakan untuk menyimpan data yang di inputkan ke EditText(unix code) sebagai data permanent
    void saveData() {
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putString("data", sLogin);
        editor.apply();
    }

    //methode untuk meload isi data permanent yang di buat oleh methode saveData
    void loadData() {
        SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
        String loadedString = prefs.getString("data", null);
        editLogin.setText(loadedString);
        if (!editLogin.getText().toString().isEmpty()) {
            autoLoadHomePage();
        }

    }

    //methode ini digunakan untuk login otomatis ketika activity ini di click dengan nilai 0 berarti otomasi jalan dalam waktu 0 melisecond
    void autoLoadHomePage() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnLogin.performClick();
            }
        }, 0);
    }

}
