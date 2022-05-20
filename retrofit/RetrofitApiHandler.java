package com.outleap.scholar.httpclient.retrofit;

import com.outleap.scholar.httpclient.ApiException;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class RetrofitApiHandler {
    public static <T> T handleSync(Call<T> caller) throws ApiException {
        try {
            Response<T> response = caller.execute();
            if (response.isSuccessful()) {

                return response.body();
            } else {
                throw new ApiException(response.errorBody().string());
            }
        } catch (IOException e) {
            throw new ApiException(e.getMessage());
        }
    }
}
