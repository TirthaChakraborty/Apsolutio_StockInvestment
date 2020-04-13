package com.example.apsolutiostockinvestment.apicalls;

import com.example.apsolutiostockinvestment.apiresponse.BSEResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APICallBSE {
    @GET("{code}")
    Call<BSEResponse> bsecall(@Path("code") String code, @Query("api_key") String key);
}
