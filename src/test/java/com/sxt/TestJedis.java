package com.sxt;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestJedis {

    @Test
    public void testJedisSingle(){

        Jedis jedis = new Jedis("192.168.2.129", 6379);
        Set<String> keys = jedis.keys("s*");
        if(keys != null){
            for (String key :keys){
                System.out.println(key);
            }
        }

        String str = jedis.get("str");
        System.out.println(str);
        Map<String, String> user = jedis.hgetAll("user");
        Collection<String> values = user.values();
        for(String v :values){
            System.out.println(v);
        }

        jedis.close();
    }


    @Test
    public void testJedisSingleByPool() {

        JedisPool pool = new JedisPool("192.168.2.129", 6379);
        Jedis jedis = null;

        try  {
            jedis = pool.getResource();

            List<String> list1 = jedis.lrange("list1", 0, -1);
            for (String ss : list1){
                System.out.println(ss);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(jedis != null){
                //关闭连接
                jedis.close();
            }
        }

    }

}
