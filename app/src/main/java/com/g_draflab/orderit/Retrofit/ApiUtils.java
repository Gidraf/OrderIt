package com.g_draflab.orderit.Retrofit;

import com.g_draflab.orderit.Models.AttributesServices;

public class ApiUtils {

    private ApiUtils() {
    }

    public static AuthServices registerCustomerService(){
        return ApiClient.getClient().create(AuthServices.class);
    }

    public static ShippingRegionService shippingRegionService(){
        return ApiClient.getClient().create(ShippingRegionService.class);
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

    public static AttributesServices attributesServices(){
        return ApiClient.getClient().create(AttributesServices.class);
    }

    public static CartServices cartServices(){
        return ApiClient.getClient().create(CartServices.class);
    }

    public static OrderServices orderServices(){
        return ApiClient.getClient().create(OrderServices.class);
    }

    public static StripeServices stripeServices(){
        return ApiClient.getClient().create(StripeServices.class);
    }
}
