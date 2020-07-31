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
public class PartnersCellularFragment extends Fragment {

    private List<Partner> partnars = new ArrayList();
    ListView partnersList;

    public PartnersCellularFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_partners_cellular, container, false);

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
        partnars.add(new Partner ("Київстар", "http://www.kyivstar.ua", R.drawable.logo_kyivstar));
        partnars.add(new Partner ("Lifacell", "http://www.lifecell.ua", R.drawable.logo_lifecell));
        partnars.add(new Partner ("Vodafone", "https://www.vodafone.ua", R.drawable.logo_vodafone));
        partnars.add(new Partner ("Інтертелеком", "http://www.intertelecom.ua", R.drawable.logo_intertelecom));
    }

}
