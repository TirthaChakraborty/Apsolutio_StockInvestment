package com.example.apsolutiostockinvestment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class StockAutocompleteAdapter extends BaseAdapter implements Filterable {
    private Context mContext;
    private ArrayList<Stocks> mResult = new ArrayList<>();
    private LayoutInflater inflater;
    public StockAutocompleteAdapter(Context context)
    {
        mContext=context;
    }
    @Override
    public int getCount() {
        return mResult.size();
    }

    @Override
    public Object getItem(int i) {
        if(mResult.get(i).getSecurity_name()==null)
        return mResult.get(i).getCompany_ame();
        else return mResult.get(i).getCompany_ame();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = inflater.inflate(R.layout.autocompleteitem, viewGroup, false);
        Stocks s=mResult.get(i);
        TextView textView=view.findViewById(R.id.autocompletecompname);
        if(s.getSecurity_name()==null)
        textView.setText(s.getCompany_ame());
        else
            textView.setText(s.getSecurity_name());
        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                return null;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if(charSequence!=null)
                {
                    getCompanyList(charSequence.toString());
                    notifyDataSetChanged();
                }
                else
                    notifyDataSetInvalidated();

            }
        };
    }
    private void getCompanyList(String s)
    {
        Realm realm=Realm.getDefaultInstance();
        RealmResults<Stocks> stocks= realm.where(Stocks.class).contains("company_ame",s).or().contains("security_name",s).findAll();
        mResult.clear();
        mResult.addAll(realm.copyFromRealm(stocks));

    }
}
