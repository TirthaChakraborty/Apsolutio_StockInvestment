package com.example.apsolutiostockinvestment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.realm.Realm;

//import butterknife.OnCheckedChanged;
//import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private RadioGroup bottomnav;
    private Context context;

    private static  final int MY_PERMISSION_REQUEST_STORAGE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;


        //set ActionBar
        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
        getSupportActionBar().setTitle("Portfolio");
        bottomnav = findViewById(R.id.radioGroup1);

        navigate(bottomnav);
        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fb_add);
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment addstock=new AddStock();
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                addstock.show(fragmentTransaction,null);

            }
        });

        add(myFab);



    }
    public void loadcsv(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        int counter = sharedPreferences.getInt("counter", 0);
//        float font_size = editor.getFloat("font_size",20.0f);
        if (counter == 0) {


            copyAsset();   //copy all asset contents to internal storage
            loadRealm();   //load all internal storage contents to realm

            editor.putInt("counter", ++counter);
            editor.commit();
            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
        }
    }



    public void navigate(RadioGroup bottomnav){

        bottomnav.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkid) {
                Intent in;
                switch (checkid) {
                    case R.id.overview:
                        //code here
                        break;
                    case R.id.stocks:
                        in = new Intent(MainActivity.this, Test.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
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

    public void copyAsset() {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            //Log.e("tag", "Failed to get asset file list.", e);
        }
        for(String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(filename);
                File outFile = new File(getExternalFilesDir(null), filename);
                out = new FileOutputStream(outFile);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            } catch(IOException e) {
               // Log.e("tag", "Failed to copy asset file: " + filename, e);
            }
        }
    }
        private void copyFile(InputStream in, OutputStream out) throws IOException {
            byte[] buffer = new byte[1024];
            int read;
            while((read = in.read(buffer)) != -1){
                out.write(buffer, 0, read);
            }
        }


    public void add(FloatingActionButton myFab){
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment addstock=new AddStock();
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                addstock.show(fragmentTransaction,null);
            }
        });




    }


    public void loadRealm() {

        try {

            File csvNfile=new File(getExternalFilesDir(null),"/All_NSE_Scrips.csv");
            final CSVParser parser =
                    new CSVParserBuilder()
                            .withSeparator(',')
                            .withIgnoreQuotations(true)
                            .build();
            CSVReader reader_N = new CSVReaderBuilder(new FileReader(csvNfile)).withCSVParser(parser).build();

            // read line by line for NSE


            String[] record = null;

            while ((record = reader_N.readNext()) != null) {
                createStockItem("N", record);
            }

            reader_N.close();

            // read line by line for BSE



            File csvBfile = new File(getExternalFilesDir(null), "All_BSE_Scrips.csv");
            CSVReader reader_B = new CSVReaderBuilder(new FileReader(csvBfile)).withCSVParser(parser).build();

            String[] record1 = null;

            while ((record1 = reader_N.readNext()) != null) {
                createStockItem("B", record1);
            }

            reader_B.close();

            Toast.makeText(this, "Read", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "The specified file was not found", Toast.LENGTH_SHORT).show();
        }


    }


    public void createStockItem(String t, String[] record){

        Realm realm=Realm.getDefaultInstance();
        realm.beginTransaction();
        try{
            Number currentIdNum=realm.where(Stocks.class).max("stock_id");
            int nextId = (currentIdNum == null) ? 1 : currentIdNum.intValue() + 1;
            Stocks stock=realm.createObject(Stocks.class,nextId);
            stock.setType(t);

            if(t.equals("B")) {
                //stock.setStock_id(nextId);
                stock.setSecurity_code(Integer.parseInt(record[0]));
                stock.setSecurity_id(record[2]);
                stock.setSecurity_name(record[3]);
                stock.setStatus(record[4]);
                stock.setGroup(record[5]);
                stock.setFace_value(Integer.parseInt(record[6]));
                stock.setIsin(record[7]);
                stock.setIndustry(record[8]);
                stock.setInstrument(record[9]);
            }
            if(t.equals("N")){
                // stock.setStock_id(nextId);
                stock.setSymbol(record[0]);
                stock.setCompany_ame(record[1]);
                stock.setSeries(record[2]);
                stock.setListing_date(record[3]);
                stock.setPaid_up_value(Integer.parseInt(record[4]));
                stock.setMarket_lot(Integer.parseInt(record[5]));
                stock.setIsin(record[6]);
                stock.setFace_value(Integer.parseInt(record[7]));

            }
//            SimpleDateFormat f=new SimpleDateFormat("dd/MM/yyyy");
            // Date d=f.parse(fromDate.getText().toString());




            realm.commitTransaction();
            Toast.makeText(this, "Realm Loaded!!"+stock.getIsin(), Toast.LENGTH_SHORT).show();

            // Intent intent=new Intent(this,Dashboard.class);
            //startActivity(intent);

        }
        catch(Exception e){realm.cancelTransaction();
            Toast.makeText(this, "realm failed", Toast.LENGTH_SHORT).show();}
        finally {
            realm.close();
        }

    }



}
