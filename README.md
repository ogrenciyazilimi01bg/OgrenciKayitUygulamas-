# OgrenciKayitUygulaması
## Genel bakış
Bu projede, Ogrenci Kayit Sistemi için geliştirilen kodların kaynağı yer almaktadır. Bu proje, Java dilinde yazılmış bir sinema OgrenciKayit sistemi uygulamasını içermektedir.Proje temelde Öğrenci Kayıt Uygulaması niteliğinde Windows Form üzerinden yapılmaktadır.  Proje  kullanıcının istekleri doğrultusunda Ogrenci kaydı ve ders kaydı  yapmayı amaçlamaktadır. Bu yönergeleri, projeyi kendi makinenizde çalıştırmak ve geliştirmek için kullanabilirsiniz.
###Temel Bileşenler
DersKayit sınıfı içinde  ders adı, derskodu, dersdönemi, dershocası ve dersbölümü gibi özelliklere sahiptir. Bu sınıf, ders bilgilerini yönetmek ve erişmek için get ve set metotlarını içerir.
OgrenciKayit sınıfı, öğrenci bilgilerini temsil eder. Oğrenciadı, Ogrencisoyadı, Ogrencino, Ogrencibölüm,Ogrencidönem, telefonNo, Ogrenciders seçimi ve DoğumTarihi gibi bilgiler bu sınıf üzerinden yönetilir. Tarih formatındaki doğum tarihi, özel bir metot aracılığıyla çözümlenir.
###Kullanıcı Arayüzü
Menu sınıfı, ana menüyü temsil eder ve kullanıcıya "Ders Kayıt Formu" ve "Öğrenci Kayıt Formu" seçeneklerini sunar. Kullanıcı, menü üzerinden istediği formu seçerek işlemlerine devam edebilir.
DersKayitFormu sınıfı, kullanıcıya ders kayıtlarını girebileceği bir arayüz sunar. Kullanıcı, dersin adı,ders kodu, dersdönemi, dershocası ve verilecek ders bölümü gibi bilgileri girerek yeni ders kaydı ekleyebilir. 
OgrenciKayitFormu sınıfı, öğrenci bilgilerini girebilmek için tasarlanmış arayüzü içerir. Kullanıcı, öğrencinin adı, soyadı, numarası, bölümü, dönemi, telefon numarası, ders seçimi ve doğum tarihi gibi bilgileri girebilir.Bunlardan Bölümü ,dönemi ve ders seçimi combobox tarzı yapıldı. Tarih formatı doğrulaması ve eksik bilgi uyarıları içerir.
####Dosya İşlemleri
Uygulama, kayıtları dosyalarda saklar ve dosyadan  okuma/yazma işlemleri gerçekleştirir. Kaydedilen veri dosyası dersKayit.csv ve ogrenciKayit.csv adlarını taşır.
## Uygulama şu şekilde çalışır:
