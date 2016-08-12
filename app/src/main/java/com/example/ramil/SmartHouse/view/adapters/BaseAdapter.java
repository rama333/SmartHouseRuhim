package com.example.ramil.SmartHouse.view.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ramil.SmartHouse.R;

import java.util.List;

/**
 * Created by Ramil on 29.07.2016.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    protected List<T> list;

    public BaseAdapter(List<T> list) {
        this.list = list;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView Name;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            Name = (TextView)itemView.findViewById(R.id.person_name);
            //personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }
}
