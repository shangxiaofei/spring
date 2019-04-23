package com.spring.test.service.javacore.clone;

import org.apache.commons.lang3.SerializationUtils;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午10:11 2019/4/4
 */
public class CloneTest {

    public static void main(String[] args) {
        Person person1=new Person();
        person1.setName("sxf");
        person1.setAge(10);
        Pet pet1=new Pet();
        pet1.setName("xx");
        person1.setPet(pet1);
        Person person2=SerializationUtils.clone(person1);
        person2.setName("chn");
        person2.getPet().setName("yy");
        //person1 name:sxf
        //person2 name:chn
        //pseron1 pet1 name:xx
        //pseron2 pet2 name:yy
        System.out.println("person1 name:"+person1.getName());
        System.out.println("person2 name:"+person2.getName());
        System.out.println("pseron1 pet1 name:"+person1.getPet().getName());
        System.out.println("pseron2 pet2 name:"+person2.getPet().getName());

    }
}
