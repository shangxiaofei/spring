package com.spring.test.service.guava.annotations;

import com.spring.test.service.guava.enums.CacheLeveEnum;
import com.spring.test.service.guava.enums.DoType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午11:39 2018/5/30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

    DoType doType() default DoType.READ;
    CacheLeveEnum[] cacheLeves() default { CacheLeveEnum.DB };
}
