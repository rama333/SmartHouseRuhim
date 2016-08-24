package com.example.ramil.SmartHouse.view.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramil.SmartHouse.R;
import com.example.ramil.SmartHouse.presenter.MyHomeListPresenter;
import com.example.ramil.SmartHouse.presenter.vo.Room;

import java.util.List;


/**
 * Created by Ramil on 28.07.2016.
 */
public class RoomAdapter extends BaseAdapter<Room>{

    final boolean itemLayout;

    private Context context;
    private MyHomeListPresenter myHomeListPresenter;

    public RoomAdapter(List<Room> list, boolean itemLayout , Context context, MyHomeListPresenter myHomeListPresenter){
        super(list);
        this.itemLayout = itemLayout;
        this.context = context;
        this.myHomeListPresenter = myHomeListPresenter;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_home_view_u, viewGroup, false);

        switch(i){
            case 1:
                return inflate(view, R.mipmap.sproom);
            case 2:
                return inflate(view, R.mipmap.kit);
            default:
                return inflate(view, R.mipmap.van);

        }
    }

    private ViewHolder inflate(View view, int id) {

        View lay = view.findViewById(R.id.image);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
        Drawable dr = new BitmapDrawable(bitmap);
        lay.setBackground(dr);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder ViewHolder, int i) {

        ViewHolder.Name.setText(list.get(i).getName());

        if(i == 0) {
            StaggeredGridLayoutManager.LayoutParams layoutParams =
                    (StaggeredGridLayoutManager.LayoutParams) ViewHolder.itemView.getLayoutParams();
            layoutParams.setFullSpan(true);
        }

        ViewHolder.cv.setOnClickListener(view -> myHomeListPresenter.startFragmentDevice(list.get(i).getId()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getId();
    }

    public void setRoomList(List<Room> list) {
        this.list = list;
        notifyDataSetChanged();
    }

}
