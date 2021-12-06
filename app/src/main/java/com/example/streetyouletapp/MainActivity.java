package com.example.streetyouletapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.streetyouletapp.Model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    RecyclerView productList;
    List<Product> products;
    ProductAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productList = findViewById(R.id.product_list);
        products = new ArrayList<>();

        extractProduct(getResources().getString(R.string.url));
        GridLayoutManager manager = new GridLayoutManager(this,2);
        productList.setLayoutManager(manager);

        adapter = new ProductAdapter(products);
        productList.setAdapter(adapter);




    }
    private void extractProduct(String URL){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Log.d("TAG", "onResponse" + response.toString());
                for (int i = 0; i < response.length(); i++){
                    try {
                        Product product = new Product();
                        JSONObject jsonObject = response.getJSONObject(i);
                        product.setId(jsonObject.getInt("id"));
                        product.setLink(jsonObject.getString("link"));
                        product.setStatus(jsonObject.getString("status"));
                        // size
                        JSONObject sizeObject = jsonObject.getJSONObject("excerpt");
                        String rendered =sizeObject.getString("rendered");
                        Pattern p = Pattern.compile("\\d+");
                        Matcher m = p.matcher(rendered);
                        ArrayList<String> size = new ArrayList<>();
                        while (m.find()){
                            size.add(m.group());
                        }
                        product.setSize(size);
                        //title
                        JSONObject titleObject = jsonObject.getJSONObject("title");
                        product.setTitle(titleObject.getString("rendered"));

                        Log.d("TAG'", "onResponse " + product.toString());
                        products.add(product);
                        adapter.notifyDataSetChanged();



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        );
        queue.add(request);
    }

}