package pl.zabrze.zs10.room3a;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProduktDAO {

    @Insert
    public void wstawDoBazy(Produkt produkt);

    @Delete
    public void usunZBazy(Produkt produkt);

    @Update
    public void zaktualizuj(Produkt produkt);

    @Query("Select * from produkty")
    public List<Produkt> wypiszWszystkieProdukty();

}
