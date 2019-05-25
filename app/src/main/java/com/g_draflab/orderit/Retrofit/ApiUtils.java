package com.g_draflab.orderit.Retrofit;

public class ApiUtils {

    private ApiUtils() {
    }

    public static AuthServices registerCustomerService(){
        return ApiClient.getClient().create(AuthServices.class);
    }

    public static DepartmentsServices departmentsServices(){
        return  ApiClient.getClient().create(DepartmentsServices.class);
    }

    public static CategoryService categoryService(){
        return ApiClient.getClient().create(CategoryService.class);
    }

    public static ProductsServices productsServices(){
        return ApiClient.getClient().create(ProductsServices.class);
    }
}
