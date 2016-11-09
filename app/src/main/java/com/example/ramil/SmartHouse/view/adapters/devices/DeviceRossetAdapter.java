package com.example.ramil.SmartHouse.view.adapters.devices;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramil.SmartHouse.R;
import com.example.ramil.SmartHouse.presenter.devices.RosseteDeviceListPresenter;
import com.example.ramil.SmartHouse.presenter.vo.Device;
import com.example.ramil.SmartHouse.view.adapters.BaseAdapter;
import com.example.ramil.SmartHouse.view.fragments.devices.RosseteTabView;

import java.util.List;
import java.util.Random;

/**
 * Created by Ramil on 29.08.2016.
 */
public class DeviceRossetAdapter extends BaseAdapter<Device> {

    private Context context;

    private RosseteDeviceListPresenter myDeviceRosseteListPresenter;
    private RosseteTabView myDeviceRosseteView;

    int[] devices = {R.layout.tv, R.layout.ventilytor, R.layout.teapot, R.layout.iron };

    public DeviceRossetAdapter(List list, Context context, RosseteDeviceListPresenter myDeviceRosseteListPresenter, RosseteTabView myDeviceRosseteView) {
        super(list);
        this.context = context;
        this.myDeviceRosseteListPresenter = myDeviceRosseteListPresenter;
        this.myDeviceRosseteView = myDeviceRosseteView;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case 1:
                return inflate(R.layout.tv, viewGroup);
            case 2:
                return inflate(R.layout.ventilytor, viewGroup);
            case 3:
                return inflate(R.layout.teapot, viewGroup);
            case 4:
                return inflate(R.layout.iron, viewGroup);
            default:
                Random rnd = new Random();
                return inflate(devices[rnd.nextInt(3)], viewGroup);
        }
    }

    private ViewHolder inflate(int id , ViewGroup viewGroup){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(id, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {

        Device device = list.get(position);

        holder.Name.setText(device.getType());

        String condition = device.getCondition();

        if (condition == "off") {
            holder.cv.setCardBackgroundColor(context.getResources().getColor(R.color.Colorwhite));
        } else {
            holder.cv.setCardBackgroundColor(context.getResources().getColor(R.color.colorYellow));
        }

        holder.cv.setOnClickListener(view -> myDeviceRosseteView.startFragmentRossete(device.getId(), device.getType()));

        //holder.cv.setOnClickListener(view -> myDeviceListPresenter.startFragmentDeviceControl(device.getId(), device.getType()));
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
