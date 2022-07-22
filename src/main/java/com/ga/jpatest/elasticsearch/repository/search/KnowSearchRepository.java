package com.ga.jpatest.elasticsearch.repository.search;

import com.ga.jpatest.elasticsearch.Know;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowSearchRepository extends ElasticsearchRepository<Know,Long> {
    List<Know> findByTitleContaining(String title);
    List<Know> findByContentContaining(String content);
}
