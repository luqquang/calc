package com.luqqorp.calculator.model.data;

import lombok.Data;

import java.util.LinkedList;

/**
 * The class represent the history, that stores a list of values with operations on them
 * in time sequence of expression.
 */
@Data
public class History {
   private LinkedList<HistoryUnit> historyUnits = new LinkedList<>();

}
