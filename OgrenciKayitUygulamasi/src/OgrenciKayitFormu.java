import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

public class OgrenciKayitFormu extends JFrame { // Arayüz bileşenleri
    private JTextField textNo;

    private JTextField textTelNo;
    private JTextField textSoyad;
    private JTextField textOgrenciAdi;
    private JButton ogrenciKaydetButton;
    private JLabel Ad;
    private JLabel Soyad;
    private JLabel No;
    private JLabel Bolum;
    private JLabel Tel;
    private JLabel Ders;
    private JCheckBox CheckBox;
    private JPanel Panelogrencikayit;
    private JLabel Donem;

    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JTextField textDogumTarihi;
    private JLabel DogumTarihi;
    private JLabel LabelOrnek;
    private JLabel Ornek2;
    private JComboBox<String> comboBox3;

    private OgrenciKayit ogrenciKayit;

    public OgrenciKayitFormu() {
        setTitle("Ogrenci Kayit Sayfası");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(Panelogrencikayit);

        fillComboBox1();
        fillComboBox2();
        fillComboBox3();


        ogrenciKayit = new OgrenciKayit();
        // "Öğrenci Kaydet" butonuna tıklandığında gerçekleşecekler.
        ogrenciKaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInputs()) {
                    kaydetOgrenci();
                    Menu menu = new Menu();
                    menu.setVisible(true);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(OgrenciKayitFormu.this, "Lütfen tüm alanları doldurun ve onay kutusunu işaretleyin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDersDonemi = (String) comboBox2.getSelectedItem();
                fillComboBox1BasedOnComboBox2(selectedDersDonemi);
            }
        });

        CheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckBox();
            }
        });
    }
    // Tarih çözümleme işlemi gerçekleştiren metot
    public Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    private Set<String> addedDersAdlari = new HashSet<>();

    // ComboBox1'i ComboBox2'ye göre dolduran metot.
    private void fillComboBox1BasedOnComboBox2(String selectedDersDonemi) {
        comboBox1.removeAllItems();
        addedDersAdlari.clear();

        String selectedBolum = (String) comboBox3.getSelectedItem();

        try (BufferedReader reader = new BufferedReader(new FileReader("dersKayit.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 2) {
                    String dersDonemi = parts[0].trim();
                    String dersAdi = parts[2].trim();
                    String dersBolum = parts[1].trim();

                    // Eğer dönem ve bölümle eşleşiyorsa, comboBox1'e ekle ve Set'e ekle
                    if (dersDonemi.equals(selectedDersDonemi) && dersBolum.equals(selectedBolum) && !addedDersAdlari.contains(dersAdi)) {
                        comboBox1.addItem(dersAdi);
                        addedDersAdlari.add(dersAdi);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Dosya okuma hatası.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Kullanıcı girişlerini doğrulayan metot.
    private boolean validateInputs() {
        if (textNo.getText().isEmpty() ||
                textTelNo.getText().isEmpty() || textSoyad.getText().isEmpty() ||
                textOgrenciAdi.getText().isEmpty() || !CheckBox.isSelected()) {
            return false;
        }
        return true;
    }
    // Öğrenci bilgilerini dosyaya kaydeden metot.
    private void kaydetOgrenci() {
        String ogrenciNo = textNo.getText().trim();
        String bolum = (String) comboBox3.getSelectedItem();
        String telNo = textTelNo.getText().trim();
        String soyad = textSoyad.getText().trim();
        String ogrenciAdi = textOgrenciAdi.getText().trim();
        String selectedDersDonemi = (String) comboBox2.getSelectedItem();
        String selectedDersAdi = (String) comboBox1.getSelectedItem();
        String dogumTarihi = textDogumTarihi.getText().trim();


        if (!validateDate(dogumTarihi)) {
            JOptionPane.showMessageDialog(this, "Kaydınız gerçekleştirilemedi. Lütfen tekrar bu form sayfasına girin. Doğru bir tarih formatı kullanın (GG/AA/YYYY).", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ogrenciKayit.setOgrenciAdi(ogrenciAdi);
        ogrenciKayit.setOgrenciSoyadi(soyad);
        ogrenciKayit.setOgrenciNo(ogrenciNo);
        ogrenciKayit.setOgrenciBolum(bolum);
        ogrenciKayit.setOgrenciDonem(selectedDersDonemi);
        ogrenciKayit.setTelefonNo(telNo);
        ogrenciKayit.setOgrenciDersSecimi(selectedDersAdi);
        // parseDate metodunu kullanarak tarih çözümleme işlemi gerçekleştir
        ogrenciKayit.setDogumTarihi(parseDate(dogumTarihi));

        String ogrenciVerisi = ogrenciKayit.getOgrenciAdi() + "," + ogrenciKayit.getOgrenciSoyadi() + "," + ogrenciKayit.getOgrenciNo() + "," + ogrenciKayit.getDogumTarihi() + "," + ogrenciKayit.getTelefonNo() + "," + ogrenciKayit.getOgrenciBolum() + "," + ogrenciKayit.getOgrenciDonem() + "," + ogrenciKayit.getOgrenciDersSecimi();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ogrenciKayit.csv", true))) {
            writer.write(ogrenciVerisi);
            writer.newLine();

            JOptionPane.showMessageDialog(this, "Öğrenci bilgileriniz kaydedilmiştir.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Dosya yazma hatası.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateDate(String date) {
        String dateFormatRegex = "\\d{2}/\\d{2}/\\d{4}";
        return date.matches(dateFormatRegex);
    }

    private void handleCheckBox() {

    }

    // ComboBox1'i dolduran metot.
    private void fillComboBox1() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dersKayit.csv"))) {
            Set<String> addedValues = new HashSet<>(); // Aynı değerleri kontrol etmek için bir küme oluştur

            String header = reader.readLine();
            String[] headers = header.split(",");
            if (headers.length > 2 && addedValues.add(headers[2].trim())) { // Eğer değer daha önce eklenmemişse ekle
                comboBox1.addItem(headers[2].trim());
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 2) {
                    String dersAdi = parts[2].trim();
                    if (addedValues.add(dersAdi)) { // Eğer değer daha önce eklenmemişse ekle
                        comboBox1.addItem(dersAdi);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Dosya okuma hatası.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    // ComboBox2'yi dolduran metot.
    private void fillComboBox2() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dersKayit.csv"))) {
            Set<String> addedValues = new HashSet<>(); // Aynı değerleri kontrol etmek için bir küme oluştur

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    String dersDonemi = parts[0].trim();
                    if (addedValues.add(dersDonemi)) { // Eğer değer daha önce eklenmemişse ekle
                        comboBox2.addItem(dersDonemi);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Dosya okuma hatası.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void fillComboBox3() {
        Set<String> addedValues = new HashSet<>(); // Aynı değerleri kontrol etmek için bir küme oluştur

        try (BufferedReader reader = new BufferedReader(new FileReader("dersKayit.csv"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    String secondValue = parts[1].trim();

                    // Eğer değer daha önce eklenmemişse ekle
                    if (addedValues.add(secondValue)) {
                        comboBox3.addItem(secondValue);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Dosya okuma hatası.", "Hata", JOptionPane.ERROR_MESSAGE);
        }

        // Add ActionListener to comboBox3
        comboBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateComboBox2BasedOnComboBox3();
            }
        });
    }

    // ComboBox1'i ComboBox2 ve ComboBox3'e göre dolduran metot.
    private void updateComboBox2BasedOnComboBox3() {
        comboBox2.removeAllItems();

        Set<String> addedDersDonemleri = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("dersKayit.csv"))) {
            String selectedValue = (String) comboBox3.getSelectedItem();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    String secondValue = parts[1].trim();
                    String dersDonemi = parts[0].trim();

                    if (secondValue.equals(selectedValue) && addedDersDonemleri.add(dersDonemi)) {
                        // Eğer değer daha önce eklenmemişse ekle
                        comboBox2.addItem(dersDonemi);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Dosya okuma hatası.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        new OgrenciKayitFormu();
    }


}