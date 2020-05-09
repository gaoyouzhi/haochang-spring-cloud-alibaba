package com.haochang.spring.cloud.alibaba.highqps.extension.spi;

import com.haochang.spring.cloud.alibaba.highqps.extension.annotation.SPI;
import com.haochang.spring.cloud.alibaba.highqps.extension.utils.Holder;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.ConcurrentHashMap;
import com.haochang.spring.cloud.alibaba.highqps.extension.utils.Compiler;
import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock.Catch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 描述：利用spi技术进行加载扩展类
 * @author: youzhi.gao
 * @date: 2020-05-09 13:39
 */
public class ExtensionLoader<T> {

    private static final ConcurrentHashMap<Class<?>, ExtensionLoader<?>> EXTENSION_LOADERS = new ConcurrentHashMap<>();
    private final Class<?> type;
    private final ExtensionFactory objectFactory;
    private final Holder<Object> cachedAdaptiveInstance = new Holder();
    private volatile Throwable createAdaptiveInstanceError;
    private volatile Class<?> cachedAdaptiveClass;
    private volatile Class<?> cachedAdaptiveExtensionClass;
    private static final Logger logger = LoggerFactory.getLogger(ExtensionLoader.class);

    public ExtensionLoader(Class<?> type) {
        this.type = type;
        this.objectFactory = type == ExtensionFactory.class ? null : (ExtensionFactory)getExtensionLoader(ExtensionFactory.class).getAdaptiveExtension();
    }

    /**
     * 适配器扩展
     * @return
     */
    public T getAdaptiveExtension() {
        Object instance = this.cachedAdaptiveInstance.get();
        if (instance == null) {
            if (this.createAdaptiveInstanceError != null) {
                throw new IllegalStateException("fail to create adaptive instance: " + this.createAdaptiveInstanceError.toString(), this.createAdaptiveInstanceError);
            }

            Holder var2 = this.cachedAdaptiveInstance;
            synchronized(this.cachedAdaptiveInstance) {
                instance = this.cachedAdaptiveInstance.get();
                if (instance == null) {
                    try {
                        instance = this.createAdaptiveExtension();
                        this.cachedAdaptiveInstance.set(instance);
                    } catch (Throwable var5) {
                        this.createAdaptiveInstanceError = var5;
                        throw new IllegalStateException("fail to create adaptive instance: " + var5.toString(), var5);
                    }
                }
            }
        }

        return (T) instance;
    }


    /**
     * @Description 描述：创建适配扩展对象
     * @Author: youzhi.gao
     * @Date: 2020-05-09 15:30
     */
    public T createAdaptiveExtension() {

        try{
            return this.injectExtension((T) this.getAdaptiveExtensionClass().newInstance());
        }catch (Exception val2){
            throw new IllegalStateException("failed to create adaptive extension", val2);
        }

    }

    /**
     * @return
     */
    private T injectExtension(T instance) {

        try {

            if(this.objectFactory != null){
                Method[] arr$ = instance.getClass().getMethods();
                int len$ = arr$.length;
                for (int i = 0; i < len$; i++) {
                    Method method = arr$[i];
                    if(method.getName().startsWith("set") && method.getParameters().length == 1 && Modifier.isPublic(method.getModifiers())){
                        Class pt = method.getParameterTypes()[0];

                        try{
                            String property = method.getName().length() > 3 ? method.getName().substring(3,4).toLowerCase() + method.getName().substring(4) : "";
                            Object object = this.objectFactory.getExtension(pt, property);
                            if(null != object){
                                method.invoke(instance, object);
                            }
                        } catch(Exception e){

                        }
                    }
                }
            }
        }catch (Exception e){
            logger.error("inject extension exception " + instance);
        }

        return instance;
    }



    public Class<?> getAdaptiveExtensionClass() {
        this.getExtensionClasses();
        return this.cachedAdaptiveClass != null ? this.cachedAdaptiveClass : (this.cachedAdaptiveClass = this.createAdaptiveExtensionClass());
    }

    public Class<?> createAdaptiveExtensionClass() {
        String code = this.createAdaptiveExtensionClassCode();
        ClassLoader classLoader = findClassLoader();
        Compiler compiler = getExtensionLoader(Compiler.class).getAdaptiveExtension();
        return compiler.compiler(code, classLoader);
    }

    /**
     * 找到类加载器
     * @return
     */
    public static ClassLoader findClassLoader() {
        return ExtensionLoader.class.getClassLoader();
    }

    /**
     * @Description 描述：构造class文件
     * @Author: youzhi.gao
     * @Date: 2020-05-09 15:43
     */
    public String createAdaptiveExtensionClassCode() {
        StringBuilder codeBuilder = new StringBuilder();
        Method[] methods = this.type.getMethods();

        return codeBuilder.toString();
    }

    /**
     * 获取扩展类
     */
    public void getExtensionClasses() {

    }

    /**
     * @Description 描述：尝试添加扩展类
     * @Author: youzhi.gao
     * @Date: 2020-05-09 13:40
     */
    public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> type) {
        if(null == type){
            throw new IllegalArgumentException("Extension type is null");
        }else if(!type.isInterface()){
            throw new IllegalArgumentException("Extension type " + type + "is not interface");
        }else if(!withExtensionAnnotation(type)){
            throw new IllegalArgumentException("Extension type " + type + " is not extension, because WITHOUT @" + SPI.class.getSimpleName() + "annotation!");
        }else{
            ExtensionLoader<T> loader = (ExtensionLoader) EXTENSION_LOADERS.get(type);
            if(null == loader){
                EXTENSION_LOADERS.putIfAbsent(type, new ExtensionLoader(type));
                loader = (ExtensionLoader) EXTENSION_LOADERS.get(type);
            }
            return loader;
        }
    }

    /**
     * @Description 描述：判断注解类型
     * @Param
     * @Returns
     * @Author: youzhi.gao
     * @Date: 2020-05-09 14:24
     */
    private static <T> boolean withExtensionAnnotation(Class<T> type) {
        return type.isAnnotationPresent(SPI.class);
    }
}
