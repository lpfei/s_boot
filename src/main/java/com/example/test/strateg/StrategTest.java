package com.example.test.strateg;

import com.example.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description:策略模式测试
 * Created by lpfei on 2019/8/22
 */

/**
 * 使用场景： 1、如果在一个系统里面有许多类，它们之间的区别仅在于它们的行为，那么使用策略模式可以动态地让一个对象在许多行为中选择一种行为。
 * 2、一个系统需要动态地在几种算法中选择一种。 3、如果一个对象有很多的行为，如果不用恰当的模式，这些行为就只好使用多重的条件选择语句来实现。
 * 注意事项：如果一个系统的策略多于四个，就需要考虑使用混合模式，解决策略类膨胀的问题。
 * <p>
 * 算法规则封装
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StrategTest {

    /**
     * 指定实现类
     */
    @Test
    public void test() {
        StrategContext context = new StrategContext(new OperationAdd());
        int val = context.executeStrategy(1, 3);
        log.info("value:{}", val);
    }

    /**
     * 结合工厂,匹配实现类
     */
    @Test
    public void testFactory() {
        StrategContext context = new StrategContext();
        int val = context.executeStrategy(1, 3, "+");
        log.info("value:{}", val);
    }

}
