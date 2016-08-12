package com.example.ramil.SmartHouse.view.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramil.SmartHouse.ActivityCallback;
import com.example.ramil.SmartHouse.R;
import com.example.ramil.SmartHouse.presenter.BasePresenter;
import com.example.ramil.SmartHouse.presenter.MyHomeListPresenter;
import com.example.ramil.SmartHouse.presenter.vo.Room;
import com.example.ramil.SmartHouse.view.adapters.RoomAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ramil on 26.07.2016.
 */
public class MyHomeFragment extends BaseFragment implements MyHomeView {

    @Bind(R.id.rv)
    RecyclerView recyclerViewU;

    private MyHomeListPresenter myHomeListPresenter = new MyHomeListPresenter(this);

    private RoomAdapter roomAdapterU;


    private ProgressDialog progressDialog;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    private LinearLayoutManager linearLayoutManager;

    ActivityCallback activityCallback;
    //TODO add ActivityCallback

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            //TODO edit activity
            activityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_home, container, false);
        ButterKnife.bind(this, view);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Загрузка..");

        roomAdapterU = new RoomAdapter(new ArrayList<>(),true , getActivity() , myHomeListPresenter);

        myHomeListPresenter.showRoomList();

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                LinearLayoutManager.VERTICAL);

        linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);

        recyclerViewU.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewU.setAdapter(roomAdapterU);

        return view;
    }

    @Override
    public void showList(List<Room> list) {
        roomAdapterU.setRoomList(list);
    }

    @Override
    public void startFragmentDevice(int id) {

        activityCallback.startFragmentDevice(id);
    }

    @Override
    public void ShowProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void DismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void ShowError(String error) {
        makeToast(error);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    private void makeToast(String text) {
        Snackbar.make(recyclerViewU, text, Snackbar.LENGTH_LONG).show();
    }

}