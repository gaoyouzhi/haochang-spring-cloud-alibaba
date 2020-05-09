package com.haochang.spring.cloud.alibaba.rocketmq.provider.model;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 描述：线程测试
 * @author: youzhi.gao
 * @date: 2020-05-07 17:19
 */
public class EmployeePlus extends Thread{
    private long time;
    private String employeeName;
    private CountDownLatch countDownLatch;

    public EmployeePlus(){

    }

    public EmployeePlus(long time, String employeeName, CountDownLatch countDownLatch){
        this.time = time;
        this.employeeName = employeeName;
        this.countDownLatch = countDownLatch;
    }

    public long getTime() {
        return time;
    }


    public void setTime(long time) {
        this.time = time;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public void run() {
        try {
            System.out.println("开始第一阶段准备---" + employeeName);
            Thread.sleep(time);
            System.out.println("结束第一阶段准备---" + employeeName);

            countDownLatch.countDown();
            System.out.println("开始第二阶段准备---" + employeeName);
            Thread.sleep(time);
            System.out.println("结束第二阶段准备---" + employeeName);


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
