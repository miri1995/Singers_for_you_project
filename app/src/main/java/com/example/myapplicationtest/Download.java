package com.example.myapplicationtest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Download extends AsyncTask<Void, Void, String> {
    ProgressDialog mProgressDialog;
    Context context;
    private String query;
    private String colName;
    public List<String> playerList;
    private String flag;


    public Download(Context context,String query,String colName,String flag) {
        //Log.d("D",url);
        this.context = context;
        playerList=new ArrayList<>();
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
            Log.d("D","in background"+con);
            Log.d("D",query);
            Log.d("D",colName);
            try (Statement stmt = con.createStatement();

                 ResultSet rs = stmt.executeQuery(query);) {

                while (rs.next()) {
                    Log.d("D",flag);
                    if(flag.equals("singer")){
                        SingersActivity.geners.add(rs.getString(colName));
                    }else if(flag.equals("sol")){

                        SulationSinger.artists.add(rs.getString(colName));
                    }


                    Log.d("D",rs.getString(colName));
                }
                Log.d("D","result"+ SulationSinger.artists);
                con.close();
                //return "COMPLETE2";
            } catch (SQLException e) {
                System.out.println("ERROR executeQuery - " + e.getMessage());
                Log.d("D","ERROR executeQuery");
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

