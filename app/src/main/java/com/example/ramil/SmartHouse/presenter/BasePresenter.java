package com.example.ramil.SmartHouse.presenter;

import com.example.ramil.SmartHouse.model.Model;
import com.example.ramil.SmartHouse.model.ModelImpl;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ramil on 26.07.2016.
 */
public abstract class BasePresenter implements Presenter {

    protected Model dataRepository = new ModelImpl();
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}
