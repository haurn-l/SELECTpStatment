package veriCekmeSELECT;

import java.sql.*;

public class uygulama {

    static Connection connection = null;
    static PreparedStatement pStatement =null;
    static ResultSet resultSet = null;

    public static void main(String[] args) {
        getConnection();
        veriCekme(1);
        close();

    }
    public static void veriCekme(int arananid){
        String sorgu = "SELECT * FROM ogrenciler123 WHERE id= ?";

        try {
            pStatement = connection.prepareStatement(sorgu);
            pStatement.setInt(1,arananid);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()){//resulset.next boolean türünde veri döndürür bu yüzden
                                     // while içi false olduğunda veri çekmeyi bitirir.
            int id = resultSet.getInt("id");
            String isim = resultSet.getNString("adi");
            String soyisim = resultSet.getNString("soyadi");
            int yas = resultSet.getInt("yasi");
                System.out.println("id : "+id);
                System.out.println("isim : "+isim);
                System.out.println("soyisim : "+soyisim);
                System.out.println("yas : "+yas);
                System.out.println("***********");
            }
        } catch (SQLException e) {
            System.err.println("HATA : "+e.getMessage());
        }
    }
    public static void getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Bağlanılıyor...");
        } catch (ClassNotFoundException e) {
            System.err.println("Drivera bağlanılamadı."+e.getMessage());
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","2024123011Qk!");
            System.out.println("Connection bağlantısı başarılı");
        } catch (SQLException e) {
            System.out.println("Connection bağlantısı yapılamadı.Hata : "+e.getMessage());
        }

    }
    public static void  close(){
        if (pStatement!=null){
            try {
                pStatement.close();
                System.out.println("pStatment basarılı bir sekilde kapatıldı");
            } catch (SQLException e) {
                System.err.println("pStatement kapatılırken bir sorun olustu");
                System.out.println(e.getMessage());
            }
        }
        if (connection!=null){
            try {
                connection.close();
                System.out.println("Connection basarılı bir sekilde kapatıldı.");
            } catch (SQLException e) {
                System.err.println("Connection kapatılırken hata olusutu.Hata : "+e.getMessage());
            }
        }
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.err.println("HATA : "+e.getMessage());
            }
        }
    }
}
