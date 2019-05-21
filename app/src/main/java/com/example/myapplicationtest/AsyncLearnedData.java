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

public class AsyncLearnedData extends AsyncTask<Void, Void, String> {
    ProgressDialog mProgressDialog;
    Context context;
    public AsyncLearnedData(Context context, String query, String colName1, String colName2, String colName3, String colName4, String flag) {
        //Log.d("D",url);
        this.context=context;
    }

    protected void onPreExecute() {
        mProgressDialog = ProgressDialog.show(context, "",
                "Please wait...");
    }

    @Override
    protected String doInBackground(Void... voids) {


            Log.d("D","in background list"+ SolutionSinger_Tab1.artists);

        // Log.d("D",playerList.get(1).toString());
        return "COMPLETE";
    }



    protected void onPostExecute(String result) {

        if (result!=null) {
            mProgressDialog.dismiss();
            //result= SingersActivity.geners.get(1).toString();
        }

    }
}
