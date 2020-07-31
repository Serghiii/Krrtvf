package com.krrt.vl;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConcernAboutFragment extends Fragment {

    private ImageView rrt,earth;
    private Animation anim1,anim2,anim3,anim4;

    public ConcernAboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_concern_about, container, false);

        earth = rootView.findViewById(R.id.img_earth);
        earth.setElevation(1);
        rrt = rootView.findViewById(R.id.img_rrt);
        rrt.setElevation(0);
        anim1 = AnimationUtils.loadAnimation(container.getContext(), R.anim.translate_one);
        anim2 = AnimationUtils.loadAnimation(container.getContext(), R.anim.translate_two);
        anim3 = AnimationUtils.loadAnimation(container.getContext(), R.anim.translate_three);
        anim4 = AnimationUtils.loadAnimation(container.getContext(), R.anim.translate_four);
        anim1.setAnimationListener(animListener);
        anim2.setAnimationListener(animListener);
        anim3.setAnimationListener(animListener);
        anim4.setAnimationListener(animListener);
        rrt.startAnimation(anim1);

        return rootView;
    }

    Animation.AnimationListener animListener = new Animation.AnimationListener() {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation arg0) {
            if (arg0 == anim1) {
                rrt.setElevation(3);
                rrt.startAnimation(anim2);
            }else
            if (arg0 == anim2){
                rrt.setElevation(3);
                rrt.startAnimation(anim3);
            }else
            if (arg0 == anim3){
                rrt.setElevation(0);
                rrt.startAnimation(anim4);
            }else
            if (arg0 == anim4){
                rrt.setElevation(0);
                rrt.startAnimation(anim1);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

    };

}
