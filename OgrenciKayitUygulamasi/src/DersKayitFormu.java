import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DersKayitFormu extends JFrame {
    // Arayüz bileşenleri
    private JTextField textOgretmeni;
    private JTextField textDonemi;
    private JTextField textDersAdi;
    private JLabel Ogretmen;
    private JLabel Donem;
    private JLabel DersKodu;
    private JLabel DersAdi;
    private JButton KaydetDers;
    private JPanel Panelders;
    private JTextField textDersKodu;
    private JTextField textDersBolum;
    private JLabel DersBolum;

    public DersKayitFormu() {
        setTitle("Ders Kayit Sayfası");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(Panelders);

        // "Kaydet" butonuna tıklandığında.
        KaydetDers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kullanıcı girişlerini doğrula ve yeni ders kaydını kaydet.
                if (validateInputs() && !isDuplicateCourse()) {
                    kaydetDers();
                    // Yeni ders kaydı başarıyla kaydedildiyse, Menu ekranını göster ve bu pencereyi kapat.
                    Menu menu = new Menu();
                    menu.setVisible(true);
                    dispose();
                }
                // Hata durumları için kullanıcıya uyarı mesajları göster.
                else {
                    if (isDuplicateCourse()) {
                        JOptionPane.showMessageDialog(DersKayitFormu.this, "Bu ders zaten kayıtlı.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                    else {

                        JOptionPane.showMessageDialog(DersKayitFormu.this, "Lütfen tüm alanları doldurun.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private boolean validateInputs() {
        // Gerekli alanların boş olup olmadığını kontrol edilir.
        return !textOgretmeni.getText().isEmpty() &&
                !textDersKodu.getText().isEmpty() &&
                !textDersBolum.getText().isEmpty() &&
                !textDonemi.getText().isEmpty() &&
                !textDersAdi.getText().isEmpty();
    }

    private boolean isDuplicateCourse() {
        // Ders bilgilerinin daha önce kaydedilip kaydedilmediğini kontrol edilir.
        DersKayit dersKayit = createDersKayitFromInputs();
        String searchData = dersKayit.getDersHocasi() + "," + dersKayit.getDersKodu() + "," + dersKayit.getDersDonemi() + "," + dersKayit.getDersAdi() + "," + dersKayit.getDersBolum();

        String existingData = readExistingData();
        return existingData.contains(searchData);
    }

    private void kaydetDers() {// Yeni ders kaydını oluşturur ve dosyaya kaydeder.
        DersKayit dersKayit = createDersKayitFromInputs();
        String newData = dersKayit.getDersDonemi() + "," + dersKayit.getDersBolum() + "," + dersKayit.getDersAdi() + "," + dersKayit.getDersKodu() + "," + dersKayit.getDersHocasi();

        String existingData = readExistingData();
        if (!existingData.isEmpty()) {
            existingData += "\n";
        }
        existingData += newData;

        writeDataToFile(existingData);

        JOptionPane.showMessageDialog(this, "Ders Bilgileri kaydedilmiştir.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
    }

    // Kullanıcının girdiği bilgilerle DersKayit nesnesi oluşturan metot.
    private DersKayit createDersKayitFromInputs() {
        DersKayit dersKayit = new DersKayit();
        dersKayit.setDersHocasi(textOgretmeni.getText());
        dersKayit.setDersKodu(textDersKodu.getText());
        dersKayit.setDersDonemi(textDonemi.getText());
        dersKayit.setDersAdi(textDersAdi.getText());
        dersKayit.setDersBolum(textDersBolum.getText());
        return dersKayit;
    }

    private String readExistingData() {  // Var olan veriyi dosyadan okuyan metot.
        Path filePath = Paths.get("dersKayit.csv");
        if (Files.exists(filePath)) {
            try {
                return new String(Files.readAllBytes(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private void writeDataToFile(String data) { // Veriyi dosyaya yazan metot.
        Path filePath = Paths.get("dersKayit.csv");
        try {
            Files.write(filePath, data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Dosya yazma hatası.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        new DersKayitFormu();
    }
}