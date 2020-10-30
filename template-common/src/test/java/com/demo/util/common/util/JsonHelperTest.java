package com.demo.util.common.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author dp
 * @date 2020/3/15 7:44 下午
 */
public class JsonHelperTest {

    @Test
    public void toJsonTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Assert.assertEquals("[1,2]", JsonHelper.toJson(list));

        Map<String, Object> maps = new HashMap<>();
        maps.put("test2", "2342343");
        maps.put("test3", new Date());
        maps.put("test1", 1);
        String result = JsonHelper.toJson(maps);
        Map<String, Object> transferMaps = JsonHelper.fromJsonMap(result, String.class, Object.class);
        String time = (String) transferMaps.get("test3");
        Assert.assertTrue(time.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
    }
}
