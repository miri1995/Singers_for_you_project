package com.example.myapplicationtest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.myapplicationtest.Enums.EnumAsync;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class AsyncHTTP extends AsyncTask<Void, Void, String> {
    ProgressDialog mProgressDialog;
    Context context;
    private String query;
    private String colName1;
    private String colName2;
    private String colName3;
    private String colName4;
    public List<String> playerList;
    private String flag;


    public AsyncHTTP(Context context) {
        this.context=context;
    }

    protected void onPreExecute() {
        mProgressDialog = ProgressDialog.show(context, "",
                "Please wait, getting database...");
    }

    @Override
    protected String doInBackground(Void... voids) {
        // Create URL
        URL url = null;
        try {
            url = new URL("https://www.distance.to/");
            Log.d("D",url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("D","m1 "+e.getMessage());
        }

// Create connection
        try {
            HttpsURLConnection myConnection =
                    (HttpsURLConnection) url.openConnection();
            //myConnection.setRequestProperty("headerAirline", "application/vnd.github.v3+json");
            //InputStream in = new BufferedInputStream(myConnection.getInputStream());
            InputStream responseBody = myConnection.getInputStream();
            InputStreamReader responseBodyReader =
                    new InputStreamReader(responseBody, "UTF-8");
            JsonReader jsonReader = new JsonReader(responseBodyReader);
            Log.d("D","J "+responseBody.toString());
            jsonReader.beginObject();
            while (jsonReader.hasNext()) { // Loop through all keys
                String key = jsonReader.nextName(); // Fetch the next key
                Log.d("D","PP key "+key);
                if (key.equals("headerAirline")) { // Check if desired key
                    // Fetch the value as a String
                    String value = jsonReader.nextString();

                    // Do something with the value
                    // ...

                    break; // Break out of the loop
                } else {
                    jsonReader.skipValue(); // Skip values of other keys
                }
            }

            Log.d("D","PP "+jsonReader.nextString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("D","m2 "+e.getMessage());
        }
        return "COMPLETE";
    }



    protected void onPostExecute(String result) {

        if (result!=null) {
            mProgressDialog.dismiss();
            //result= SingersActivity.geners.get(1).toString();
        }

    }
}
