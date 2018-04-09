package com.csx.base;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import sun.reflect.generics.tree.VoidDescriptor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author csx
 * @Package com.csx.base
 * @Description: TODO
 * @date 2018/4/9 0009
 */
public class SetTest {
    private JedisCluster jedis=null;

    @Before
    public void setUp(){
        Set<HostAndPort> hostAndPorts=new HashSet<>();
        hostAndPorts.add(new HostAndPort("192.168.1.30",41012));
        hostAndPorts.add(new HostAndPort("192.168.1.31",41012));
        hostAndPorts.add(new HostAndPort("192.168.1.32",41012));
        jedis=new JedisCluster(hostAndPorts);
    }

    /**
     * set操作
     */
    @Test
    public void set(){
        String result=jedis.set("key_1","value_1");
        System.out.println("result:"+result);
        System.out.println("value:"+jedis.get("key_1"));
    }


    /**
     * set带参数
     * nxxx:NX|XX，NX当值不存在时才设置，XX当值存在时才设置
     * expx:过期时间单位，EX = seconds; PX = milliseconds
     */
    @Test
    public void setByParam(){
        String result=jedis.set("key_1","value_2","XX","EX",10);
        System.out.println("result:"+result);
        System.out.println(jedis.exists("key_1"));
    }

    /**
     * 不存在才设置
     */
    @Test
    public void setnx(){
        long result=jedis.setnx("key_1","value_1");
        System.out.println("result:"+result);
    }

    @Test
    public void append(){
        Long result = jedis.append("key_1", "_append");
        System.out.println(result);
        System.out.println(jedis.get("key_1"));
    }

    /**
     * 设置bit位
     */
    @Test
    public void setBit(){
        Boolean result=jedis.setbit("peter",1,false);
        System.out.println(jedis.getbit("peter",1));
    }

    @Test
    public void setBit2(){
        Boolean result=jedis.setbit("csx",1,"0");
        System.out.println(jedis.getbit("csx",1));
    }

    /**
     * 统计bit位上为1的数量
     */
    @Test
    public void bitCount(){
        jedis.setbit("csx",1,true);
        jedis.setbit("csx",2,false);
        jedis.setbit("csx",3,true);
        Long bitcount = jedis.bitcount("csx");
        System.out.println(bitcount);
    }

    @Test
    public void bitField(){
        List<Long> resultList=jedis.bitfield("bitFiled_1","1","0","1");
        for(Long result:resultList){
            System.out.println(result);
        }
    }


    @Test
    public void decrOrIncr(){
        String result = jedis.set("key_count", "100");
        System.out.println(result);
        Long result_1=jedis.incr("key_count");
        System.out.println(result_1);
        Long result_2=jedis.decr("key_count");
        System.out.println(result_2);
        Long result_3=jedis.incrBy("key_count",10);
        System.out.println(result_3);
        Long result_4=jedis.decrBy("key_count",11);
        System.out.println(result_4);
    }

    @Test
    public void getRange(){
        String result = jedis.set("world", "this is a string");
        System.out.println(result);
        String world = jedis.getrange("world", 0, 4);
        System.out.println(world);
    }

    @Test
    public void getSet(){
        String result = jedis.set("mycount", "1000");
        String mycount=jedis.getSet("mycount","0");
        System.out.println(mycount);
        System.out.println(jedis.get("mycount"));
    }

    @Test
    public void msetOrget(){
        String result = jedis.mset("key1", "value1", "key2", "value2", "key3", "value3");
        List<String> values = jedis.mget("key1", "key2", "key3");
        for(String value:values){
            System.out.println(value);
        }

    }


}
