package com.example.apsolutiostockinvestment.adapters;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apsolutiostockinvestment.R;
import com.example.apsolutiostockinvestment.realmobjects.UserStock;

import java.text.SimpleDateFormat;

import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class UserStockAdapter extends RecyclerView.Adapter<UserStockAdapter.MyViewHolder>{
    // private final Context mcontent;
    private RealmResults<UserStock> muserStocks;
    OnItemClickListener mlistener;

    public  UserStockAdapter(){}
    public UserStockAdapter(RealmResults<UserStock> data,OnItemClickListener listener) {
        muserStocks=data;
        mlistener=listener;
        // mbooking.addChangeListener((OrderedRealmCollectionChangeListener<RealmResults<Booking>>) this);
    }



    public interface OnItemClickListener
    {

        public void onItemClick(int position);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        Log.i("adapter","view holder called");
        return new MyViewHolder(view,mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        UserStock userStocks=muserStocks.get(position);

// egulo fill korish!!!!!

//        holder.name.setText(userStocks.getStockname());
//        holder.gain.setText();
//        holder.units.setText(String.valueOf(userStocks.getUnits()));
//        holder.mp.setText();
//        holder.value.setText();
//        holder.cagr.setText();
//        holder.cwfd.setText();
//        holder.fd.setText();
//        holder.absolute_return.setText();



    }


    @Override
    public int getItemCount() {
        return muserStocks.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //  private TextView ;
        private TextView name;
        private TextView gain;
        private TextView units;
        private TextView mp;
        private TextView value;
        private TextView cagr;
        private TextView cwfd;
        private TextView fd;
        private TextView absolute_return;
        private TableLayout item;

        private OnItemClickListener mitem;

        public MyViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            //carNo = itemView.findViewById(R.id.car_Number);
            name=itemView.findViewById(R.id.textView1);
            gain=itemView.findViewById(R.id.textView3);
            units=itemView.findViewById(R.id.textView4);
            mp=itemView.findViewById(R.id.textView5);
            value=itemView.findViewById(R.id.textView6);
            cagr=itemView.findViewById(R.id.textView7);
            cwfd=itemView.findViewById(R.id.textView8);
            fd=itemView.findViewById(R.id.textView9);
            absolute_return=itemView.findViewById(R.id.textView10);
            item=itemView.findViewById(R.id.tablelayout1);

            mitem=listener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(getAdapterPosition()!=RecyclerView.NO_POSITION);
                    mitem.onItemClick(getAdapterPosition());
                }
            });

        }

        @Override
        public void onClick(View view) {
            if(getAdapterPosition()!=RecyclerView.NO_POSITION)
            { mitem.onItemClick(getAdapterPosition());}
            //button.setImageResource(R.drawable.mbuttoncheck);
                   /* if(x==1)
                        button.setImageResource(R.drawable.ic_check_box1);
                    else
                        button.setImageResource(R.drawable.ic_check_box);*/
        }


    }
}
