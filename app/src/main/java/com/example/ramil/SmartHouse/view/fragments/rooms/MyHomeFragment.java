package com.example.ramil.SmartHouse.view.fragments.rooms;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.ramil.SmartHouse.R;
import com.example.ramil.SmartHouse.activity.ActivityCallback;
import com.example.ramil.SmartHouse.presenter.BasePresenter;
import com.example.ramil.SmartHouse.presenter.home.HomeListPresenter;
import com.example.ramil.SmartHouse.presenter.vo.Room;
import com.example.ramil.SmartHouse.view.adapters.rooms.RoomAdapter;
import com.example.ramil.SmartHouse.view.fragments.BaseFragment;

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
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;

    private HomeListPresenter myHomeListPresenter = new HomeListPresenter(this);

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

        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Моя квартира");

        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        progressDialog = new ProgressDialog(getActivity());

        roomAdapterU = new RoomAdapter(new ArrayList<>(),true , getActivity() , myHomeListPresenter);

        myHomeListPresenter.onCreate(savedInstanceState);

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            myHomeListPresenter.showRoomList(false);
        });

        myHomeListPresenter.showRoomList(true);

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
    public void startFragmentDevice(int id, String name) {
        activityCallback.startFragmentDevice(id, name);
    }

    @Override
    public void ShowProgressDialog() {
        progressDialog.setMessage("Загрузка..");
        progressDialog.show();
    }

    @Override
    public void DismissProgressDialog(boolean progress) {
        if (progress) progressDialog.dismiss();
        else swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void ShowError(String error) {
        makeToast(error);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        myHomeListPresenter.onSaveInstanceState(outState);
    }

    @Override
    protected BasePresenter getPresenter() {
        return myHomeListPresenter;
    }

    private void makeToast(String text) {
        Snackbar.make(recyclerViewU, text, Snackbar.LENGTH_LONG).show();
    }

}