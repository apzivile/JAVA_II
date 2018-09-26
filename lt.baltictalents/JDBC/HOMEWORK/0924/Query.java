package lt.baltictalents.hw0924;

import com.mysql.jdbc.Connection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class Query {


    static void createNewTable() throws IOException {
        File schemeFile = new File("res/db_scheme");
        BufferedReader bfrS = new BufferedReader(new FileReader(schemeFile));
        String dataS;
        List<String> schemeContent = new ArrayList<>();

        while((dataS = bfrS.readLine()) != null){
            String[] getDataS = dataS.split("\\|");
            for(String s: getDataS){
                schemeContent.add(s);
                if(s == null) s.equals("NULL");
            }
        }
        System.out.println("Scheme: " + schemeContent);

        File dataFile = new File("res/db_data");
        BufferedReader bfrD = new BufferedReader(new FileReader(dataFile));
        String dataD;
        List<String> dataContent = new ArrayList<>();

        while((dataD = bfrD.readLine()) != null){
            String[] getDataD = dataD.split("\\|");
            for(String d: getDataD){
                dataContent.add(d);
                if(d == null) d.equals("NULL");
            }
        }
        System.out.println("Data: " + dataContent);

        String url = "jdbc:mysql://localhost:3306/homework?"
                + "user=root&password=password";

        String sql = ("CREATE TABLE " + schemeContent.get(0) + " (") +
                schemeContent.get(2) +
                " " + schemeContent.get(3) +
                " " + schemeContent.get(4) +
                "," +
                " " + schemeContent.get(5) +
                " " + schemeContent.get(6) +
                " " + schemeContent.get(7) +
                "," +
                " " + schemeContent.get(8) +
                " " + schemeContent.get(9) +
                " " + schemeContent.get(10) +
                "," +
                " " + schemeContent.get(11) +
                " " + schemeContent.get(12) +
                " " + schemeContent.get(13) +
                "," +
                " " + schemeContent.get(14) +
                ");";

        String sql1 = ("INSERT INTO " + dataContent.get(0) + "(" + schemeContent.get(2) + "," + schemeContent.get(5) + "," + schemeContent.get(8) + "," + schemeContent.get(11) + ")") +
                "VALUES " + "(" + dataContent.get(2) + "," + "'" + dataContent.get(5) + "'" + "," + dataContent.get(8) + "," + dataContent.get(11) + ")" + "," + "(" + dataContent.get(3) + "," + "'" + dataContent.get(6) + "'" + "," + dataContent.get(9) + "," + dataContent.get(12) + ")" + "," + "(" + dataContent.get(4) + "," + "'" + dataContent.get(7) + "'" + "," + dataContent.get(10) + "," + dataContent.get(13) + ")" +
                ";";
        System.out.println(sql1);
        try (

                Connection conn = (Connection) DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            stmt.execute(sql1);
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

