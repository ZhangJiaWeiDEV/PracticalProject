package com.example.demo.service;

import com.example.demo.Enums.ScenarioCodeEnum;
import com.example.demo.model.ESObject;
import com.example.demo.model.ScenarioCode;
import com.example.demo.repositort.ESRepository;
import com.example.demo.utils.JsonResult;
import com.example.demo.utils.PagedGridResult;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.mustache.MultiSearchTemplateResponse;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ESObjectServiceImpl implements ESObjectService {
    @Resource
    @Autowired
    private ESRepository repository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Iterator<ESObject> getScenarios(String scenarioCode){
        QueryBuilder queryBuilder = QueryBuilders.termQuery("scenarioCode", scenarioCode);

        Pageable requestPageable = new PageRequest(0, 10000, new Sort(Sort.Direction.DESC, "timestamp"));
        Page<ESObject> page = repository.search(queryBuilder, requestPageable);
        return page.iterator();
    }


    @Override
    public JsonResult queryScenarios(String scenarioCode) {

        QueryBuilder matchAllQuery = QueryBuilders.boolQuery()
                .mustNot(QueryBuilders.termQuery("scenarioCode", "binaryannotations"))
                .mustNot(QueryBuilders.termQuery("scenarioCode", "message"))
                .mustNot(QueryBuilders.termQuery("scenarioCode", "pr_scenariocode"));
        if (!StringUtils.isEmpty(scenarioCode)) {

            String valueForMsg = ScenarioCodeEnum.getValueForMsg(scenarioCode);

            matchAllQuery = QueryBuilders.
                    queryStringQuery(valueForMsg).field("scenarioCode");
        }

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(matchAllQuery);
        nativeSearchQueryBuilder.withSearchType(SearchType.QUERY_THEN_FETCH);
        nativeSearchQueryBuilder.withIndices("bc_traces").withTypes("trace");
        TermsAggregationBuilder termsAggregation = AggregationBuilders.terms("group_scenarioCode").field("scenarioCode").order(BucketOrder.count(false));;
        nativeSearchQueryBuilder.addAggregation(termsAggregation);
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();

        //查询并返回带聚合结果
        AggregatedPage<MultiSearchTemplateResponse.Item> result = elasticsearchTemplate.queryForPage(nativeSearchQuery, MultiSearchTemplateResponse.Item.class);
        //解析聚合
        Aggregations aggregations = result.getAggregations();
        //获取指定名称的聚合
        StringTerms terms = aggregations.get("group_scenarioCode");
        //获取桶
        List<StringTerms.Bucket> buckets = terms.getBuckets();
        //遍历打印
        List<ScenarioCode> scenarioCodes = new ArrayList<>();
        for (StringTerms.Bucket bucket : buckets) {
            ScenarioCode code = new ScenarioCode();
            code.setCount(bucket.getDocCount());
            if (ScenarioCodeEnum.contains(bucket.getKeyAsString())) {
                code.setScenario(ScenarioCodeEnum.getMsgForValue(bucket.getKeyAsString()));
                code.setCode(bucket.getKey());

            }else{
                code.setScenario(bucket.getKey());
                code.setCode(bucket.getKey());
            }
            scenarioCodes.add(code);
        }

        PagedGridResult pagedGridResult = new PagedGridResult();
        pagedGridResult.setPage(result.getNumber());
        pagedGridResult.setTotal(1);
        pagedGridResult.setRecords(buckets.size());
        pagedGridResult.setRows(scenarioCodes);

        return JsonResult.success(pagedGridResult);
    }

}
