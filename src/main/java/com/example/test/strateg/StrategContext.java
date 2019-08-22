package com.example.test.strateg;

/**
 * description:
 * Created by lpfei on 2019/8/22
 */
public class StrategContext {
    public Strateg strateg;

    public StrategContext(Strateg strateg) {
        this.strateg = strateg;
    }

    public int executeStrategy(int num1, int num2) {
        return strateg.doOperation(num1, num2);
    }
}
