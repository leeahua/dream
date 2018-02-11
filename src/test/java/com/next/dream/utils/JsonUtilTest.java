package com.next.dream.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonUtilTest {

    @Test
    public void toJsonStirng() {
        Map<String,String> map = new HashMap<>();
        map.put("test","test");
        JsonUtil.toJson((Object) map);

    }

    @Test
    public void praseJson(){
        Map<String,String> map = new HashMap<>();
        map.put("test","test");
        String jsonStr = JsonUtil.toJson((Object) map);
        Map<String,String> map2 = JsonUtil.parseJson(jsonStr,HashMap.class);
        System.out.println(JsonUtil.toJson(map2));
    }

    @Test
    public void parseJsonArray(){
        Map<String,String> map = new HashMap<>();
        map.put("test","test");
        List<Map<String,String>> list = new ArrayList<>();
        list.add(map);
        List<Map<String,String>> list2 = JsonUtil.parseJsonArray(JsonUtil.toJson(list));
        System.out.println(JsonUtil.toJson(list2));

    }
}