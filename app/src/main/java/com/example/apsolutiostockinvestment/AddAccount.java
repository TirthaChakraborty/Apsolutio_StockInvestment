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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Date;

import io.realm.Realm;

public class AddAccount extends DialogFragment {
    private ImageButton close;
    private AutoCompleteTextView compname;
    private Button adddone;
    private EditText units,price,totalamount;
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
        units=view.findViewById(R.id.unit);
        adddone=view.findViewById(R.id.adddone);
        adddone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm realm=Realm.getDefaultInstance();
                realm.beginTransaction();
                UserStock userStock=new UserStock();
                Date d=new Date();
                userStock.setId(d.toString());
                userStock.setStockname(compname.getText().toString());
                userStock.setDate(d);


                DialogFragment successdialog=new AddSuccessAnimationDialog();
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                successdialog.show(fragmentTransaction,null);
            }
        });


        //check whether unit is whole number or not

        final TextView total=(TextView)view.findViewById(R.id.price);
        final EditText price_per_unit = (EditText) view.findViewById(R.id.unitprice);

        units.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                String str=s.toString();
                double unit=Double.parseDouble(str);
                if(unit%1!=0){
                    units.setError("No. of Units must be a whole number");

                }

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {



            }
        });

        // to calculate the total amount

        price_per_unit.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                if(units.getText().toString().isEmpty()){
                    units.setError("Enter no. of units");
                    price_per_unit.setText(null);
                }
                else {
                    double unit = Double.parseDouble(units.getText().toString());
                    String s1 = s.toString();
                    double rate = Double.parseDouble(s1);
                    double total_price = unit * rate;

                    total.setText("Rs." + (Double.toString(total_price)));
                }

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

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
