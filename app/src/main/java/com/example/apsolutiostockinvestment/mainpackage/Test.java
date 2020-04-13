package com.example.apsolutiostockinvestment.mainpackage;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apsolutiostockinvestment.R;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaystocks);
    }
//    public void check(View view) {
//
//        try {
//
//            File csvNfile=new File(getExternalFilesDir(null),"/All_NSE_Scrips.csv");
//            final CSVParser parser =
//                    new CSVParserBuilder()
//                            .withSeparator(',')
//                            .withIgnoreQuotations(true)
//                            .build();
//            CSVReader reader_N = new CSVReaderBuilder(new FileReader(csvNfile)).withCSVParser(parser).build();
//
//            // read line by line for NSE
//
//
//            String[] record = null;
//
//            while ((record = reader_N.readNext()) != null) {
//                createStockItem("N", record);
//            }
//
//            reader_N.close();
//
//            // read line by line for BSE
//
//
//
//            File csvBfile = new File(getExternalFilesDir(null), "All_BSE_Scrips.csv");
//            CSVReader reader_B = new CSVReaderBuilder(new FileReader(csvBfile)).withCSVParser(parser).build();
//
//            String[] record1 = null;
//
//            while ((record1 = reader_N.readNext()) != null) {
//                createStockItem("B", record1);
//            }
//
//            reader_B.close();
//
//            Toast.makeText(this, "Read", Toast.LENGTH_SHORT).show();
//
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(this, "The specified file was not found", Toast.LENGTH_SHORT).show();
//        }
//
//
//    }
//
//
//
//    public void createStockItem(String t, String[] record){
//
//        Realm realm=Realm.getDefaultInstance();
//        realm.beginTransaction();
//        try{
//            Number currentIdNum=realm.where(Stocks.class).max("stock_id");
//            int nextId = (currentIdNum == null) ? 1 : currentIdNum.intValue() + 1;
//            Stocks stock=realm.createObject(Stocks.class,nextId);
//            stock.setType(t);
//
//           if(t.equals("B")) {
//               //stock.setStock_id(nextId);
//               stock.setSecurity_code(Integer.parseInt(record[0]));
//               stock.setSecurity_id(record[2]);
//               stock.setSecurity_name(record[3]);
//               stock.setStatus(record[4]);
//               stock.setGroup(record[5]);
//               stock.setFace_value(Integer.parseInt(record[6]));
//               stock.setIsin(record[7]);
//               stock.setIndustry(record[8]);
//               stock.setInstrument(record[9]);
//           }
//               if(t.equals("N")){
//                   // stock.setStock_id(nextId);
//                    stock.setSymbol(record[0]);
//                    stock.setCompany_ame(record[1]);
//                    stock.setSeries(record[2]);
//                    stock.setListing_date(record[3]);
//                    stock.setPaid_up_value(Integer.parseInt(record[4]));
//                    stock.setMarket_lot(Integer.parseInt(record[5]));
//                    stock.setIsin(record[6]);
//                    stock.setFace_value(Integer.parseInt(record[7]));
//
//            }
////            SimpleDateFormat f=new SimpleDateFormat("dd/MM/yyyy");
//           // Date d=f.parse(fromDate.getText().toString());
//
//
//
//
//            realm.commitTransaction();
//            Toast.makeText(this, "Realm Loaded!!", Toast.LENGTH_SHORT).show();
//
//            // Intent intent=new Intent(this,Dashboard.class);
//            //startActivity(intent);
//
//        }
//        catch(Exception e){realm.cancelTransaction();
//            Toast.makeText(this, "realm failed", Toast.LENGTH_SHORT).show();}
//        finally {
//            realm.close();
//        }
//
//    }
}
