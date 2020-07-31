package com.krrt.vl;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PartnersFmradioFragment extends Fragment {

    private List<Partner> partnars = new ArrayList();
    ListView partnersList;

    public PartnersFmradioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInitialData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_partners_fmradio, container, false);

        partnersList = (ListView) rootView.findViewById(R.id.partnersList);
        PartnerAdapter partnerAdapter = new PartnerAdapter(getContext(), R.layout.partner_list_item, partnars);
        partnersList.setAdapter(partnerAdapter);

        return rootView;
    }

    private void setInitialData(){
        partnars.add(new Partner ("Українське радіо", "http://www.nrcu.gov.ua", R.drawable.logo_ur));
        partnars.add(new Partner ("Хіт FM", "https://www.hitfm.ua/player", R.drawable.logo_hitfm));
        partnars.add(new Partner ("FM Галичина", "http://www.galychyna.fm", R.drawable.logo_fm20galichina));
        partnars.add(new Partner ("Relax", "https://www.radiorelax.ua", R.drawable.logo_relax));
        partnars.add(new Partner ("Русское радио Україна", "http://www.rusradio.ua", R.drawable.logo_rusradio));
        partnars.add(new Partner ("Шансон", "http://www.shanson.ua", R.drawable.logo_shanson));
        partnars.add(new Partner ("Львівська хвиля", "http://lviv.fm", R.drawable.logo_lvivhv));
        partnars.add(new Partner ("Сім'я і дім", "http://radio.simya.com.ua", R.drawable.logo_sid));
    }

}
