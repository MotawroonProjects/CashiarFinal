package com.cashiar.services;


import com.cashiar.models.AggreateEarnReportsModel;
import com.cashiar.models.AllAccountsModel;
import com.cashiar.models.AllAmountLeftOverReportModel;
import com.cashiar.models.AllBillCustomerReportModel;
import com.cashiar.models.AllBillOfSellModel;
import com.cashiar.models.AllCategoryModel;
import com.cashiar.models.AllColorsModel;
import com.cashiar.models.AllCustomersModel;
import com.cashiar.models.AllDiscountsModel;
import com.cashiar.models.AllExpensesModel;
import com.cashiar.models.AllPermissionModel;
import com.cashiar.models.AllProductsModel;
import com.cashiar.models.AllPurhcasesBillReportModel;
import com.cashiar.models.AllSalesBillReportModel;
import com.cashiar.models.AllSalesPurshReportModel;
import com.cashiar.models.StockDataModel;
import com.cashiar.models.BillModel;
import com.cashiar.models.CreateBuyOrderModel;
import com.cashiar.models.CreateOrderModel;
import com.cashiar.models.PackageResponse;
import com.cashiar.models.PlaceGeocodeData;
import com.cashiar.models.PlaceMapDetailsData;
import com.cashiar.models.SettingModel;
import com.cashiar.models.SingleProductDataModel;
import com.cashiar.models.SingleProductModel;
import com.cashiar.models.Slider_Model;
import com.cashiar.models.SubscriptionDataModel;
import com.cashiar.models.UnpaidBillSaleReportModel;
import com.cashiar.models.UserModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Service {
    @GET("place/findplacefromtext/json")
    Call<PlaceMapDetailsData> searchOnMap(@Query(value = "inputtype") String inputtype,
                                          @Query(value = "input") String input,
                                          @Query(value = "fields") String fields,
                                          @Query(value = "language") String language,
                                          @Query(value = "key") String key
    );

    @GET("geocode/json")
    Call<PlaceGeocodeData> getGeoData(@Query(value = "latlng") String latlng,
                                      @Query(value = "language") String language,
                                      @Query(value = "key") String key);

    @FormUrlEncoded
    @POST("api/login")
    Call<UserModel> login(@Field("phone_code") String phone_code,
                          @Field("phone") String phone

    );

    @FormUrlEncoded
    @POST("api/register")
    Call<UserModel> signup(@Field("phone_code") String phone_code,
                           @Field("phone") String phone,
                           @Field("name") String name,
                           @Field("email") String email,
                           @Field("software_type") String software_type


    );

    @GET("api/currentUser")
    Call<UserModel> getuser(
            @Header("Authorization") String Authorization

    );

    @FormUrlEncoded
    @POST("api/firebase-tokens")
    Call<ResponseBody> updatePhoneToken(
            @Field("firebase_token") String firebase_token,
            @Field("user_id") int user_id,
            @Field("software_type") String software_type
    );

    @GET("api/allCategories")
    Call<AllCategoryModel> getproductcategories(
            @Header("Authorization") String Authorization

    );

    @GET("api/searchCategories")
    Call<AllCategoryModel> getcategories(
            @Header("Authorization") String Authorization,
            @Query("search_keyWord") String search_keyWord


    );

    @FormUrlEncoded
    @POST("api/searchProducts")
    Call<AllProductsModel> getproducts(
            @Header("Authorization") String Authorization,
            @Field("search_keyWord") String search_keyWord,
            @Field("category_id") String category_id


    );

    @FormUrlEncoded
    @POST("api/addNewProduct")
    Call<SingleProductDataModel> addproduct(
            @Header("Authorization") String Authorization,
            @Field("title") String title,
            @Field("product_type") String product_type,
            @Field("product_cost") String product_cost,
            @Field("product_price") String product_price,
            @Field("sku") String sku,
            @Field("barcode_code") String barcode_code,
            @Field("stock_type") String stock_type,
            @Field("stock_amount") String stock_amount,
            @Field("display_logo_type") String display_logo_type,
            @Field("color_id") String color_id,
            @Field("category_id") String category_id


    );

    @Multipart
    @POST("api/addNewProduct")
    Call<SingleProductDataModel> addproductwithimage(
            @Header("Authorization") String Authorization,
            @Part("title") RequestBody title,
            @Part("product_type") RequestBody product_type,
            @Part("product_cost") RequestBody product_cost,
            @Part("product_price") RequestBody product_price,
            @Part("sku") RequestBody sku,
            @Part("barcode_code") RequestBody barcode_code,
            @Part("stock_type") RequestBody stock_type,
            @Part("stock_amount") RequestBody stock_amount,
            @Part("display_logo_type") RequestBody display_logo_type,
            @Part("color_id") RequestBody color_id,
            @Part("category_id") RequestBody category_id,
            @Part MultipartBody.Part image


    );

    @FormUrlEncoded
    @POST("api/editProduct")
    Call<ResponseBody> updateproduct(
            @Header("Authorization") String Authorization,
            @Field("title") String title,
            @Field("product_type") String product_type,
            @Field("product_cost") String product_cost,
            @Field("product_price") String product_price,
            @Field("sku") String sku,
            @Field("barcode_code") String barcode_code,
            @Field("stock_type") String stock_type,
            @Field("stock_amount") String stock_amount,
            @Field("display_logo_type") String display_logo_type,
            @Field("color_id") String color_id,
            @Field("category_id") String category_id,
            @Field("product_id") String product_id


    );

    @Multipart
    @POST("api/editProduct")
    Call<ResponseBody> updtaeproductwithimage(
            @Header("Authorization") String Authorization,
            @Part("title") RequestBody title,
            @Part("product_type") RequestBody product_type,
            @Part("product_cost") RequestBody product_cost,
            @Part("product_price") RequestBody product_price,
            @Part("sku") RequestBody sku,
            @Part("barcode_code") RequestBody barcode_code,
            @Part("stock_type") RequestBody stock_type,
            @Part("stock_amount") RequestBody stock_amount,
            @Part("display_logo_type") RequestBody display_logo_type,
            @Part("color_id") RequestBody color_id,
            @Part("category_id") RequestBody category_id,
            @Part("product_id") RequestBody product_id,

            @Part MultipartBody.Part image


    );

    @GET("api/getAllColors")
    Call<AllColorsModel> getcolors(
    );

    @FormUrlEncoded
    @POST("api/addNewCategory")
    Call<ResponseBody> adddepartment(
            @Header("Authorization") String Authorization,
            @Field("title") String title,
            @Field("display_logo_type") String display_logo_type,
            @Field("color_id") String color_id


    );

    @FormUrlEncoded
    @POST("api/editCategory")
    Call<ResponseBody> updatedepartment(
            @Header("Authorization") String Authorization,
            @Field("title") String title,
            @Field("display_logo_type") String display_logo_type,
            @Field("color_id") String color_id,
            @Field("category_id") String category_id


    );

    @FormUrlEncoded
    @POST("api/addNewCoupon")
    Call<ResponseBody> adddiscount(
            @Header("Authorization") String Authorization,
            @Field("title") String title,
            @Field("type") String type,
            @Field("value") String value


    );

    @GET("api/searchCoupons")
    Call<AllDiscountsModel> getDiscount(
            @Header("Authorization") String Authorization

    );

    @GET("api/searchClientsInSale")
    Call<AllCustomersModel> getCustomer(
            @Header("Authorization") String Authorization

    );

    @GET("api/searchSuppliersInPurchase")
    Call<AllCustomersModel> getsuppliers(
            @Header("Authorization") String Authorization

    );

    @POST("api/makeSaleOrder")
    Call<BillModel> createOrdersale(@Header("Authorization") String user_token,
                                    @Body CreateOrderModel model
    );

    @POST("api/makePurchaseOrder")
    Call<ResponseBody> createOrderBuy(@Header("Authorization") String user_token,
                                      @Body CreateBuyOrderModel model
    );

    @POST("api/makeBackSaleOrder")
    Call<ResponseBody> createBacksale(@Header("Authorization") String user_token,
                                      @Body CreateOrderModel model
    );

    @POST("api/makeBackPurchaseOrder")
    Call<ResponseBody> createBackbuy(@Header("Authorization") String user_token,
                                     @Body CreateBuyOrderModel model
    );

    @FormUrlEncoded
    @POST("api/getSingleProductByBarcode")
    Call<SingleProductModel> getsingleproductbybarcode(
            @Header("Authorization") String Authorization,
            @Field("barcode_code") String barcode_code


    );

    @GET("api/searchClients")
    Call<AllCustomersModel> getCustomer(
            @Header("Authorization") String Authorization,
            @Query("search_keyWord") String search_keyWord

    );

    @GET("api/searchSuppliers")
    Call<AllCustomersModel> getsuppliers(
            @Header("Authorization") String Authorization,
            @Query("search_keyWord") String search_keyWord


    );

    @FormUrlEncoded
    @POST("api/addNewClient")
    Call<ResponseBody> addcustomer(
            @Header("Authorization") String Authorization,
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone_code") String phone_code,
            @Field("phone") String phone,
            @Field("address") String address


    );

    @FormUrlEncoded
    @POST("api/addNewSupplier")
    Call<ResponseBody> addsuppliers(
            @Header("Authorization") String Authorization,
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone_code") String phone_code,
            @Field("phone") String phone,
            @Field("address") String address


    );

    @FormUrlEncoded
    @POST("api/addNewCashier")
    Call<ResponseBody> addcahier(
            @Header("Authorization") String Authorization,
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone_code") String phone_code,
            @Field("phone") String phone,
            @Field("software_type") String software_type,
            @Field("permissions[]") List<Integer> permissions


    );

    @GET("api/allExpenses")
    Call<AllExpensesModel> getExpenses(
            @Header("Authorization") String Authorization

    );

    @GET("api/allPermissions")
    Call<AllPermissionModel> getPermission(

    );

    @GET("api/allAccounts")
    Call<AllAccountsModel> getaccounts(
            @Header("Authorization") String Authorization

    );

    @FormUrlEncoded
    @POST("api/makeExpense")
    Call<ResponseBody> addexpense(
            @Header("Authorization") String Authorization,
            @Field("account_id") String account_id,
            @Field("total_price") String total_price,
            @Field("date") String date


    );

    @FormUrlEncoded
    @POST("api/makeAccount")
    Call<ResponseBody> addaccount(
            @Header("Authorization") String Authorization,
            @Field("display_title") String display_title


    );

    @POST("api/logout")
    Call<ResponseBody> logout(@Header("Authorization") String user_token
    );

    @GET("api/allSaleOrders")
    Call<AllBillOfSellModel> getallbillsell(
            @Header("Authorization") String Authorization

    );

    @GET("api/allPurchaseOrders")
    Call<AllBillOfSellModel> getallbillbuy(
            @Header("Authorization") String Authorization

    );

    @FormUrlEncoded
    @POST("api/deleteProduct")
    Call<ResponseBody> delteproduct(
            @Header("Authorization") String Authorization,
            @Field("product_id") String product_id


    );

    @FormUrlEncoded
    @POST("api/deleteCategory")
    Call<ResponseBody> deltecategory(
            @Header("Authorization") String Authorization,
            @Field("category_id") String category_id


    );

    @GET("api/allSliders")
    Call<Slider_Model> get_slider();

    @GET("api/marketData")
    Call<UserModel> getprofile(
            @Header("Authorization") String Authorization
    );

    @FormUrlEncoded
    @POST("api/profile/update")
    Call<UserModel> Editprofile(
            @Header("Authorization") String Authorization,
            @Field("name") String name,
            @Field("phone_code") String phone_code,
            @Field("phone") String product_cost,
            @Field("address") String address,
            @Field("longitude") String longitude,
            @Field("latitude") String latitude,
            @Field("currency") String currency,
            @Field("tax_amount") String tax_amount


    );


    @Multipart
    @POST("api/profile/update")
    Call<UserModel> Editprofilewithimage(
            @Header("Authorization") String Authorization,
            @Part("name") RequestBody name,
            @Part("phone_code") RequestBody phone_code,
            @Part("phone") RequestBody product_cost,
            @Part("address") RequestBody address,
            @Part("longitude") RequestBody longitude,
            @Part("latitude") RequestBody latitude,
            @Part("currency") RequestBody currency,
            @Part("tax_amount") RequestBody tax_amount,
            @Part MultipartBody.Part image


    );

    @FormUrlEncoded
    @POST("api/profile/update")
    Call<ResponseBody> Editprofilecahier(
            @Header("Authorization") String Authorization,
            @Field("name") String name,
            @Field("phone_code") String phone_code,
            @Field("phone") String product_cost,
            @Field("address") String address,
            @Field("longitude") String longitude,
            @Field("latitude") String latitude


    );

    @FormUrlEncoded
    @POST("api/deleteCoupon")
    Call<ResponseBody> deltediscount(
            @Header("Authorization") String Authorization,
            @Field("coupon_id") String coupon_id


    );

    @FormUrlEncoded
    @POST("api/editCoupon")
    Call<ResponseBody> updateiscount(
            @Header("Authorization") String Authorization,
            @Field("title") String title,
            @Field("type") String type,
            @Field("value") String value,
            @Field("coupon_id") String coupon_id


    );

    @FormUrlEncoded
    @POST("api/deleteExpense")
    Call<ResponseBody> delteexpense(
            @Header("Authorization") String Authorization,
            @Field("id") String id


    );

    @FormUrlEncoded
    @POST("api/editExpense")
    Call<ResponseBody> editExpense(
            @Header("Authorization") String Authorization,
            @Field("account_id") String account_id,
            @Field("total_price") String total_price,
            @Field("date") String date,
            @Field("id") String id

    );

    @FormUrlEncoded
    @POST("api/deleteClient")
    Call<ResponseBody> deltecustomer(
            @Header("Authorization") String Authorization,
            @Field("client_id") String client_id


    );

    @FormUrlEncoded
    @POST("api/editClient")
    Call<ResponseBody> updatecustomer(
            @Header("Authorization") String Authorization,
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone_code") String phone_code,
            @Field("phone") String phone,
            @Field("address") String address,
            @Field("client_id") String client_id

    );

    @FormUrlEncoded
    @POST("api/deleteSupplier")
    Call<ResponseBody> deltesupplier(
            @Header("Authorization") String Authorization,
            @Field("supplier_id") String supplier_id


    );

    @FormUrlEncoded
    @POST("api/editSupplier")
    Call<ResponseBody> updatesuppliers(
            @Header("Authorization") String Authorization,
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone_code") String phone_code,
            @Field("phone") String phone,
            @Field("address") String address,
            @Field("supplier_id") String supplier_id


    );

    @FormUrlEncoded
    @POST("api/purchaseCollectionReport")
    Call<SettingModel> getpurchase(
            @Header("Authorization") String Authorization,
            @Field("type") String type,
            @Field("from") String from,
            @Field("to") String to


    );

    @FormUrlEncoded
    @POST("api/salesCollectionReport")
    Call<SettingModel> getsales(
            @Header("Authorization") String Authorization,
            @Field("type") String type,
            @Field("from") String from,
            @Field("to") String to


    );

    @GET("api/earningsReport")
    Call<SettingModel> getearnproduct(
            @Header("Authorization") String Authorization


    );

    @GET("api/salesReport")
    Call<SettingModel> getsaleproduct(
            @Header("Authorization") String Authorization


    );

    @GET("api/mostProductSales")
    Call<AllProductsModel> getmostsaleproduct(
            @Header("Authorization") String Authorization


    );

    @GET("api/app/info")
    Call<SettingModel> getSetting();

    @GET("api/detailed-sales-report")
    Call<AllSalesPurshReportModel> getdetialedsalesReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("search_name") String search_name,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date


    );

    @GET("api/an-aggregate-sale-report")
    Call<AllSalesPurshReportModel> getAggreatesalesReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("search_name") String search_name,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date


    );

    @GET("api/report-of-unpaid-sales-invoices")
    Call<AllSalesBillReportModel> getUnpaidsalesInvoicesReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("search_name") String search_name,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date


    );

    @GET("api/sales-invoices")
    Call<AllSalesBillReportModel> getBillsalesReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("search_name") String search_name,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date
    );

    @GET("api/detailed-earnings-report")
    Call<AllSalesPurshReportModel> getdetialedEarningReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("search_name") String search_name,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date


    );

    @GET("api/aggregate-earnings-report")
    Call<AggreateEarnReportsModel> getAggreateEarningReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date


    );

    @GET("api/report-of-remaining-amounts-customers")
    Call<AllAmountLeftOverReportModel> getAmountLeftOverCustomerReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("search_name") String search_name,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date


    );

    @GET("api/client-bills")
    Call<AllBillCustomerReportModel> getBillCustomerReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("bill_num") String bill_num,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date,
            @Query("search_name") String search_name


    );

    @GET("api/report-products-sold-customer")
    Call<AllSalesPurshReportModel> getProductSoldCustomeReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("product_name") String product_name,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date,
            @Query("search_name") String search_name

    );

    @GET("api/report-remaining-amounts-suppliers")
    Call<AllAmountLeftOverReportModel> getAmountLeftOverSupplierReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("search_name") String search_name,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date


    );

    @GET("api/supplier-invoices")
    Call<AllBillCustomerReportModel> getBillSupplierReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("bill_num") String bill_num,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date,
            @Query("search_name") String search_name


    );

    @GET("api/report-products-purchased-supplier")
    Call<AllSalesPurshReportModel> getProductPurchasesSupplierReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("product_name") String product_name,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date,
            @Query("search_name") String search_name

    );

    @GET("api/detailed-purchase-report")
    Call<AllSalesPurshReportModel> getdetialedpurchasesReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("search_name") String search_name,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date


    );

    @GET("api/an-aggregate-purchase-report")
    Call<AllSalesPurshReportModel> getAggreatepurchasesReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("search_name") String search_name,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date


    );

    @GET("api/purchase-invoices")
    Call<AllPurhcasesBillReportModel> getBillpurchasesReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("search_name") String search_name,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date
    );

    @GET("api/report-of-unpaid-purchase-invoices")
    Call<AllPurhcasesBillReportModel> getUnpaidpurchasesInvoicesReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("search_name") String search_name,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date


    );

    @GET("api/movement-purchase-price-change-item")
    Call<AllSalesPurshReportModel> getmovement_purchase_price_change_itemReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("search_name") String search_name,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date


    );

    @GET("api/detailed-expense-report")
    Call<AllExpensesModel> getdetialedexpenseReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("search_name") String search_name,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date


    );

    @GET("api/outline-expense-report")
    Call<AllExpensesModel> getaggerateexpenseReport(
            @Header("Authorization") String Authorization,
            @Query("user_id") String user_id,
            @Query("search_name") String search_name,
            @Query("from_date") String from_date,
            @Query("to_date") String to_date


    );

    @GET("api/GetPackages")
    Call<SubscriptionDataModel> getSubscription();

    @FormUrlEncoded
    @POST("api/PayBill")
    Call<UnpaidBillSaleReportModel> paidunpaidsaleinvoice(
            @Field("id") String id,
            @Field("amount") String amount


    );

    @FormUrlEncoded
    @POST("api/getLinkToPay")
    Call<PackageResponse> buyPackage(
            @Header("Authorization") String Authorization,
            @Field("package_id") int package_id

    );

    @FormUrlEncoded
    @POST("api/paymentIsSuccess")
    Call<PackageResponse> confirmPackage(
            @Header("Authorization") String Authorization,
            @Field("package_id") int package_id

    );

    @FormUrlEncoded
    @POST("api/singleSaleOrder")
    Call<BillModel> getBill(@Header("Authorization") String Authorization,
                            @Field("sale_id") int sale_id

    );
    @GET("api/searchWarehouses")
    Call<StockDataModel> getStocks(
            @Header("Authorization") String Authorization

    );

    @FormUrlEncoded
    @POST("api/addNewWarehouse")
    Call<ResponseBody> addStock(@Header("Authorization") String Authorization,
                                @Field("title") String title

    );


    @FormUrlEncoded
    @POST("api/getProductsByWarehouse")
    Call<AllProductsModel> getproductsInStock(
            @Header("Authorization") String Authorization,
            @Field("search_keyWord") String search_keyWord,
            @Field("warehouse_id") String warehouse_id);


    @FormUrlEncoded
    @POST("api/editWarehouse")
    Call<ResponseBody> updateStock(@Header("Authorization") String Authorization,
                                   @Field("warehouse_id") String warehouse_id,
                                   @Field("title") String title
    );

}