package com.example.apsolutiostockinvestment.mainpackage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apsolutiostockinvestment.R;
import com.example.apsolutiostockinvestment.adapters.UserStockAdapter;
import com.example.apsolutiostockinvestment.realmobjects.UserStock;

import io.realm.Realm;
import io.realm.RealmResults;

public class NonEmptyInvFragment extends Fragment implements UserStockAdapter.OnItemClickListener {
    RealmResults<UserStock> userStocks;
    static int count;

    NonEmptyInvFragment(RealmResults<UserStock> results) {
        userStocks = results;
        count = 0;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_notempty, container, false);
        RecyclerView recyclerView = (RecyclerView) rootview.findViewById(R.id.stock_recyclerview);
        // recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UserStockAdapter mAdapter = new UserStockAdapter(userStocks, this);
        recyclerView.setAdapter(mAdapter);
        return rootview;
    }






    public void onItemClick(int position)
    {

    }

}
