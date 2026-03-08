package com.example.a2581;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap <String,String> hashMap;

    GridView gridview;

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        gridview = findViewById(R.id.gridview);


        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String Url = "https://deenway.online/eshop.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,Url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {


                        try {

                            JSONArray jsonArray = jsonObject.getJSONArray("products");


                            for (int x=0; x<jsonArray.length();x++){


                                JSONObject jsonObject1 = jsonArray.getJSONObject(x);

                                String Title = jsonObject1.getString("title");
                                String Des = jsonObject1.getString("description");
                                String Cate = jsonObject1.getString("category");
                                String Pric = jsonObject1.getString("price");
                                String Dicp = jsonObject1.getString("discountPercentage");
                                String Raiing = jsonObject1.getString("rating");
                                String Stock = jsonObject1.getString("stock");

                                JSONArray imagesarra = jsonObject1.getJSONArray("images");
                                String Imageview = imagesarra.getString(0);


                                hashMap = new HashMap<>();
                                hashMap.put("title",Title);
                                hashMap.put("description",Des);
                                hashMap.put("category",Cate);
                                hashMap.put("price",Pric);
                                hashMap.put("discountPercentage",Dicp);
                                hashMap.put("rating",Raiing);
                                hashMap.put("stock",Stock);
                                hashMap.put("images",Imageview);


                                arrayList.add(hashMap);


                            }

                            MYADapter MYadapter = new MYADapter();
                            gridview.setAdapter(MYadapter);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ////////////////////////////////
            }
        });


        requestQueue.add(jsonObjectRequest);


    }


    public class MYADapter extends BaseAdapter{
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myview = layoutInflater.inflate(R.layout.proxml,parent,false);

            ImageView imageView = myview.findViewById(R.id.imageView);
            TextView star = myview.findViewById(R.id.star);
            TextView title = myview.findViewById(R.id.title);
            TextView des = myview.findViewById(R.id.des);
            TextView price = myview.findViewById(R.id.price);
            TextView noprice = myview.findViewById(R.id.noprice);
            TextView stock = myview.findViewById(R.id.stock);


            HashMap<String,String>hashMap=arrayList.get(position);

            String Imageview = hashMap.get("images");
            String Title = hashMap.get("title");
            String Des = hashMap.get("description");
            String Price = hashMap.get("price");
            String Desp = hashMap.get("discountPercentage");
            String Rating = hashMap.get("rating");
            String Stock = hashMap.get("stock");

            Picasso.get().load(Imageview).into(imageView);

            
            title.setText(Title);
            des.setText(Des);
            price.setText(Price);
            noprice.setText(Html.fromHtml("<strike>"+Desp+"</strike>"));
            star.setText("⭐ "+Rating);
            stock.setText("Stock : "+ Stock);


            return myview;
        }
    }




}