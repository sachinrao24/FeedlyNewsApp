package com.example.feedlyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.feedlyapp.adapter.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<NewsItem> newsItemList;
    private LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initRecyclerView();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

    }

    private void initData() {
        String url = "https://cloud.feedly.com/v3/streams/user%2F4565b0c1-b378-48f3-a53e-351f40719a4b%2Fcategory%2F7be5624b-33f6-48fb-bbc4-ff0e36afea76/contents";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                newsItemList = new ArrayList<>();
                try {
                    JSONArray items = response.getJSONArray("items");
                    for (int i = 0; i < items.length(); i++) {
                        JSONObject item = items.getJSONObject(i);
                        JSONObject news_category = item.getJSONObject("categories[0]");
                        String title = item.getString("title");
                        String category = news_category.getString("label");
                        String site_name = item.getString("origin.title");
                        int popularity_count = item.getInt("engagement");

                        NewsItem newsItem = new NewsItem(title, category, site_name, popularity_count);

                        newsItemList.add(newsItem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Something went wrong...", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                String access_token = "A6QSp6QuYaOcjrssY6rnHM_lfDYqNy_rgap5acAQPrhrCYS42FVnVwHQ27qOGxq9voO2-_gQt_0kdU_8fH23MdIJZGhUMqjOgGn1KNPhQuFNa0EWvLrrN89VEHqgSMWOFI674XqjRv63Lnj8uCe_uC7gFB7xT8H2VZi1EfKbSVWvQYGr0RpWnFlP79y1pByyJzTm0cO_gFOTw2Pz2Ofz6UhE4Ig3KRQrPc_exVyAfUtvPPDe3exSxfhRYJjQ:feedlydev";
                headers.put("Content-Type", "application/json; charset=UTF-8");
                headers.put("Authorization", "Bearer" + access_token);
                return headers;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonObjectRequest);

    }
    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerViewAdapter = new RecyclerViewAdapter(newsItemList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}