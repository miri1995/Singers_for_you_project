package com.example.myapplicationtest;

//import Logic.Priority;
//import Logic.Solution;
//import Logic.Filters;
import java.io.*;
import java.sql.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;


/**
 * Connector class - responsible for connection creation.
 */
public class Connector {
    public static int counter;
    Connection conn; // DB connection
    List<String> artists = new ArrayList<String>();


    /**
     * Empty constructor
     */
    public Connector() {
        this.conn = null;
    }

    /**
     * opens the connection with mysql.
     * reads from configuration file the details: schema,user,password.
     * @return true if the connection was successfully set
     */
    public boolean openConnection() {
        System.out.print("Trying to connect... ");
        String host = "35.225.34.63";
        String port = "3306";
        String schema = "dbProject";
        String user = "root";
        String password = "0542015460mb";

        // Parameters taken from the config file
       /* BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("config"));
            schema = br.readLine();
            user = br.readLine();
            password = br.readLine();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ioe) {
                System.out.println("Error in closing the BufferedReader");
            }
        }*/
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + schema, user, password);
        } catch (SQLException e) {
            System.out.println("Unable to connect - " + e.getMessage());
            conn = null;
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Connected!");
        return true;
    }

    /**
     * close the connection with mysql.
     */
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Unable to close the connection - " + e.getMessage());
        }

    }


    /**
     * executes the query according to the user's choice.
     * adds the artists returned from the query to a list.
     * @param filters = holds the values of genre,loudness and tempo that the user chose.
     */
    /*public void ExecuteQuery(Filters filters, Priority priority) {
        artists.clear();
        Query query =new Query();
        String q3= query.UserInput(filters.getGenre(),filters.getLoudness(),priority.getLoudness(),filters.getTempo(),priority.getTempo());

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(q3);) {
            while (rs.next() == true) {
                artists.add(rs.getString("artist_name"));
            }

            Solution.getInstance(artists);
            System.out.println(artists);

        } catch (SQLException e) {
            System.out.println("ERROR executeQuery - " + e.getMessage());
        }
    }*/

     public void ExecuteQuery() {
        artists.clear();
        Query query =new Query();
        //String q3= query.UserInput(filters.getGenre(),filters.getLoudness(),priority.getLoudness(),filters.getTempo(),priority.getTempo());
         String q3="select * from artists";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(q3);) {
            while (rs.next() == true) {
                artists.add(rs.getString("artist_name"));
            }

           // Solution.getInstance(artists);
            System.out.println(artists);

        } catch (SQLException e) {
            System.out.println("ERROR executeQuery - " + e.getMessage());
        }
    }
}