package com.example.ramil.SmartHouse.presenter.home;

import android.os.Bundle;
import android.util.Log;

import com.example.ramil.SmartHouse.presenter.BasePresenter;
import com.example.ramil.SmartHouse.presenter.mappers.RoomListMapper;
import com.example.ramil.SmartHouse.presenter.vo.Room;
import com.example.ramil.SmartHouse.view.fragments.rooms.MyHomeView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;

/**
 * Created by Ramil on 26.07.2016.
 */
public class HomeListPresenter extends BasePresenter {

    private static final String BUNDLE_MY_HOME_LIST_KEY = "BUNDLE_MY_HOME_LIST_KEY";

    private MyHomeView myHomeView;

    private RoomListMapper roomListMapper = new RoomListMapper();

    private List<Room> roomList;

    public HomeListPresenter(MyHomeView myHomeView) {
        this.myHomeView = myHomeView;
    }

    public void showRoomList(final boolean progress) {

        if (progress) myHomeView.ShowProgressDialog();
        Subscription subscription = dataRepository.getRoomss()
                .map(roomListMapper)
                .subscribe(new Observer<List<Room>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable onError) {
                        myHomeView.DismissProgressDialog(progress);
                        myHomeView.ShowError(onError.getMessage());
                    }

                    @Override
                    public void onNext(List<Room> rooms) {
                        if (rooms != null && !rooms.isEmpty()) {
                            roomList = rooms;
                            myHomeView.showList(rooms);
                            myHomeView.DismissProgressDialog(progress);
                        }
                    }
                });
                /*rooms -> {
                    if (rooms != null && !rooms.isEmpty()) {
                        roomList = rooms;
                        myHomeView.showList(rooms);
                        myHomeView.DismissProgressDialog(progress);
                    }
                }, onError -> {
                    myHomeView.DismissProgressDialog(progress);
                    myHomeView.ShowError(onError.getMessage());
                }*/

        addSubscription(subscription);
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            roomList = (List<Room>) savedInstanceState.getSerializable(BUNDLE_MY_HOME_LIST_KEY);
            Log.d("TAG", "ok state");
        }

        if(!isRoomListEmpty()) {
            myHomeView.showList(roomList);
            Log.d("TAG", "ok list");
        }
    }

    public void startFragmentDevice(int id, String name) {
        myHomeView.startFragmentDevice(id, name);
    }

    public void onSaveInstanceState(Bundle outState) {
        if(!isRoomListEmpty()) {
            outState.putSerializable(BUNDLE_MY_HOME_LIST_KEY, new ArrayList<>(roomList));}
    }

    private boolean isRoomListEmpty() { return roomList == null || roomList.isEmpty(); }
}
