package com.krrt.vl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setTitle("Новини");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public View onCreateView(String name, final Context context, AttributeSet attrs) {
        NetworkService.getInstance()
                .getJSONApi()
                .getNewsId(getIntent().getLongExtra("id", 0))
                .enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
                        ImageView img = findViewById(R.id.na_img);
                        if (response.body().getImg() != null) {
                            byte[] decodedString = Base64.decode(response.body().getImg(), Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                            img.setImageBitmap(decodedByte);
                        }
                        TextView title = findViewById(R.id.na_title);
                        title.setText(response.body().getTitle());
                        WebView content = findViewById(R.id.na_content);
                        content.setFocusable(false);
                        content.setBackgroundColor(Color.TRANSPARENT);
                        content.loadData(response.body().getContent(), "text/html; charset=UTF-8", null);
                        TextView date = findViewById(R.id.na_date);
                        date.setText(new SimpleDateFormat("dd.MM.yyyy").format(response.body().getDate()));
                    }
                    @Override
                    public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
