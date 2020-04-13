package com.example.apsolutiostockinvestment;

import android.content.Context;

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
