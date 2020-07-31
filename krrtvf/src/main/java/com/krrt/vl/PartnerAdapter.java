package com.krrt.vl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PartnerAdapter extends ArrayAdapter<Partner> {

    private LayoutInflater inflater;
    private int layout;
    private List<Partner> partners;

    public PartnerAdapter(Context context, int resource, List<Partner> partners) {
        super(context, resource, partners);
        this.partners = partners;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView flagView = (ImageView) view.findViewById(R.id.pic);
        TextView nameView = (TextView) view.findViewById(R.id.name);

        Partner partner = partners.get(position);

        flagView.setImageResource(partner.getResource());
        nameView.setText(partner.getName());

        return view;
    }

}
