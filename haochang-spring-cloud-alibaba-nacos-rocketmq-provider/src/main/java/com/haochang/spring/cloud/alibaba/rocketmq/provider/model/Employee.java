package com.haochang.spring.cloud.alibaba.rocketmq.provider.model;

/**
 * @description: 描述：线程测试
 * @author: youzhi.gao
 * @date: 2020-05-07 17:19
 */
public class Employee extends Thread{
    private long time;
    private String employeeName;

    public Employee(long time, String employeeName){
        this.time = time;
        this.employeeName = employeeName;
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
            System.out.println("开始准备---" + employeeName);
            Thread.sleep(time);
            System.out.println("准备结束---" + employeeName);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
