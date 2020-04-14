package com.example.apsolutiostockinvestment.mainpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.apsolutiostockinvestment.R;
import com.example.apsolutiostockinvestment.realmobjects.UserStock;

import io.realm.Realm;
import io.realm.RealmResults;

public class DisplayStocksActivity extends AppCompatActivity {

    private Context mcontext;
    private RadioGroup bottomnav;
    private RadioButton home;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaystocks);

        mcontext=this;


        //set ActionBar
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("Portfolio");
        bottomnav = findViewById(R.id.radioGroup1);
        home=findViewById(R.id.stocks);

        bottomnav.clearCheck();
        home.setChecked(true);

        navigate(bottomnav);

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
       // realm.close();

    }

    public void navigate(RadioGroup bottomnav){

        bottomnav.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkid) {
                Intent in;
                switch (checkid) {
                    case R.id.overview:
                        in = new Intent(DisplayStocksActivity.this, MainActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.stocks:
                      //this is the page
                        break;
                    case R.id.mf:
                        //code here
                        break;
                    case R.id.ulip:
                        //code here
                        break;

                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.exit:
            Intent intent = new Intent(mcontext, MainActivity.class);
            startActivity(intent);
            return(true);

    }
        return(super.onOptionsItemSelected(item));
    }
}
