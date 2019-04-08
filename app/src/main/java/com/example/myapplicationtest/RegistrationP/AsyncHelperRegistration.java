package com.example.myapplicationtest.RegistrationP;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.myapplicationtest.ProductActivity;
import com.example.myapplicationtest.SingersActivity;
import com.example.myapplicationtest.SulationSinger;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AsyncHelperRegistration extends AsyncTask<Void, Void, String> {
    ProgressDialog mProgressDialog;
    Context context;
    private String query;
    private String colName;
    public List<String> playerList;
    private String flag;


    public AsyncHelperRegistration(Context context, String query,String colName, String flag) {
        //Log.d("D",url);
        this.context = context;
       // playerList=new ArrayList<>();
        this.query=query;
        this.colName=colName;
        this.flag=flag;
        Log.d("D","con"+flag);
    }

    protected void onPreExecute() {
        mProgressDialog = ProgressDialog.show(context, "",
                "Please wait, getting database...");
    }

    @Override
    protected String doInBackground(Void... voids) {
        final String host = "35.225.34.63";
        final String port = "3306";
        final String schema = "dbProject";
        final String user = "root";
        final String password = "0542015460mb";

        try {
            Log.d("D","in background");
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + schema + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Israel", user, password);
            Log.d("DD","FLAG:"+flag);
            int result;
            switch (flag){
                case "genre":
                case "genre_poet":
                    try (Statement stmt = con.createStatement();
                         ResultSet rs = stmt.executeQuery(query);) {
                        while (rs.next()) {
                           // Helper.geners.add(rs.getString(colName));
                                if(flag.equals("genre")) {
                                    SingersRegistration.geners.add(rs.getString(colName));
                                } else if(flag.equals("genre_poet")) {
                                PoetsRegistration.geners.add(rs.getString(colName));
                            }
                        }
                        Log.d("D","result"+ Helper.geners);

                        //return "COMPLETE2";
                    } catch (SQLException e) {
                        System.out.println("ERROR executeQuery - " + e.getMessage());
                        Log.d("D","ERROR executeQuery");
                    }
                    break;
                case "lastId":
                    try (Statement stmt = con.createStatement();
                         ResultSet rs = stmt.executeQuery(query);) {
                        while (rs.next()) {
                            SingersRegistration.lastID=Integer.parseInt(rs.getString(colName))+1;
                        }
                        Log.d("D","result"+ SingersRegistration.lastIDSong);

                        //return "COMPLETE2";
                    } catch (SQLException e) {
                        System.out.println("ERROR executeQuery - " + e.getMessage());
                        Log.d("D","ERROR executeQuery");
                    }
                    break;
                case "lastIdSong":
                    try (Statement stmt = con.createStatement();
                         ResultSet rs = stmt.executeQuery(query);) {
                        while (rs.next()) {

                                SingersRegistration.lastIDSong=Integer.parseInt(rs.getString(colName))+1;

                        }
                        Log.d("D","result"+ SingersRegistration.lastIDSong);

                        //return "COMPLETE2";
                    } catch (SQLException e) {
                        System.out.println("ERROR executeQuery - " + e.getMessage());
                        Log.d("D","ERROR executeQuery");
                    }
                    break;
                case "GenreId":
                    try (Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);) {
                        while (rs.next()) {
                                SingersRegistration.genreID=Integer.parseInt(rs.getString(colName));
                        }
                        Log.d("D","result"+ SingersRegistration.lastIDSong);

                        //return "COMPLETE2";
                    } catch (SQLException e) {
                        System.out.println("ERROR executeQuery - " + e.getMessage());
                        Log.d("D","ERROR executeQuery");
                    }

                    break;

                case "insertSinger":
                    try (Statement stmt = con.createStatement();) {
                        Log.d("D","query " + query);
                         result = stmt.executeUpdate(query);
                        Log.d("D","Success - executeUpdate, result = " + result);

                    } catch (SQLException e) {
                        System.out.println("ERROR executeUpdate - " + e.getMessage());
                        Log.d("D","ERROR executeUpdate - " + e.getMessage());
                    }
                    break;

            }

            Log.d("D","in background list"+SulationSinger.artists);
        } catch (SQLException e) {
            Log.d("D",e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Log.d("D",e.getMessage());
            e.printStackTrace();
        }
        // Log.d("D",playerList.get(1).toString());
        return "COMPLETE";
    }


    public List<String> getList(){
        Log.d("D","LLL"+playerList);
        return playerList;
    }
    protected void onPostExecute(String result) {

        if (result!=null) {
            mProgressDialog.dismiss();

        }

    }
}
