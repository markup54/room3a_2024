package pl.zabrze.zs10.room3a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ZakupyDataBase bazaDanych;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        wstawProduktDoBazyWTle(new Produkt("myszka",45));
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