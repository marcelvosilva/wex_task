package com.test.wex.util;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.task.wex.constant.AppConstants;
import com.task.wex.util.URLUtils;

class URLUtilsTest {
    
    @Test
    void addParamsTest() throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();
        params.put(AppConstants.FIELDS_PARAM, AppConstants.RECORD_DATE_PARAM);
        var expected = "fields=record_date";
        assertEquals(expected, URLUtils.addParams(params));
    }
}
