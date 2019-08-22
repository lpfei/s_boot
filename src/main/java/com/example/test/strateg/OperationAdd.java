package com.example.test.strateg;

/**
 * description:
 * Created by lpfei on 2019/8/22
 */
public class OperationAdd implements Strateg {

    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
