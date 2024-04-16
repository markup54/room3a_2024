package pl.zabrze.zs10.room3a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
Button button;
EditText editTextNazwa;
EditText editTextCena;
List<Produkt> wszystkieProdukty = new ArrayList<>();
ListView listView;
ArrayAdapter<Produkt> adapter;

    ZakupyDataBase bazaDanych;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editTextCena =findViewById(R.id.editTextCena);
        editTextNazwa = findViewById(R.id.editTextNazwa);
        listView = findViewById(R.id.listView);
        RoomDatabase.Callback callback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };
        bazaDanych = Room.databaseBuilder(
                getApplicationContext(),
                ZakupyDataBase.class,
                "zakuby_db"
        ).addCallback(callback).build();
        wypiszWszystkieProduktyZBazyWTle();
button.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nazwa =editTextNazwa.getText().toString();
                int cena = Integer.valueOf(editTextCena.getText().toString());
                wstawProduktDoBazyWTle(new Produkt(nazwa,cena));
            }
        }
);

    }
    private  void  wypiszWszystkieProduktyZBazyWTle(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        wszystkieProdukty = bazaDanych.zwrocProduktDao().wypiszWszystkieProdukty();
                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        //z listy wszystkieProdukty do listView
                                    adapter = new ArrayAdapter<>(getApplicationContext(),
                                            android.R.layout.simple_list_item_1,wszystkieProdukty);
                                    listView.setAdapter(adapter);
                                    }
                                }
                        );
                    }
                }
        );
    }
    private void wstawProduktDoBazyWTle(Produkt produkt){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        //co robi
                        bazaDanych.zwrocProduktDao().wstawDoBazy(produkt);

                        //co po tym jak zrobi
                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Wstawiono do bazy",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                    }
                }
        );
    }
}