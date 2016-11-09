package com.example.ramil.SmartHouse.view.adapters.devices;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramil.SmartHouse.R;
import com.example.ramil.SmartHouse.presenter.devices.LightDeviceListPresenter;
import com.example.ramil.SmartHouse.presenter.vo.Device;
import com.example.ramil.SmartHouse.view.adapters.BaseAdapter;
import com.example.ramil.SmartHouse.view.fragments.devices.LightTabView;

import java.util.List;
import java.util.Random;

/**
 * Created by Ramil on 02.08.2016.
 */
public class DeviceAdapter extends BaseAdapter<Device> {

    private Context context;

    private LightDeviceListPresenter myDeviceListPresenter;
    private LightTabView myDeviceView;

    int[] devices = {R.layout.torsh_view, R.layout.lamp_view, R.layout.floor_lamp_view, R.layout.shade_view };

    public DeviceAdapter(List list, Context context, LightDeviceListPresenter myDeviceListPresenter, LightTabView myDeviceView) {
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
        switch (viewType) {
            case 1:
                return inflate(R.layout.torsh_view, viewGroup);
            case 2:
                return inflate(R.layout.lamp_view, viewGroup);
            case 3:
                return inflate(R.layout.floor_lamp_view, viewGroup);
            case 4:
                return inflate(R.layout.light_view, viewGroup);
            case 5:
                return inflate(R.layout.dark_light_view, viewGroup);
            case 6:
                return inflate(R.layout.shade_view, viewGroup);
            case 7:
                return inflate(R.layout.torsh_view, viewGroup);
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

            holder.cv.setOnClickListener(view -> myDeviceListPresenter.startFragmentDeviceControl(device.getId(), device.getType()));
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
