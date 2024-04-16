package pl.zabrze.zs10.room3a;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "produkty")
public class Produkt {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_produktu")
    private int id;

    @ColumnInfo(name = "nazwa_produktu")
    private String nazwa;

    private  int cena;

    @Ignore
    public Produkt() {
    }

    public Produkt(String nazwa, int cena) {
        id =0;
        this.nazwa = nazwa;
        this.cena = cena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }
}
