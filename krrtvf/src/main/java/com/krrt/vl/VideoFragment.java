package com.krrt.vl;


import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {

    int videoPosition = 0;
    VideoView videoView;

    public VideoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        MediaController mediaController = new MediaController(getContext());
        videoView = rootView.findViewById(R.id.videoView);
        PrepareOrientation();
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(String.format(getResources().getString(R.string.video_url), getArguments().getLong("id"), getArguments().getString("videofilename"))));
        videoView.requestFocus();
        videoView.start();
        return rootView;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        PrepareOrientation();
    }

    @Override
    public void onPause() {
        super.onPause();
        videoPosition = videoView.getCurrentPosition();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (videoPosition > 0)videoView.seekTo(videoPosition);
    }

    private void PrepareOrientation(){
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            DisplayMetrics metrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) videoView.getLayoutParams();
            params.height = metrics.heightPixels;
            params.gravity = Gravity.CENTER_HORIZONTAL;
            videoView.setLayoutParams(params);
        }
    }

}
