package com.example.demo.repositort;

import com.example.demo.model.ESObject;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ESRepository extends ElasticsearchRepository<ESObject, String> {

}