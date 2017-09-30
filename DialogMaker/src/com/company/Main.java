package com.company;

import org.json.*;

import java.io.FileWriter;
import java.sql.*;

public class Main {

    private class Resource
    {
        public String Name;

        public Resource(String name)
        {
            Name = name;
        }
    }

    private Connection connect() {
        Connection conn = null;
        String url = "jdbc:sqlite:F:/Documents/CPSC/SQLite/prod.db";
        try
        {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void selectAll() {
        String sql = "SELECT * FROM Character";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next()) {
                System.out.printf("CharacterID: %d%n", rs.getInt("CharacterID"));
                System.out.printf("CharacterName: %s%n", rs.getString("Name"));
                System.out.printf("ClassID: %d%n", rs.getInt("ClassID"));
                System.out.printf("Level: %d%n", rs.getInt("Level"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        System.out.println("Aight, time to make dialog boiz");

        Main test = new Main();
        test.selectAll();
    }



    private static void JsonMakeTest() {
        JSONObject obj = new JSONObject();

        obj.put("Name", "Nik");
        obj.put("Age", "18");

        try (FileWriter file = new FileWriter("./testOut4.json")){
            file.write(obj.toString(4));
        }
        catch(Exception e)
        {
            System.out.println("geezus chill the fuck out pls");
        }
    }
}
