package com.example.apsolutiostockinvestment.utility;

import android.content.Context;

import com.example.apsolutiostockinvestment.apicalls.APICallBSE;
import com.example.apsolutiostockinvestment.apiresponse.BSEResponse;
import com.example.apsolutiostockinvestment.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utility {
    private BSEResponse bseResponse;
    public BSEResponse makeBSECall(String code, Context context)
    {

        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://www.quandl.com/api/v3/datasets/BSE/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        APICallBSE apiCallBSE=retrofit.create(APICallBSE.class);
        Call<BSEResponse> call=apiCallBSE.bsecall(code,context.getString(R.string.BSEApiKey));
        call.enqueue(new Callback<BSEResponse>() {
            @Override
            public void onResponse(Call<BSEResponse> call, Response<BSEResponse> response) {
                bseResponse=response.body();
            }

            @Override
            public void onFailure(Call<BSEResponse> call, Throwable t) {

            }
        });
        return bseResponse;
    }

}
