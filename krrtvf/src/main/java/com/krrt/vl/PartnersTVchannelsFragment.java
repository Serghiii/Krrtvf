package com.krrt.vl;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PartnersTVchannelsFragment extends Fragment {

    private List<Partner> partnars = new ArrayList();
    ListView partnersList;

    public PartnersTVchannelsFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_partners_tvchannels, container, false);

        partnersList = (ListView) rootView.findViewById(R.id.partnersList);
        PartnerAdapter partnerAdapter = new PartnerAdapter(getContext(), R.layout.partner_list_item, partnars);
        partnersList.setAdapter(partnerAdapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Partner selectedPartner = (Partner) parent.getItemAtPosition(position);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(selectedPartner.getRef())));
            }
        };
        partnersList.setOnItemClickListener(itemListener);

        return rootView;
    }

    private void setInitialData(){
        partnars.add(new Partner ("UA:Перший", "http://1tv.com.ua", R.drawable.logo_ua1));
        partnars.add(new Partner ("Інтер","http://inter.ua", R.drawable.logo_inter));
        partnars.add(new Partner ("1+1", "http://1plus1.ua", R.drawable.logo_1p1));
        partnars.add(new Partner ("5 канал", "http://www.5.ua", R.drawable.logo_5));
        partnars.add(new Partner ("Україна", "http://kanalukraina.tv", R.drawable.logo_wtrk_ukraina));
        partnars.add(new Partner ("НТН", "http://ntn.ua", R.drawable.logo_ntn));
        partnars.add(new Partner ("К1", "http://www.k1.ua", R.drawable.logo_k1));
        partnars.add(new Partner ("ТЕТ", "http://tet.tv", R.drawable.logo_tet));
        partnars.add(new Partner ("Аверс", "http://mediaavers.com", R.drawable.logo_avers));
        partnars.add(new Partner ("12 канал", "https://12kanal.com", R.drawable.logo_12));
    }

}
