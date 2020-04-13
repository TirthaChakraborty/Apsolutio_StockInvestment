package com.example.apsolutiostockinvestment.mainpackage;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.apsolutiostockinvestment.R;
import com.example.apsolutiostockinvestment.realmobjects.UserStock;

import io.realm.Realm;
import io.realm.RealmResults;

public class DisplayStocksActivity extends AppCompatActivity {

    private Context mcontext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mcontext=this;

        Realm.init(mcontext);
        Realm realm=Realm.getDefaultInstance();
        RealmResults<UserStock> userStock=realm.where(UserStock.class).findAll();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(userStock.isEmpty())
        {
//            Toast.makeText(this, "Fragment to be loaded", Toast.LENGTH_SHORT).show();
            ft.add(R.id.frame_container,new EmptyInvFragment());
        }
        else  {

            //Log.i("dashboard", "task exist");

            ft.add(R.id.frame_container, new NonEmptyInvFragment(userStock));
        }



        ft.commit();
        //realm.close();

    }
}
