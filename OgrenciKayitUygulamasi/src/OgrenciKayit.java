import javax.swing.*;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class OgrenciKayit extends DersKayit {
    String OgrenciAdi;
    String OgrenciSoyadi;
    String OgrenciNo;
    String OgrenciBolum;
    String OgrenciDonem;
    String TelefonNo;
    String OgrenciDersSecimi;
    Date DogumTarihi;


    public String getOgrenciAdi() {
        return OgrenciAdi;
    }

    public void setOgrenciAdi(String OgrenciAdi) {
        this.OgrenciAdi = OgrenciAdi;
    }

    public String getOgrenciSoyadi() {
        return OgrenciSoyadi;
    }

    public void setOgrenciSoyadi(String OgrenciSoyadi) {
        this.OgrenciSoyadi = OgrenciSoyadi;
    }

    public String getOgrenciNo() {
        return OgrenciNo;
    }

    public void setOgrenciNo(String OgrenciNo) {
        this.OgrenciNo = OgrenciNo;
    }
    public String getOgrenciBolum() {
        return OgrenciBolum;
    }

    public void setOgrenciBolum(String OgrenciBolum) {
        this.OgrenciBolum = OgrenciBolum;
    }

    public String getOgrenciDonem() {
        return OgrenciDonem;
    }

    public void setOgrenciDonem(String OgrenciDonem) {this.OgrenciDonem = OgrenciDonem;}
    public String getTelefonNo() {
        return TelefonNo;
    }

    public void setTelefonNo(String TelefonNo) {this.TelefonNo = TelefonNo;}

    public String getOgrenciDersSecimi() {
        return OgrenciDersSecimi;
    }

    public void setOgrenciDersSecimi(String OgrenciDersSecimi) {this.OgrenciDersSecimi = OgrenciDersSecimi;}

    public Date getDogumTarihi() {return DogumTarihi;}

    public void setDogumTarihi(Date DogumTarihi) {this.DogumTarihi = DogumTarihi;}
}
