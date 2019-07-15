package com.apexsoft.webapp.modules.mysql.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * @Author: Dinglei
 * @Description:
 * @Date: Created in 上午11:27 2018/2/6
 * @MODIFIED BY:
 */
@Repository
public class RedisDao {

    @Autowired
    private RedisTemplate<Object,Object> template;

    @Autowired
    private StringRedisTemplate stringTemplate;

    public  void setKey(String key,String value){
        ValueOperations<String, String> ops = stringTemplate.opsForValue();
        ops.set(key,value);
    }

    public String getValue(String key){
        ValueOperations<String, String> ops = stringTemplate.opsForValue();
        return ops.get(key);
    }
}
