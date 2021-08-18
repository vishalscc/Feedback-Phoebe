package com.scc.shake.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface ApiService {


    @POST("sentFeedback")
    Call<FeedbackModel> sentFeedback(@Field("token") String token,
                                     @Field("lang") String lang,
                                     @Field("device_type") String device_type,
                                     @Field("model") String model,
                                     @Field("page_name") String page_name,
                                     @Field("text") String text,
                                     @Field("os") int os);



}
