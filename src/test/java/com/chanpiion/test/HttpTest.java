package com.chanpiion.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author April Chen
 * @date 2020/5/12 10:30
 */
public class HttpTest {
    private OkHttpClient httpClient;

    private String url = "http://stocktag.news/stocktag?domain=index&code=";

    @Before
    public void before() {
        httpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    @Test
    public void testTag() throws IOException {
        Request request = new Request.Builder().url("http://stocktag.news/stocktag?domain=index&code=000001")
                .addHeader("X-Arsenal-Auth", "kgserver.wencai")
                .build();
        Response response = httpClient.newCall(request).execute();
        String res = response.body().string();
        List<String> tags = (List<String>) JSONPath.read(res, "$.data.tags..tag_name");
        System.out.println(tags);
    }

    public List<String> queryTag(String code) {
        Request request = new Request.Builder().url("http://stocktag.news/stocktag?domain=index&code=" + code)
                .addHeader("X-Arsenal-Auth", "kgserver.wencai")
                .build();
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            String res = response.body().string();
            List<String> tags = (List<String>) JSONPath.read(res, "$.data.tags..tag_name");
            System.out.println(tags.size());
            return tags;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<JSONObject> tagToEvent(String code, String domain, String instance, String datetime, Map<String, String> tagDesMap) {
        List<String> tags = queryTag(code);
        String id = "000001".equals(code) ? "1A0001" : code;
        return tags.parallelStream().map(tag -> {
            JSONObject event = new JSONObject();
            event.put("event_name", tag);
            event.put("event_source", "tag");
            event.put("date_time", datetime);
            event.put("subject_type", domain);
            event.put("subject_id", id);
            event.put("subject_name", instance);
            event.put("event_instance", tagDesMap.get(tag));
            return event;
        }).collect(Collectors.toList());
    }

    public JSONArray dataQuery() {
        try {
            Request request = new Request.Builder().url("http://172.19.80.202:9111/dataquery?ret=json_all&q=%E6%A6%82%E5%BF%B5%E6%8C%87%E6%95%B0").build();
            Response response = null;
            response = httpClient.newCall(request).execute();
            String res = response.body().string();
            JSONObject json = JSONObject.parseObject(res);
            return json.getJSONArray("datas");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> tagDesMap() {
        Map<String, String> map = new HashMap<>();
        try (InputStream inputStream = new FileInputStream("d:\\tmp\\tag.txt");
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(",");
                String tag = "";
                String des = "";
                for (String str : array) {
                    String[] pair = str.split(":");
                    if (pair[0].equals("name")) {
                        tag = pair[1];
                    }
                    if (pair[0].equals("description")) {
                        des = pair[1];
                    }
                }
                map.put(tag, des);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        System.out.println(map);
        return map;
    }

    @Test
    public void testEvent() throws IOException {
        String datetime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        Map<String,String> tagDesMap = tagDesMap();
        List<JSONObject> allEvents = new LinkedList<>();
        tagDesMap.forEach((k,v)->{
            JSONObject event = new JSONObject();
            event.put("event_name", k);
            event.put("event_source", "tag");
            event.put("date_time", datetime);
            event.put("subject_type", "指数");
            event.put("subject_id", "1A0001");
            event.put("subject_name", "上证指数");
            event.put("event_instance", v);
            allEvents.add(event);
        });


//        List<JSONObject> events = tagToEvent("000001", "指数", "上证指数", datetime, tagDesMap);
//        allEvents.addAll(events);
//        JSONArray indexCodes = dataQuery();
//        indexCodes.stream().forEach(obj -> {
//            JSONObject item = (JSONObject) obj;
//            String code = StringUtils.substringBefore(item.getString("指数代码"), ".");
//            String name = item.getString("指数简称");
//            List<JSONObject> list = tagToEvent(code, "概念", name, datetime,tagDesMap);
//            allEvents.addAll(list);
//
//        });
        FileUtils.write(new File("d:\\tmp\\event.json"), JSON.toJSONString(allEvents), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        System.out.println("test");

    }

}
