//package com.example.myapplicationtest;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.util.Log;
//
//import com.example.myapplicationtest.Enums.EnumAsync;
//
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AsyncLearnedData extends AsyncTask<Void, Void, String> {
//    ProgressDialog mProgressDialog;
//    Context context;
//    String query;
//    String colName1;
//    String colName2;
//    public AsyncLearnedData(Context context, String query, String colName,String colName2) {
//        //Log.d("D",url);
//        this.context=context;
//        this.query=query;
//        this.colName1=colName1;
//        this.colName2 = colName2
//    }
//
//    protected void onPreExecute() {
//        mProgressDialog = ProgressDialog.show(context, "",
//                "Please wait...");
//    }
//
//    @Override
//    protected String doInBackground(Void... voids) {
//
//        final String host = "35.225.34.63";
//        final String port = "3306";
//        final String schema = "dbProject";
//        final String user = "root";
//        final String password = "0542015460mb";
//
//        try {
//            Log.d("D", "in background");
//            Class.forName("com.mysql.jdbc.Driver");
//            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + schema + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Israel", user, password);
//
//            try (Statement stmt = con.createStatement();
//                 ResultSet rs = stmt.executeQuery(query);) {
//                while (rs.next()) {
//
//                    HelperLists.composerGenreHelperList.add(rs.getString(colName));
//                }
//                con.close();
//            } catch (SQLException e) {
//                Log.d("D", "ERROR executeQuery gen");
//            }
//        }catch (Exception e) {
//            Log.d("D", e.getMessage());
//        }
//        return "COMPLETE";
//    }
//
//
//
//    protected void onPostExecute(String result) {
//
//        if (result!=null) {
//            mProgressDialog.dismiss();
//            //result= SingersActivity.geners.get(1).toString();
//        }
//
//    }
//}
