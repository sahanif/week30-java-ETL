//import library module yang diperlukan
import java.sql.*;
import java.io.*;

public class csvETLDatabase {

    public static void  main (String[] args) {
        //inisiasi string koneksi ke database psql
        String userName = "postgres";
        String password = "digitalskola";

        //string koneksi ke database psql yang bernama: skola
        String  connectionString ="jdbc:postgresql://127.0.0.1:5432/skola?user="+userName+"&password="+password;

        //koneksi ke database dengan menerapkan exception handling
        try {
            Connection conn = DriverManager.getConnection(connectionString);
            Statement stmnt = conn.createStatement();

            FileReader file = new FileReader(("C:\\ddk_tingkat_pendidikan.csv"));
            BufferedReader reader = new BufferedReader(file);
            String lineText = null;

            //skip csv header
            reader.readLine();

            //baca file csv line by line untuk ketika line tidak sama dengan null
            while ((lineText = reader.readLine()) != null) {
                //ektract line dalam bentuk array data dengan patokan delimiter koma
                String[] data = lineText.split(",");
                int kode_provinsi = Integer.parseInt(data[0]);
                String nama_provinsi = data[1];
                String tingkat_pendidikan = data[2];
                String jenis_kelamin = data[3];
                int jumlah_individu = Integer.parseInt(data[4]);

                //string query untuk memasukkan buffer dari pembacaaan data csv ke psql
                String query = "INSERT INTO ddk_tingkat_pendidikan VALUES ("+kode_provinsi+", '"+nama_provinsi+"', '"+tingkat_pendidikan+"', '"+jenis_kelamin+"', "+jumlah_individu+")";

                //eksekusi query
                stmnt.executeUpdate(query);

            }

            //* opsi jika ingin menampilan hasil dari database
            /*
            String selectQuery = "SELECT * FROM ddk_tingkat_pendidikan";
            ResultSet rs = stmnt.executeQuery(selectQuery);
            while (rs.next()) {
                System.out.println("================================");
                System.out.println("Kode Provinsi: " +rs.getInt("kode_provinsi"));
                System.out.println("Nama Provinsi " +rs.getString("nama_provinsi"));
                System.out.println("Tingkat Pendidikan " +rs.getString("tingkat_pendidikan"));
                System.out.println("Jenis Kelamnin " +rs.getString("jenis_kelamin"));
                System.out.println("Jumlah Individu: "+rs.getInt("jumlah_individu"));
            }
             */

            //tutup proses query dan koneksi untuk menghemat resources
            stmnt.close();
            conn.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();;
        }
    }

}
