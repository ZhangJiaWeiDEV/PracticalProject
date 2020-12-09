package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Data
@Document(indexName = "bc_traces", type = "trace")
public class ESObject implements Serializable {
    @Id
    private String id;
    private Object[] scenarioCode;
    private Object traceId;
    private Object timestamp;
    private Object[] orderId;
    private Object orderNumber;
    private Object[] servNo;
    private Object total_time;

    public ESObject(){

    }
}
