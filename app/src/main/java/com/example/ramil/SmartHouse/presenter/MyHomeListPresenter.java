package com.example.ramil.SmartHouse.presenter;

import com.example.ramil.SmartHouse.presenter.mappers.RoomListMapper;
import com.example.ramil.SmartHouse.presenter.vo.Room;
import com.example.ramil.SmartHouse.view.fragments.MyHomeView;

import java.util.List;

import rx.Observer;
import rx.Subscription;

/**
 * Created by Ramil on 26.07.2016.
 */
public class MyHomeListPresenter extends BasePresenter {

    private static final String BUNDLE_MY_HOME_LIST_KEY = "BUNDLE_MY_HOME_LIST_KEY";

    private MyHomeView myHomeView;

    private RoomListMapper roomListMapper = new RoomListMapper();

    private List<Room> roomList;

    public MyHomeListPresenter(MyHomeView myHomeView) {
        this.myHomeView = myHomeView;
    }

    public void showRoomList() {

        myHomeView.ShowProgressDialog();
        Subscription subscription = dataRepository.getRoomss()
                .map(roomListMapper)
                .subscribe(new Observer<List<Room>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        myHomeView.DismissProgressDialog();
                        myHomeView.ShowError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Room> rooms) {
                        if (rooms != null && !rooms.isEmpty()) {
                            roomList = rooms;
                            myHomeView.showList(rooms);
                            myHomeView.DismissProgressDialog();
                        }
                    }
                });
        addSubscription(subscription);
    }

    public void clickRepo(int id) {
        myHomeView.startFragmentDevice(id);
    }



}
