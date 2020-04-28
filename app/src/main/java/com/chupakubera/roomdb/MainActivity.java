/**
    NIM         : 10118702
    Nama        : Erwin Jelly Barus Tobing
    Kelas       : IF-6k
    Tanggal     : 28/04/2020
    Deskripsi   : menambahkan komponen room pada dependencies;
                  membuat kelas MainActivity, AktivitisEntity dan AppDatabase;
                  membuat interface AktivisDao;
                  menambahkan isi database aktivis ke layout activity_main
 */

package com.chupakubera.roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textData;

    private AktivisEntity aktivisEntity;
    public static AppDatabase db;

    //Atribut untuk mendisplay hasil data
    List<AktivisEntity> aktivisEntities = new ArrayList<>();
    List<AktivisEntity> aktivisEntityListByZone = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textData = findViewById(R.id.tv_data);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "aktivis")
                .allowMainThreadQueries().build();

        //TAMBAH DATA
        aktivisEntity = new AktivisEntity();
        aktivisEntity.setNamaAktivis("Maya Susanti");
        aktivisEntity.setEmailAktivis("maya@gmail.com");
        aktivisEntity.setZonaTugas("Bandung");

        Log.d("TAMBAH","Tambah Data Aktivis");
        Log.d("TAMBAH","===================");
        Log.d("TAMBAH","Nama : "+aktivisEntity.getNamaAktivis());
        Log.d("TAMBAH","Email : "+aktivisEntity.getEmailAktivis());
        Log.d("TAMBAH","Zona : "+aktivisEntity.getZonaTugas());

        textData.setText("Nama  : "+aktivisEntity.getNamaAktivis()+"\n"+
                         "Email : "+aktivisEntity.getEmailAktivis()+"\n"+
                         "Zona  : "+aktivisEntity.getZonaTugas());


        db.aktivisDao().tambahAktivis(aktivisEntity);

        //TAMPIL SELURUH DATA
        Log.d("TAMPIL","Tampil seluruh data aktivis");
        Log.d("TAMPIL","===========================");

        aktivisEntities = db.aktivisDao().tampilSeluruhAktivis();

        for (int i = 0; i<aktivisEntities.size(); i++){

            Log.d("TAMPIL","Data Ke-"+(i+1));
            Log.d("TAMPIL","Nama : "+aktivisEntities.get(i).getNamaAktivis());
            Log.d("TAMPIL","Email : "+aktivisEntities.get(i).getEmailAktivis());
            Log.d("TAMPIL","Zona : "+aktivisEntities.get(i).getZonaTugas());
            Log.d("TAMPIL","========================");
        }

        //TAMPIL BERDASARKAN ZONA
        Log.e("ZONE","Data Aktivis berdasarkan Zona");
        Log.e("ZONE","===================");
        aktivisEntityListByZone = db.aktivisDao().findByZone("Jakarta");
        for (int i = 0 ;i <aktivisEntityListByZone.size();i++){
            Log.e("ZONE","Data Aktivis Ke-"+(i+1));
            Log.e("ZONE",aktivisEntityListByZone.get(i).getNamaAktivis());
            Log.e("ZONE",aktivisEntityListByZone.get(i).getEmailAktivis());
            Log.e("ZONE",aktivisEntityListByZone.get(i).getZonaTugas());
            Log.e("ZONE","===================");
        }
    }
}
