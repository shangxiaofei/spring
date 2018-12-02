package com.spring.test.service.commons.BeanCopier;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午2:52 2018/6/5
 */
public class CacheValue {

    private Throwable throwable;
    private boolean haveThrowable;
    public Object result;

    public Throwable getThrowable() {
        return throwable;
    }

    public boolean isHaveThrowable() {
        return haveThrowable;
    }

    public Object getResult() {
        return result;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public void setHaveThrowable(boolean haveThrowable) {
        this.haveThrowable = haveThrowable;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
