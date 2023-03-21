package com.example.projectstore.awesomeAPI;

import java.math.BigDecimal;

public interface Converter {
    public BigDecimal converter(BigDecimal originalPrice, BigDecimal converterPrice);
}
