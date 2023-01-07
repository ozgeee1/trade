package com.backendcase.evaexchange.utility;

import java.math.BigDecimal;

public class QuantityQalculation {

    public static BigDecimal calculateQuantity(BigDecimal percent,BigDecimal quantity){
       return quantity.multiply(percent.divide(BigDecimal.valueOf(100)));
    }
}
