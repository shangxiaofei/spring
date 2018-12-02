package com.spring.test.service.javacore.innerclass;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 *
 * (1)内部类的第一个好处就体现出来了 隐藏你不想让别人知道的操作，也即封装性。
 * (2)创建非静态内部类实例的语法：
 *      outerObject=new outerClass(Constructor Parameters);
 *      outerClass.innerClass innerObject=outerObject.new InnerClass(Constructor Parameters);
 *      内部类使用外部类内容的严格格式：OutClass.this.[属性名/方法]
 * (3)一个非静态内部类对象可以访问创建它的外部类对象的内容，甚至包括私有变量！
 *    这是一个非常有用的特性，为我们在设计时提供了更多的思路和捷径。
 *    要想实现这个功能，内部类对象就必须有指向外部类对象的引用
 * (4)一个非静态内部类中，不能有静态数据和静态方法。
 * (5)一个静态内部类：可以拥有这一切，静态属性，非静态属性，静态方法，非静态方法。
 *    静态内部类，只可以访问外部类的的静态属性和方法。不可以访问非静态属性和方法。
 * @date 上午11:03 2018/9/29
 */
public class GoodsTest {
    /**
     * Contents 对象执行算法结果==>110
     * Destination 对象执行算法结果==>123123
     * 通过外部方式创建的内部类实例==>desyac
     */
    public static void main(String[] args) {
        //===========example1=============
        Goods goods=new Goods();
        Contents contents=goods.bulidContents();
        Destination destination=goods.bulidDestination("123123");
        System.out.println("Contents 对象执行算法结果==>"+contents.value());
        System.out.println("Destination 对象执行算法结果==>"+destination.readLabel());


        //==========example2=========
        Goods.MyDestination myContent =goods.new MyDestination("desyac");
        System.out.println("通过外部方式创建的内部类实例==>"+myContent.readLabel());

        //==========example3=========
        Goods.InnerStaicClass  innerStaicClass=new Goods.InnerStaicClass(12,"adfs");
        System.out.println("静态内部类执行结果==>"+innerStaicClass.getInfo());
    }
}
