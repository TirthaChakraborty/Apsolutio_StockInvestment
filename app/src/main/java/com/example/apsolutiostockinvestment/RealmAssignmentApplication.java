package com.example.apsolutiostockinvestment;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmAssignmentApplication  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

    }
}