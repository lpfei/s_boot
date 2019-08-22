package com.example.test.strateg;

import java.util.HashMap;
import java.util.Map;

/**
 * description:策略模式工厂
 * Created by lpfei on 2019/8/22
 */
public class StrategyFactory {

    private static StrategyFactory factory = new StrategyFactory();

    private StrategyFactory() {
    }

    private static Map strategyMap = new HashMap<>();

    static {
        strategyMap.put("+", new OperationAdd());
        strategyMap.put("-", new OperationSubstract());
    }

    public Strateg creator(String type) {
        return (Strateg) strategyMap.get(type);
    }

    public static StrategyFactory getInstance() {
        return factory;
    }
}
