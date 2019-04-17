package com.example.myapplicationtest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.myapplicationtest.Enums.EnumAsync;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AsyncHelper extends AsyncTask<Void, Void, String> {
    ProgressDialog mProgressDialog;
    Context context;
    private String query;
    private String colName1;
    private String colName2;
    private String colName3;
    private String colName4;
    public List<String> playerList;
    private String flag;


    public AsyncHelper(Context context, String query, String colName1,String colName2,String colName3,String colName4, String flag) {
        //Log.d("D",url);
        this.context = context;
        playerList=new ArrayList<>();
        this.query=query;
        this.colName1=colName1;
        this.colName2=colName2;
        this.colName3 = colName3;
        this.colName4 =colName4;
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
           // Log.d("D","in background"+con);
           // Log.d("D",query);
           // Log.d("D",colName1);
            Log.d("D","NEW FLAG"+flag);
            switch (EnumAsync.valueOf(flag)){

                case Genre:
                    Log.d("D","in genre"+query);
                    try (Statement stmt = con.createStatement();
                         ResultSet rs = stmt.executeQuery(query);) {
                        while (rs.next()) {
                            Log.d("D",flag);
                            HelperLists.genersHelperLists.add(rs.getString(colName1));
                        }
                        Log.d("D","result genre"+ rs);
                        con.close();
                        //return "COMPLETE2";
                    } catch (SQLException e) {
                        System.out.println("ERROR executeQueryGenre - " + e.getMessage());
                        Log.d("D","ERROR executeQuery gen");
                    }
                case Goal:
                    try (Statement stmt = con.createStatement();
                         ResultSet rs = stmt.executeQuery(query);) {
                        while (rs.next()) {
                            Log.d("D",flag);
                            HelperLists.goalHelperList.add(rs.getString(colName1));
                        }
                        Log.d("D","result goal"+ rs);
                        con.close();
                        //return "COMPLETE2";
                    } catch (SQLException e) {
                        System.out.println("ERROR executeQuery - " + e.getMessage());
                        Log.d("D","ERROR executeQuery goal");
                    }

                    //SingersActivity.geners.add(rs.getString(colName1));
                    break;
                case Topic:
                    try (Statement stmt = con.createStatement();
                         ResultSet rs = stmt.executeQuery(query);) {
                        while (rs.next()) {
                            Log.d("D",flag);
                            HelperLists.topicHelperList.add(rs.getString(colName1));
                        }

                        con.close();
                        //return "COMPLETE2";
                    } catch (SQLException e) {
                        System.out.println("ERROR executeQuery - topix" + e.getMessage());
                        Log.d("D","ERROR executeQuery top");
                    }
                    break;
                case Sol:
                    try (Statement stmt = con.createStatement();
                         ResultSet rs = stmt.executeQuery(query);) {
                        while (rs.next()) {
                            if(!SulationSinger.moreThanOnce(SulationSinger.artists,rs.getString(colName1))) {
                                SulationSinger.artists.add(rs.getString(colName1));
                                SulationSinger.tempo.add(rs.getDouble(colName2));
                                SulationSinger.loudness.add(rs.getDouble(colName3));
                                SulationSinger.genres.add(rs.getString(colName4));
                            }
                        }
                        Log.d("D","result"+ SulationSinger.artists);
                        con.close();
                        //return "COMPLETE2";
                    } catch (SQLException e) {
                        System.out.println("ERROR executeQuery - " + e.getMessage());
                        Log.d("D","ERROR executeQuery");
                    }
                    break;
                case Poet:
                    Log.d("D","col1 "+ colName1);
                    Log.d("D","in poet query"+ query);
                    try (Statement stmt = con.createStatement();
                         ResultSet rs = stmt.executeQuery(query)) {

                        while (rs.next()) {
                            Log.d("D","in poet query"+ rs.getString(colName1));
                            if(!SolutionPoets.moreThanOnce(SolutionPoets.poets,rs.getString(colName1))) {
                                SolutionPoets.poets.add(rs.getString(colName1));
                                SolutionPoets.subject.add(rs.getString(colName2));
                                SolutionPoets.goal.add(rs.getString(colName3));
                                SolutionPoets.genres.add(rs.getString(colName4));
                            }
                        }
                        Log.d("D","resultPoet"+ SolutionPoets.poets);
                        con.close();
                        //return "COMPLETE2";
                    } catch (SQLException e) {
                        System.out.println("ERROR executeQueryPoet - " + e.getMessage());
                        Log.d("D","ERROR executeQueryPoet"+ e.getMessage());
                    }
                    break;

                case Instrument:
                    Log.d("D","in instrument"+query);
                    try (Statement stmt = con.createStatement();
                         ResultSet rs = stmt.executeQuery(query);) {
                        while (rs.next()) {
                            Log.d("D",flag);
                            HelperLists.instrumentHelperList.add(rs.getString(colName1));
                        }
                        Log.d("D","result instrument"+ rs);
                        con.close();
                        //return "COMPLETE2";
                    } catch (SQLException e) {
                        System.out.println("ERROR executeQueryGenre - " + e.getMessage());
                        Log.d("D","ERROR executeQuery gen");
                    }
                    break;
                case Composer:
                    try (Statement stmt = con.createStatement();
                         ResultSet rs = stmt.executeQuery(query);) {
                        while (rs.next()) {
                            if(!SolutionComposer.moreThanOnce(SolutionComposer.composers,rs.getString(colName1))) {
                                SolutionComposer.composers.add(rs.getString(colName1));
                                SolutionComposer.tempo.add(rs.getDouble(colName2));
                                SolutionComposer.loudness.add(rs.getDouble(colName3));
                                SolutionComposer.genres.add(rs.getString(colName4));
                            }
                        }
                        Log.d("D","result"+ SolutionComposer.composers);
                        con.close();
                        //return "COMPLETE2";
                    } catch (SQLException e) {
                        System.out.println("ERROR executeQuery - " + e.getMessage());
                        Log.d("D","ERROR executeQuery");
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
            //result= SingersActivity.geners.get(1).toString();
        }

    }
}

