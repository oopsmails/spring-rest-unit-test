package com.oopsmails.test.config;

import com.oopsmails.annotation.Restful;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/**
 * Created by liu on 2017-07-11.
 */
public class OopsRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    private RequestMappingInfo.BuilderConfiguration config = new RequestMappingInfo.BuilderConfiguration();

    @Override
    protected boolean isHandler(Class<?> beanType) {
        return (AnnotatedElementUtils.hasAnnotation(beanType, Restful.class) ||
                super.isHandler(beanType));
    }

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = createRequestMappingInfo(method);
        if (info != null) {
            RequestMappingInfo typeInfo = createRequestMappingInfo(handlerType);
            if (typeInfo != null) {
                info = typeInfo.combine(info);
            }
        }
        return info;
    }

    private RequestMappingInfo createRequestMappingInfo(AnnotatedElement element) {
        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(element, RequestMapping.class);
        RequestCondition<?> condition = (element instanceof Class<?> ?
                getCustomTypeCondition((Class<?>) element) : getCustomMethodCondition((Method) element));
        RequestMappingInfo result = (requestMapping != null ? createRequestMappingInfo(requestMapping, condition) : null);

        if (result == null) {
            Restful restful = AnnotatedElementUtils.findMergedAnnotation(element, Restful.class);
            result = (requestMapping != null ? createRequestMappingInfo(restful, condition) : null);
        }

        return result;
    }

    protected RequestMappingInfo createRequestMappingInfo(
            Restful restful, RequestCondition<?> customCondition) {

        return RequestMappingInfo
                .paths(resolveEmbeddedValuesInPatterns(restful.value()))
                .methods(restful.method())
                .params(restful.params())
                .headers(restful.headers())
                .consumes(restful.consumes())
                .produces(restful.produces())
                .mappingName(restful.name())
                .customCondition(customCondition)
                .options(this.config)
                .build();
    }
}
