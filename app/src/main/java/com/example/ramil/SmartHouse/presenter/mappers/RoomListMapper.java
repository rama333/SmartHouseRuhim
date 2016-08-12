package com.example.ramil.SmartHouse.presenter.mappers;

import com.example.ramil.SmartHouse.model.dto.RoomsDTO;
import com.example.ramil.SmartHouse.presenter.vo.Room;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func1;

/**
 * Created by Ramil on 27.07.2016.
 */
public class RoomListMapper implements Func1<RoomsDTO, List<Room>> {

    @Override
    public List<Room> call(RoomsDTO roomsDTOs) {
        List<Room> repoList = new ArrayList<>();
        for(int i = 0; i < roomsDTOs.getData().getRooms().size(); i++) {
            repoList.add(new Room(roomsDTOs.getData().getRooms().get(i).getId(), roomsDTOs.getData().getRooms().get(i).getUrl(),
                    roomsDTOs.getData().getRooms().get(i).getName()));
        }
        return repoList;
    }
}
