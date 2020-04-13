package com.example.apsolutiostockinvestment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APICallBSE {
    @GET("{code}")
    Call<BSEResponse> bsecall(@Path("code") String code, @Query("api_key") String key);
}
