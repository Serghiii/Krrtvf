package com.krrt.vl;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConcernContactsFragment extends Fragment {


    public ConcernContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_concern_contacts, container, false);

        ImageView facebook = rootView.findViewById(R.id.img_facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pages/Концерн-РРТ/379302548839081?rf=449337261848187"));
                startActivity(browserIntent);            }
        });

        return rootView;
    }

}
