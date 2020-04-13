package com.example.apsolutiostockinvestment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Date;

import io.realm.Realm;

public class AddStock extends DialogFragment {
    private ImageButton close;
    private AutoCompleteTextView compname;
    private Button adddone;
    private EditText price,units;
    private TextView totalamount;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.popup_addaccount,container,false);
        close=view.findViewById(R.id.closebtn);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        compname=view.findViewById(R.id.stockname);
        compname.setAdapter(new StockAutocompleteAdapter(getActivity()));
        price=view.findViewById(R.id.unitprice);
        units=view.findViewById(R.id.unit);
        totalamount=view.findViewById(R.id.price);
        adddone=view.findViewById(R.id.adddone);

        adddone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm realm=Realm.getDefaultInstance();
                Stocks stocks=realm.where(Stocks.class).equalTo("company_ame",compname.getText().toString())
                        .or().equalTo("security_name",compname.getText().toString()).findFirst();
                if(stocks!=null)
                {
                    realm.beginTransaction();
                    UserStock userStock=new UserStock();
                    Date d=new Date();
                    userStock.setId(d.toString());
                    userStock.setStockname(compname.getText().toString());
                    userStock.setUnits(Long.parseLong(units.getText().toString()));
                    userStock.setPrice(Double.parseDouble(price.getText().toString()));
                    userStock.setTotalamount(Double.parseDouble(totalamount.getText().toString().replaceAll("\u20b9","").trim()));
                    userStock.setDate(d);
                    realm.copyToRealm(userStock);
                    realm.commitTransaction();
                    totalamount.setText("");
                    price.setText("");
                    units.setText("");
                    compname.setText("");
                    compname.requestFocus();

                    DialogFragment dialogFragment=new AddSuccessAnimationDialog();
                    FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                    dialogFragment.show(fragmentTransaction,null);
                }
                else
                    Toast.makeText(getActivity(), "Stock not in list", Toast.LENGTH_LONG).show();
            }
        });


        //check whether unit is whole number or not

//        final TextView totalamount=(TextView)view.findViewById(R.id.price);
//        final EditText price = (EditText) view.findViewById(R.id.unitprice);
//        final EditText units = (EditText) view.findViewById(R.id.unit);
        units.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                double unit;
                String str=s.toString();
                if(str.isEmpty()){
                    unit=0.0;
                }
                else{ unit=Double.parseDouble(str);}
                if(unit%1!=0){
                    units.setError("No. of Units must be a whole number");

                }
                else if(!(s.toString().isEmpty())){
                    price.setFocusableInTouchMode(true);
                }
                else{
                    price.setFocusableInTouchMode(false);
                }

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(!(s.toString().isEmpty())){
                    price.setFocusableInTouchMode(true);

                }
                else{
                    price.setFocusableInTouchMode(false);
                }



            }
        });



        // to calculate the total amount

        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (units.getText().toString().isEmpty()) {
                    units.setError("Enter no. of units");

                }

            }
        });

        price.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                double rate,unit;
                if(units.getText().toString().isEmpty()){
                    unit=0.0;
                }
                else {
                    unit = Double.parseDouble(units.getText().toString());
                }
                String s2 = s.toString();
                if(s2.isEmpty()){
                    rate=0.0;
                }
                else {
                    rate = Double.parseDouble(s2);
                }
                double total_price = unit * rate;

                totalamount.setText("\u20b9   " + String.format("%.2f",total_price));


            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

                if (units.getText().toString().isEmpty()) {
                    units.setError("Enter no. of units");
//                    price.setText(null);
                }
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                double rate,unit;
                if(units.getText().toString().isEmpty()){
                    unit=0.0;
                }
                else {
                    unit = Double.parseDouble(units.getText().toString());
                }
                String s1 = s.toString();
                if(s1.isEmpty()){
                    rate=0.0;
                }
                else {
                    rate = Double.parseDouble(s1);
                }
                double total_price = unit * rate;

                totalamount.setText("\u20b9   " + String.format("%.2f",total_price));



            }



        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    }

}
