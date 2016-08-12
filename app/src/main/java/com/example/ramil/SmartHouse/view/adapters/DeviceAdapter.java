package com.example.ramil.SmartHouse.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramil.SmartHouse.R;
import com.example.ramil.SmartHouse.presenter.MyDeviceListPresenter;
import com.example.ramil.SmartHouse.presenter.vo.Device;
import com.example.ramil.SmartHouse.view.fragments.MyDeviceView;

import java.util.List;

/**
 * Created by Ramil on 02.08.2016.
 */
public class DeviceAdapter extends BaseAdapter<Device> {


    private Context context;

    private MyDeviceListPresenter myDeviceListPresenter;
    private MyDeviceView myDeviceView;

    public DeviceAdapter(List list, Context context, MyDeviceListPresenter myDeviceListPresenter, MyDeviceView myDeviceView) {
        super(list);
        this.context = context;
        this.myDeviceListPresenter = myDeviceListPresenter;
        this.myDeviceView = myDeviceView;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;
        switch (viewType) {
            case 1:
                v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.torsh_view, viewGroup, false);
                break;
            case 2:
                v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lamp_view, viewGroup, false);
                break;
            case 3:
                v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.floor_lamp_view, viewGroup, false);
                break;
            case 4:
                v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.light_view, viewGroup, false);
                break;
            case 5:
                v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dark_light_view, viewGroup, false);
                break;
            case 6:
                v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shade_view, viewGroup, false);
                break;
            case 7:
                v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.torsh_view, viewGroup, false);
                break;
            default:
                v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dark_light_view, viewGroup, false);
        }
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {

            //holder.cv.setPreventCornerOverlap(false);

            holder.Name.setText(list.get(position).getType());
            holder.cv.setCardBackgroundColor(context.getResources().getColor(R.color.colorYellow));
            holder.cv.setOnClickListener(view -> myDeviceListPresenter.clickRepo());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getId();
    }

    public void setDeviceList(List<Device> list) {
        this.list = list;
        notifyDataSetChanged();
    }

}
