package com.krrt.vl;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesTariffsFragment extends Fragment {


    public ServicesTariffsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_services_tariffs, container, false);

        ImageButton btn_tariffs = rootView.findViewById(R.id.btn_tariffs);
        btn_tariffs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://rrt-volyn.com.ua/tariffs")));
            }
        });

        return rootView;
    }

}
