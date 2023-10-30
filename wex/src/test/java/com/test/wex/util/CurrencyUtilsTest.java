package com.test.wex.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.task.wex.util.CurrencyUtils;

class CurrencyUtilsTest {
    
    @Test
    void roundTest() {
        assertEquals("1230.00", CurrencyUtils.round(1230));
        assertEquals("1230.10", CurrencyUtils.round(1230.1));
        assertEquals("1230.11", CurrencyUtils.round(1230.11));
        assertEquals("1230.15", CurrencyUtils.round(1230.155));
        assertEquals("1230.15", CurrencyUtils.round(1230.154));
        assertEquals("1230.16", CurrencyUtils.round(1230.156));
    }
}
