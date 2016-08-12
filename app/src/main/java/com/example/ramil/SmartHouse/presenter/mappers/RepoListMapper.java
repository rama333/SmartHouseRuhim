package com.example.ramil.SmartHouse.presenter.mappers;

import com.example.ramil.SmartHouse.model.dto.RepoDTO;
import com.example.ramil.SmartHouse.presenter.vo.Repository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Ramil on 03.06.2016.
 */
public class RepoListMapper implements Func1<List<RepoDTO>, List<Repository>> {


    @Inject
    public RepoListMapper() {
    }

    @Override
    public List<Repository> call(List<RepoDTO> repositoryDTOs) {
        if (repositoryDTOs == null) {
            return null;
        }
        List<Repository> repoList = Observable.from(repositoryDTOs)
                .map(repoDTO -> new Repository(repoDTO.getName(), ""))
                .toList()
                .toBlocking()
                .first();
        return repoList;
    }

}
