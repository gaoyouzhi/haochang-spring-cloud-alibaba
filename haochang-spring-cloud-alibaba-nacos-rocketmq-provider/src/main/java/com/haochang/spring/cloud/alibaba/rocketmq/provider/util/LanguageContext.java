package com.haochang.spring.cloud.alibaba.rocketmq.provider.util;


import java.util.stream.DoubleStream.Builder;

/**
 * @description: 描述：语言载体
 * @author: youzhi.gao
 * @date: 2020-05-06 16:09
 */
public class LanguageContext {
    private String language;
    private String locale;
    private String code;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        private String language;
        private String locale;
        private String code;

        public Builder withLanguage(String val) {
            this.language = val;
            return this;
        }

        public Builder withLocale(String val){
            this.locale = val;
            return this;
        }
    }
}
