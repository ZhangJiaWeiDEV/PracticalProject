package com.example.opentsdb.util;

import org.apache.commons.lang3.math.NumberUtils;
import org.opentsdb.client.OpenTSDBClient;
import org.opentsdb.client.bean.request.Point;
import org.opentsdb.client.bean.request.Query;
import org.opentsdb.client.bean.request.SubQuery;
import org.opentsdb.client.bean.response.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class OpenTSDBUtils {

    @Autowired
    private OpenTSDBClient client;

    public List<QueryResult> query(Long begin,Long end, String metric) throws InterruptedException, ExecutionException, IOException {

        Query query = Query.begin(begin).end(end).timezone("GMT+8")
                .sub(SubQuery.metric(metric)
                        .aggregator(SubQuery.Aggregator.MAX)
                        .build())
                .build();
        // 同步查询
        return client.query(query);
    }

    public List<QueryResult> query(String begin,String end, String metric) throws InterruptedException, ExecutionException, IOException {

        Query query = Query.begin(begin).end(end).timezone("GMT+8")
                .sub(SubQuery.metric(metric)
                        .aggregator(SubQuery.Aggregator.MAX)
                        .build())
                .build();
        // 同步查询
        System.out.println("before" + begin + "&" + end);
        return client.query(query);
    }

    public void put(Long timestamp, String tagName, String tagValue, String metric,String value) {
        Point point = Point.metric(metric)
                .tag(tagName, tagValue)
                .value(timestamp, NumberUtils.createNumber(value))
                .build();
        client.put(point);
    }

    public void delete(String begin, String end,String metric) throws InterruptedException, ExecutionException, IOException, IllegalAccessException {

        Query query = Query.begin(begin).end(end)
                .sub(SubQuery.metric(metric)
                        .aggregator(SubQuery.Aggregator.NONE)
                        .build())
                .build();
        client.delete(query);
    }


    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
    
}
