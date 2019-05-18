package com.g_draflab.orderit.Retrofit;

public class ApiUtils {

    private ApiUtils() {
    }

    public static AuthServices registerCustomerService(){
        return ApiClient.getClient().create(AuthServices.class);
    }
}
