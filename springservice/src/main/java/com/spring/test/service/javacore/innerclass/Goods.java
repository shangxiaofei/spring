package com.spring.test.service.javacore.innerclass;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午10:54 2018/9/29
 */
public class Goods {

    private int anInt=10;

    private static int as=10;
    /**
     * 创建一个content对象
     * @return
     */
    public Contents bulidContents(){
        return new MyContent();
    }

    /**
     * 创建一个Destination对象
     * @param a
     * @return
     */
    public Destination bulidDestination(String a){
        return new MyDestination(a);
    }


    /**
     * 内部类 MyContent 实现了 Contents接口
     * 内部类使用外部类内容的严格格式：OutClass.this.[属性名/方法]
     */
    private class MyContent implements Contents{

        private int i=11*Goods.this.anInt;


        @Override
        public int value() {
            return i;
        }
    }


    /**
     * 内部类 MyDestination 实现了Destination接口
     */
    protected class MyDestination implements Destination{

        private String lable;

        public MyDestination(String lable){
            this.lable=lable;
        }


        @Override
        public String readLabel() {
            return lable;
        }
    }

    /**
     * 静态内部类
     */
    static class InnerStaicClass{
        private int age;
        private String name;
        public InnerStaicClass(int age,String name){
            this.name=name;
            this.age=age*as;
        }

        public String getInfo(){
            return "名字："+name+"_年龄："+age;
        }
    }
}
