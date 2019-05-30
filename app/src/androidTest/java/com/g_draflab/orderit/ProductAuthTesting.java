package com.g_draflab.orderit;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.g_draflab.orderit.Models.CustomerRegisterResponse;
import com.g_draflab.orderit.Models.ProductResponse;
import com.g_draflab.orderit.Retrofit.ApiClient;
import com.g_draflab.orderit.Retrofit.AuthServices;
import com.g_draflab.orderit.Retrofit.ProductsServices;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleInstrumentedTest {


    @Test
    public void authTesting() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        AuthServices authServices = ApiClient.getClient().create(AuthServices.class);

//        Call<CustomerRegisterResponse> login = authServices.loginCustomer("orenjagidraf@gmail.com", "password");
//        Call<CustomerRegisterResponse> register = authServices.registerCustomer("orenja33gidraf@gmail.com", "orenja33gidraf@gmail.com", "orenja");
//
//        try {
//            //Magic is here at .execute() instead of .enqueue()
//            Response<CustomerRegisterResponse> loginresponse = login.execute();
//            Response<CustomerRegisterResponse> registerResponse= register.execute();
//            CustomerRegisterResponse authResponse = loginresponse.body();
//            assertTrue(loginresponse.isSuccessful());
//            assertTrue(registerResponse.isSuccessful());
//            assertEquals("com.g_draflab.orderit", appContext.getPackageName());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Test
    public void ProductTesting() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        ProductsServices productsServices = ApiClient.getClient().create(ProductsServices.class);

        Call<ProductResponse> getAllProduct = productsServices.getAllProducts(1,1,10,200);
        Call<ProductResponse> getProductByCategory = productsServices.getproductsCategories(1,1,10,200);

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<ProductResponse> productResponse = getAllProduct.execute();
            Response<ProductResponse> productResponseGetAllCategorr= getProductByCategory.execute();
            assertTrue(productResponse.isSuccessful());
            assertTrue(productResponseGetAllCategorr.isSuccessful());
            assertEquals("com.g_draflab.orderit", appContext.getPackageName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
