package com.spring.test.study.mybatis;



import com.spring.test.study.entry.Blog;
import com.spring.test.study.mapper.BlogMapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午1:58 2018/8/11
 */
public class TestMybatis {
    public static void main(String[] args) throws FileNotFoundException {
//       test01();
        test02();
    }


    public static void test02() throws FileNotFoundException {
        String resuources="/Users/shangxiaofei/sxfwork/sxfproject3/spring/springcore/src/test/resources/mybatis-config.xml";
        InputStream inputStream=new FileInputStream(resuources);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BlogMapper mapper= sqlSession.getMapper(BlogMapper.class);
        Blog blog= mapper.getById(1);
        System.out.println("博客的名字为=>"+blog.getName());
    }

    public static void test01() throws FileNotFoundException {
        String resuources="/Users/shangxiaofei/sxfwork/sxfproject3/spring/springcore/src/test/resources/mybatis-config.xml";
        InputStream inputStream=new FileInputStream(resuources);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        Map<String,Object> map=new HashMap<>();
        map.put("id",1);
        Blog blog=sqlSession.selectOne("com.spring.test.study.mapper.BlogMapper.getById",map);
        System.out.println("blog的内容为："+blog.getContext());
        System.out.println("blog的时间为："+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(blog.getCreateDateTime()));
    }
}
