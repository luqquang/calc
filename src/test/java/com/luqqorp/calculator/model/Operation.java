package com.luqqorp.calculator.model;

import java.math.BigDecimal;

@FunctionalInterface
public interface Operation {
   BigDecimal doOperation() throws Exception;
}
