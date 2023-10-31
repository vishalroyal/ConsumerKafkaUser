package com.redis.redisproject.Controller;

import com.redis.redisproject.Model.Person;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource(name="redisTemplate")
    private HashOperations<String,String, Person> hashOperations;

    @GetMapping("/{key}")
    public Person getPersondata(@PathVariable("key") String key)
    {
        System.out.println(" fetch person begalf of id "+key);
        return hashOperations.get("Person",key);
    }

    @GetMapping
    public Map<String, Person> getPersondata()
    {
        System.out.println(" All entries ");
        return hashOperations.entries("Person");
    }

    @PostMapping(value = "/set",consumes = "application/json")
    public ResponseEntity<String> storeData(@RequestBody Person person){
        System.out.println(" adding values in redis");
        hashOperations.put("Person",person.getId(),person);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }


}