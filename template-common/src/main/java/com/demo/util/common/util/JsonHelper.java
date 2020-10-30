package com.demo.util.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author dp
 * @date 2020/3/15 10:54 上午
 */

public abstract class JsonHelper {

    /** json mapper */
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        // disable输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // disable序列化时候为空报错
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        //对象属性为NULL 不序列化
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 当为空字符串的时候 可以反序列化为null
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        //日期的转换形式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //驼峰走位
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
    }

    /**
     * 将object对象转为json字符串
     *
     * @param object 待转json的对象
     * @param <T>    对象object的泛型类型
     * @return json字符串
     */
    public static <T> String toJson(@Nullable T object) {
        if (Objects.isNull(object)) {
            return null;
        }
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将json字符串转为clazz类型的java对象
     *
     * @param json  字符串json数据
     * @param clazz 类
     * @param <T>   泛型
     * @return clazz类型的java对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将json字符串转为List类型的集合
     *
     * @param json 字符串json数据
     * @param <T>  泛型
     * @return List类型的集合
     */
    public static <T> List<T> fromJsonList(@Nullable String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return Collections.emptyList();
        }
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);

        try {
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将json字符串转为Map类型的集合
     *
     * @param json 字符串json数据
     * @param <K,  V> 泛型
     * @return Map类型的集合
     */
    public static <K, V> Map<K, V> fromJsonMap(@Nullable String json, Class<K> clazzK, Class<V> clazzV) {
        if (StringUtils.isBlank(json)) {
            return Collections.emptyMap();
        }
        JavaType javaType = mapper.getTypeFactory().constructParametricType(HashMap.class, clazzK, clazzV);
        try {
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 将json串内的某一个path下的json串转换为 object
     *
     * @param json json字符串
     * @param clazz 对应的转换类
     * @param paths 多层路径
     * @param <T> 泛型
     * @return clazz类型的java对象
     */
    public static <T> T fromPathObject(@Nullable String json, @Nonnull Class<T> clazz, @Nullable String... paths) {
        JsonNode jsonNode = parseJsonNode(json, paths);
        if (Objects.isNull(jsonNode) || jsonNode.isNull()) {
            return null;
        }
        try {
            return mapper.treeToValue(jsonNode, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回path路径下的object对象，多用于包装的对象List
     *
     * @param json json字符串
     * @param typeReference 包转的反序列化的类型，如new TypeReference<List<Integer>>()
     * @param paths 多层路径
     * @param <T> 泛型
     * @return clazz类型的java对象
     */
    public static <T> T fromPathObject(@Nonnull String json, @Nonnull TypeReference typeReference, @Nullable String... paths) {
        String pathJson = getString(json, paths);
        if (StringUtils.isBlank(pathJson)) {
            return null;
        }
        try {
            return (T) mapper.readValue(pathJson, typeReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将json串内的某一个path下的json串转换为 List
     *
     * @param json json字符串
     * @param clazz List对应的类
     * @param paths 多层路径key
     * @param <T> 泛型
     * @return clazz对应的list对象
     */
    public static <T> List<T> fromPathArray(@Nullable String json, @Nonnull Class<T> clazz, @Nullable String... paths) {
        JsonNode jsonNode = parseJsonNode(json, paths);
        if (Objects.isNull(jsonNode) || jsonNode.isNull()) {
            return null;
        }
        List<T> list = new ArrayList<>();
        try {
            if (jsonNode.isArray()) {
                for (JsonNode node : jsonNode) {
                    list.add(mapper.treeToValue(node, clazz));
                }
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取json传 K-V 的V值只适应于获取叶子节点的V值
     * 注意：如果{"a":"b","c":{"d":"d1","e":{"f","f1"}}}
     * 当 path为c时候,返回：{"d":"d1","e":{"f","f1"}}
     *
     * @param json json字符串
     * @param paths map的key
     * @return key对应的字符串value
     */
    private static String getString(@Nullable String json, @Nullable String... paths) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        JsonNode jsonNode = parseJsonNode(json, paths);
        if (Objects.isNull(jsonNode) || jsonNode.isNull()) {
            return null;
        }
        if (jsonNode.isValueNode()) {
            return jsonNode.textValue();
        }
        return toJson(jsonNode);
    }

    /**
     * 可以取出类似与fastjson JSONObject类似的一个node tree
     * path 为null时候 返回为 root json node
     * 例如：{"a":"a1","b":"b1","c":{"d":"d1","e":"e2"}}
     * 当path为null或者空时 返回jsonnode为：{"a":"a1","b":"b1","c":{"d":"d1","e":"e2"}}
     * 当path为c 返回jsonnode为： {"d":"d1","e":"e2"}
     *
     * @param json  json串
     * @param paths json key 路径path
     * @return JsonNode
     */
    private static JsonNode parseJsonNode(String json, String... paths) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            JsonNode rootNode = mapper.readTree(json);
            if (!Objects.isNull(paths) && paths.length > 0) {
                for (String pt : paths) {
                    rootNode = rootNode.path(pt);
                    //如果不存在
                    if (rootNode.isMissingNode()) {
                        return null;
                    }
                }
            }
            return rootNode;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
