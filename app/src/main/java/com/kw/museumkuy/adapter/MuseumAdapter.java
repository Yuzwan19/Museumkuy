package com.kw.museumkuy.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kw.museumkuy.R;
import com.kw.museumkuy.model.MuseumModel;

import java.util.List;

/**
 * Created by Izcax on 12/6/17.
 */

public class MuseumAdapter extends BaseAdapter {
    // params
    List<MuseumModel> listItem;
    Activity activity;

    public MuseumAdapter(Activity activity, List<MuseumModel> listItem){
        this.activity = activity;
        this.listItem = listItem;
    }

    //method ini akan menentukan seberapa banyak data yang akan ditampilkan didalam ListView
    //listItem.size() == jumlah data dalam object List yang ada
    @Override
    public int getCount() {
        return listItem.size();
    }

    //method ini untuk mengakses per-item objek dalam list
    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //method ini yang akan menampilkan baris per baris dari item yang ada di ListView
    //dengan menggunakan pattern ViewHolder untuk optimasi performa dari ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_museum, null);
            holder.txtAlamat = (TextView)view.findViewById(R.id.item_alamat);
            holder.txtDesc = (TextView)view.findViewById(R.id.item_desc);
            holder.txtTitle = (TextView)view.findViewById(R.id.item_title);

            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }

        MuseumModel model = (MuseumModel) getItem(position);
        holder.txtTitle.setText(model.getNama());
        holder.txtDesc.setText(model.getDeskripsi());
        holder.txtAlamat.setText(String.format("%s\nKoordinat: %s, %s",
                model.getAlamat(), model.getLatitude(), model.getLongitude()));

        return view;
    }

    static class ViewHolder{

        TextView txtTitle, txtAlamat, txtDesc;
    }
}
