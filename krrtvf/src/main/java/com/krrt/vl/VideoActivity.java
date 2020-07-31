package com.krrt.vl;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends AppCompatActivity {

    static final String API_KEY = "AIzaSyC99sYOr__-7YqM7KGVJL1dtPLYNYzrpZg";
    YouTubePlayerSupportFragment youTubePlayerSupportFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        setTitle("Відео");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        PrepareOrientation();
        NetworkService.getInstance()
                .getJSONApi()
                .getVideoId(getIntent().getLongExtra("id", 0))
                .enqueue(new Callback<Video>() {
                    @Override
                    public void onResponse(@NonNull Call<Video> call, @NonNull Response<Video> response) {
                        Bundle bundle = new Bundle();
                        bundle.putLong("id", response.body().getId());
                        bundle.putString("videofilename", response.body().getVideofilename());
                        if (response.body().getVideofilename().contains("youtube.com")) {
                            youTubePlayerSupportFragment = YouTubePlayerSupportFragment.newInstance();
                            youTubePlayerSupportFragment.setArguments(bundle);
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.add(R.id.flVideo, youTubePlayerSupportFragment).commit();
                            youTubePlayerSupportFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
                                @Override
                                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                                    if (!b)youTubePlayer.loadVideo(extractYoutubeId(youTubePlayerSupportFragment.getArguments().getString("videofilename")));
                                }
                                @Override
                                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                                }
                            });
                        } else {
                            VideoFragment fr = new VideoFragment();
                            fr.setArguments(bundle);
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.flVideo, fr);
                            ft.commit();
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<Video> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        PrepareOrientation();
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

    private void PrepareOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            getSupportActionBar().hide();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().show();
        }
    }

    protected static String extractYoutubeId(String url) {
        String id = null;
        try {
            String query = new URL(url).getQuery();
            if (query != null) {
                String[] param = query.split("&");
                for (String row : param) {
                    String[] param1 = row.split("=");
                    if (param1[0].equals("v")) {
                        id = param1[1];
                    }
                }
            } else {
                if (url.contains("embed")) {
                    id = url.substring(url.lastIndexOf("/") + 1);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

}