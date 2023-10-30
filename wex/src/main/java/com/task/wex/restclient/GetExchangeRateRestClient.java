package com.task.wex.restclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.task.wex.constant.AppConstants;
import com.task.wex.model.response.GetExchangeRateResponse;
import com.task.wex.util.URLUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GetExchangeRateRestClient {

    public String getExchangeRate(String currency, LocalDate transactionDate) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put(AppConstants.FIELDS_PARAM, AppConstants.EXCHANGE_RATE_PARAM.concat(",").concat(AppConstants.RECORD_DATE_PARAM));
        params.put(AppConstants.FILTER_PARAM, buildFilterParam(currency, transactionDate));
        params.put(AppConstants.SORT_PARAM, "-".concat(AppConstants.RECORD_DATE_PARAM));

        URL url = new URL(AppConstants.TREASURY_REPORT_URL.concat("?").concat(URLUtils.addParams(params)));

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        ObjectMapper objectMapper = new ObjectMapper();
        var response = objectMapper.readValue(content.toString(), GetExchangeRateResponse.class);

        if (response.getData().length > 0) {
            return response.getData()[0].get(AppConstants.EXCHANGE_RATE_PARAM).toString();
        } else {
            return null;
        }
    }
    
    private String buildFilterParam(String currency, LocalDate transactionDate) {
        return AppConstants.RECORD_DATE_PARAM
                .concat(AppConstants.GREATER_THAN_EQUAL_PARAM)
                .concat(transactionDate.minusMonths(6).format(DateTimeFormatter.ISO_LOCAL_DATE))
                .concat(",")
                .concat(AppConstants.CURRENCY_PARAM)
                .concat(AppConstants.EQUAL_PARAM)
                .concat(currency);
    }
}
