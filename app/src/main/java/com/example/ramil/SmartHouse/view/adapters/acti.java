package com.example.ramil.SmartHouse.view.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.ramil.SmartHouse.presenter.vo.Room;

import java.util.List;

/**
 * Created by Ramil on 28.07.2016.
 */
public class acti extends Activity {

    private List<Room> persons;
    private RecyclerView rv, rv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        setContentView(R.layout.my_home);

        rv=(RecyclerView)findViewById(R.id.rv);
       // rv1=(RecyclerView)findViewById(R.id.rv1);

        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);

        StaggeredGridLayoutManager llm1 = new StaggeredGridLayoutManager(2, // кол-во колонок в сетке
                LinearLayoutManager.VERTICAL);

        rv.setHasFixedSize(true);
        //rv1.setHasFixedSize(true);

        rv.setLayoutManager(llm);
        rv1.setLayoutManager(llm1);

        initializeData();

        RoomAdapter adapter = new RoomAdapter(persons, false);
        RoomAdapter adapter1 = new RoomAdapter(persons, true);

        rv.setAdapter(adapter);

        rv1.setAdapter(adapter1);

        ////



    }

    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Room(1, "http://..", "Спальная"));
        persons.add(new Room(1, "http://..", "Ванная"));
        persons.add(new Room(1, "http://..", "Кухня"));
    }*/

}}
