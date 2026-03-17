package com.example.a2581;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {


    TextView titleve,priceve,dpriceve,ratingve,stockve,desve,pppp;

    ImageView imageView,back;



    public static String Title = "";
    public static String Priceve = "";
    public static String Dpriceve = "";
    public static String Ratingve = "";
    public static String Stockve = "";
    public static String Desve = "";
    public static String ImageView = "";
    public static String Back = "";

    public static Bitmap MyBitmap = null ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        titleve = findViewById(R.id.titleve);
        priceve = findViewById(R.id.priceve);
        dpriceve = findViewById(R.id.dpriceve);
        ratingve = findViewById(R.id.ratingve);
        stockve = findViewById(R.id.stockve);
        desve = findViewById(R.id.desve);
        imageView = findViewById(R.id.pd);
        back = findViewById(R.id.back);

        pppp = findViewById(R.id.pppp);

        titleve.setText(Title);
        desve.setText(Desve);
        priceve.setText(Priceve);
        dpriceve.setText(Html.fromHtml("<strike>"+Dpriceve+"</strike>"));
        ratingve.setText(Ratingve);
        stockve.setText(Stockve);

        pppp.setText("Add to Cart ==> "+Priceve);

        if (MyBitmap!=null) imageView.setImageBitmap(MyBitmap);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}