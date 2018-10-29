package com.book.common.time;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;


public class JsonHttpMessageConverter extends FastJsonHttpMessageConverter {

    /**
     *@Date 2018/9/10 18:39
     *@Description 返回格式化日期
     **/
    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH";
        JSON.toJSONString(obj , SerializerFeature.WriteDateUseDateFormat);
        super.writeInternal(obj, outputMessage);
    }
}
