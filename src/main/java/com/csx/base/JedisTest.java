package com.csx.base;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import java.beans.Transient;

/**
 * @author csx
 * @Package com.csx.base
 * @Description: TODO
 * @date 2018/4/9 0009
 */
public class JedisTest {
    private Jedis jedis=null;
    @Test
    public void connect(){
        Jedis jedis=new Jedis("192.168.1.30",41012);
        jedis.dbSize();
    }

    @Test
    public void connectByHostPort(){
        HostAndPort hostAndPort=new HostAndPort("192.168.1.30",41012);
        Jedis jedis=new Jedis(hostAndPort.getHost(),hostAndPort.getPort());
        System.out.println(jedis.dbSize());
    }

    @Before
    public void setUp(){
        jedis=new Jedis("192.168.1.32",41012);
    }


    @Test
    public void test(){
        System.out.println(jedis.dbSize());
    }

    @Test
    public void testSet(){
        jedis.set("test_key","test_value");
        System.out.println(jedis.get("test_key"));
    }


}
