package com.task.wex.constant;

public class AppConstants {

    private AppConstants() {}
    
    public static final String TREASURY_REPORT_URL = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange";
    
    public static final String RECORD_DATE_PARAM = "record_date";
    public static final String EXCHANGE_RATE_PARAM = "exchange_rate";
    public static final String CURRENCY_PARAM = "currency";

    public static final String FIELDS_PARAM = "fields";
    public static final String FILTER_PARAM = "filter";
    public static final String SORT_PARAM = "sort";
   
    public static final String GREATER_THAN_EQUAL_PARAM = ":gte:";
    public static final String EQUAL_PARAM = ":eq:";
}
