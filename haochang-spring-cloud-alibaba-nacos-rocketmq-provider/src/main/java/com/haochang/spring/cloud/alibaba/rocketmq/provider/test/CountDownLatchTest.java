package com.haochang.spring.cloud.alibaba.rocketmq.provider.test;

import com.haochang.spring.cloud.alibaba.rocketmq.provider.model.Employee;
import com.haochang.spring.cloud.alibaba.rocketmq.provider.model.EmployeePlus;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 描述：并发类测试
 * @author: youzhi.gao
 * @date: 2020-05-07 17:13
 */
public class CountDownLatchTest {

    public static void main(String[] args)throws Exception {
//        test1();
        test2();


    }

    /**
     * @Description 描述：测试countDownLatch
     * @Author: youzhi.gao@
     * @Date: 2020-05-07 17:26
     */
    private static void test2() throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(2);
        EmployeePlus a = new EmployeePlus((long) (Math.random()*2000+3000), "线程A", countDownLatch);
        EmployeePlus b = new EmployeePlus((long) (Math.random()*2000+3000), "线程B", countDownLatch);
        EmployeePlus c = new EmployeePlus((long) (Math.random()*2000+3000), "线程C", countDownLatch);

        a.start();
        b.start();
        countDownLatch.await();
        System.out.println("A.B准备完成========");
        c.start();
    }

    /**
     * 测试join
     * @throws Exception
     */
    private static void test1()throws Exception {
        Employee thread1 = new Employee(1000, "线程1--");
        Employee thread2 = new Employee(2000, "线程2--");
        Employee thread3 = new Employee(3000, "线程3--");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        thread3.start();
    }


}
