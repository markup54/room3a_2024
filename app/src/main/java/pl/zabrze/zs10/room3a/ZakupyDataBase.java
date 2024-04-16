package pl.zabrze.zs10.room3a;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Produkt.class} , version = 1)
public abstract  class ZakupyDataBase extends RoomDatabase {
   abstract ProduktDAO zwrocProduktDao();
}
