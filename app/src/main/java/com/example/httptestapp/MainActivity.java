package com.example.httptestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.httpcodeanalysis.HttpCode;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    HttpCode myHttp;
    PrintWriter pw;
    File file;

    private MaterialButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);

        //OutputStreamWriter os = new OutputStreamWriter(System.err);

        String filePath = this.getFilesDir().getPath().toString() + "/HTTPCODE1.txt";
        file = new File(filePath);

        try {
            pw = new PrintWriter(new FileOutputStream(
                    file,
                    true /* append = true */));
            myHttp = new HttpCode(pw);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        RequestQueue queue = Volley.newRequestQueue(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="https://www.google.com";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                })
                {
                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        String mStatusCode = String.valueOf(response.statusCode);
                        myHttp.CheckHttpCode(mStatusCode);
                        try {
                            BufferedReader br = new BufferedReader(new FileReader(file));
                            String st;
                            while ((st = br.readLine()) != null)
                                System.out.println(st);
                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        return super.parseNetworkResponse(response);
                    }
                };
                queue.add(stringRequest);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pw.close();
    }
}