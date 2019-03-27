package com.example.myapplicationtest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that handles
 * the connection to DataBase.
 */
public class DBConnection {

    private java.sql.Connection conn; // DB connection
    private static DBConnection instance;
    private final List<String> artists = new ArrayList<String>();
    private final Object mutex = new Object();

    /**
     * constructor
     */
    private DBConnection() { }

    /**
     * singleton function
     * @return the instance of DBconnection
     */

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
            instance.openConnection();
        }
        return instance;
    }

    /**
     * return the connection to sql object
     * @return connection to sql object
     */
    public java.sql.Connection getConnection() {
        try {
            synchronized (mutex) {
                mutex.wait();
            }
        } catch (Exception ex) {}
        return conn;
    }
    /**
     * Open the connection to the DB
     * @return true if the connection was successfully set
     */
    public boolean openConnection() {

        System.out.print("Trying to connect... ");
        //reading the details of url, username and password of my schema from configuration connection file

        BufferedReader br;
       /* String fileName = "src/DataBase/dbconnectionconfig.txt";
        try {
            br = new BufferedReader(new FileReader(fileName));
            //the parameters for connection to DB.
            url = br.readLine().replace("url: ","");
            username = br.readLine().replace("username: ","");
            password = br.readLine().replace("password: ","");
        } catch (Exception e) {
            System.out.println("couldn't read from configuration file - " + e.getMessage());
        }*/
        // creating the connection. Parameters should be taken from config file.

        final String host = "35.225.34.63";
        final String port = "3306";
        final String schema = "dbProject";
        final String user = "root";
        final String password = "0542015460mb";

        Thread t = new Thread( () -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + schema + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Israel", user, password);
                String q3="select * from artists";

                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(q3);) {
                    while (rs.next() == true) {
                        artists.add(rs.getString("artist_name"));
                    }

                    // Solution.getInstance(artists);


                } catch (SQLException e) {
                    System.out.println("ERROR executeQuery - " + e.getMessage());
                }
            } catch (SQLException e) {
                System.out.println("Unable to connect - " + e.getMessage());
                conn = null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Connected!");
            synchronized (mutex) {
                mutex.notifyAll();
            }
        });
        t.start();
        System.out.println(artists);
        return true;
    }

    /**
     * close the connection
     */
    public void closeConnection() {
        // closing the connection
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Unable to close the connection - " + e.getMessage());
        }

    }
}
