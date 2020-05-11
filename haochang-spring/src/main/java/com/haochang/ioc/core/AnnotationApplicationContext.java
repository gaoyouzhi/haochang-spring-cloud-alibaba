package com.haochang.ioc.core;


import com.haochang.ioc.annotation.Autowired;
import com.haochang.ioc.annotation.Repository;
import com.haochang.ioc.annotation.Service;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 描述：通过注解来进行完成IOC操作的具体实现类
 * @author: youzhi.gao
 * @date: 2020-05-11 09:50
 */
public class AnnotationApplicationContext extends ApplicationContext {

    private static Map<String, Object> beanDefinationsMap = new ConcurrentHashMap<>(256);
    private static final String SCAN_PACKAGE = "com.haochang.demo";
    private static List<String> classPathCache = Collections.synchronizedList(new ArrayList<>());
    private static List<Class<?>> classesCache = Collections.synchronizedList(new ArrayList<>());


    public AnnotationApplicationContext() {
        //1.初始化IOC容器
        initContainer();
    }

    /**
     * 初始化容器
     */
    private void initContainer() {

        //1.找出所有包下的class文件
        scanPackage(SCAN_PACKAGE);
        //2.注册bean
        registerClasses();
        //3.bean的对象创建出来
        doCreateBean();
        //4.处理依赖关系
        populateBean();
    }

    /**
     * 处理对象依赖关系
     */
    private void populateBean(){
        if(classesCache.size() == 0){
            return;
        }
        try{
            for(Class<?> cl : classesCache){
                //获取别名
                String aliasName = lowerClassName(cl.getSimpleName());
                if(cl.isAnnotationPresent(Repository.class)){
                    Repository repository = cl.getAnnotation(Repository.class);
                    if(!"".equals(repository.value())){
                        aliasName = repository.value();
                    }
                }else if(cl.isAnnotationPresent(Service.class)){
                    Service service = cl.getAnnotation(Service.class);
                    if(!"".equals(service.value())){
                        aliasName = service.value();
                    }
                }

                Object instance = beanDefinationsMap.get(aliasName);

                Field[] fields = cl.getDeclaredFields();
                for (Field field : fields){
                    //判断字段是否有对应注解
                    if(field.isAnnotationPresent(Autowired.class)){
                        field.setAccessible(true);
                        Autowired autowired = field.getAnnotation(Autowired.class);
                        //判断是否有指定别名
                        if(!"".equals(autowired.value())){
                            //安装名称装配
                            field.set(instance, beanDefinationsMap.get(autowired.value()));
                        }else{
                            //按照类型装配
                            String typeName = field.getType().getName();
                            field.set(instance, beanDefinationsMap.get(typeName));
                        }
                    }
                }
            }
        }catch (Exception e){

        }

    }

    /**
     * 实例化class
     */
    private void doCreateBean() {
        if(classesCache.size() == 0){
            return;
        }
        try {
            for(Class<?> clazz : classesCache){
                //创建一个当前类的实例
                Object instance = clazz.newInstance();
                //获取类型名称
                String aliasName = lowerClassName(clazz.getSimpleName());
                if(clazz.isAnnotationPresent(Repository.class)){
                    Repository repository = clazz.getAnnotation(Repository.class);
                    //如果存在别名 按名称装配
                    if(!"".equals(repository.value())){
                        aliasName = repository.value();
                    }
                }else if(clazz.isAnnotationPresent(Service.class)){
                    Service service = clazz.getAnnotation(Service.class);
                    if(!"".equals(service.value())){
                        aliasName = service.value();
                    }
                }
                beanDefinationsMap.put(aliasName, instance);

                //判断当前类是否实现了接口
                Class<?>[] interfaces = clazz.getInterfaces();
                if(interfaces == null){
                    continue;
                }

                //把当前接口的类路径作为key存储到容器中
                for (Class<?> inter : interfaces){
                    beanDefinationsMap.put(inter.getName(), instance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 类名首字母小写
     * @param simpleName
     * @return
     */
    private String lowerClassName(String simpleName) {
        char[] ch = simpleName.toCharArray();
        ch[0] += 32;
        return new String(ch);
    }

    /**
     * 注册所有需要维护的class对象 到缓存中
     */
    private void registerClasses() {
        if(classPathCache.size() == 0){
            return;
        }

        for(String classPath : classPathCache){
            try {
                Class<?> clazz = Class.forName(classPath);
                if(clazz.isAnnotationPresent(Repository.class) || clazz.isAnnotationPresent(Service.class)){
                    classesCache.add(clazz);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 扫描所有包
     * @param scanPackage
     */
    private void scanPackage(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource(scanPackage.replaceAll("\\.", "/"));
        try {
            File file = new File(url.toURI());
            file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File child) {
                    if(child.isDirectory()){
                        scanPackage(scanPackage + "." + child.getName());
                    }else {
                        if(child.getName().endsWith(".class")){
                            String classPath = scanPackage + "." + child.getName().replaceAll("\\.class", "");
                            classPathCache.add(classPath);
                        }
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Object doGetBean(String name) {
        return this.beanDefinationsMap.get(name);
    }

    public static void main(String[] args) {
        new AnnotationApplicationContext();
//        System.out.println(classPathCache);
        System.out.println(beanDefinationsMap);
    }
}
