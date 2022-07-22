package com.ga.jpatest.elasticsearch.service;

import com.ga.jpatest.elasticsearch.Know;
import com.ga.jpatest.elasticsearch.dto.*;
import com.ga.jpatest.elasticsearch.repository.KnowRepository;
import com.ga.jpatest.elasticsearch.repository.search.KnowSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class KnowService {

    private final KnowRepository knowRepository;
    private final KnowSearchRepository knowSearchRepository;

    @Transactional
    public Long update(KnowUpdateForm knowUpdateForm) {
        Know know = knowRepository.findById(knowUpdateForm.getId()).orElseThrow(() -> new IllegalArgumentException("해당ID 가 존재하지 않습니다."));
        know.change(knowUpdateForm);
        knowSearchRepository.save(know);
        return know.getId();
    }


    @Transactional
    public Long save(KnowCreateForm knowCreateForm) {
        Know know = new Know(knowCreateForm.getTitle(), knowCreateForm.getContent());
        Know newKnow = knowRepository.save(know);
        knowSearchRepository.save(newKnow);
        return newKnow.getId();


    }

    public List<KnowDto> searchByTitleOrContent(KnowSearchForm knowSearchForm) {
        if(StringUtils.isEmpty(knowSearchForm.getSearchInput())){
            return toList(knowSearchRepository.findAll());
        }
        NativeSearchQueryBuilder searchQuery = createQuery(knowSearchForm);
        return toList(knowSearchRepository.search(searchQuery.build()));
    }

    private NativeSearchQueryBuilder createQuery(KnowSearchForm knowSearchForm) {
        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = boolQuery();

        boolQueryBuilder.should(queryStringQuery(knowSearchForm.getSearchInput()).analyzeWildcard(true)
                .field(knowSearchForm.getSearch().name().toLowerCase(Locale.ROOT)));

        searchQuery.withQuery(boolQueryBuilder);
        searchQuery.withSort(SortBuilders.fieldSort("id").order(SortOrder.ASC));
        return searchQuery;
    }

    private List<KnowDto> toList(Iterable<Know> list) {
        List<KnowDto> dtos = new ArrayList<>();
        list.forEach(know -> dtos.add(new KnowDto(know.getId(), know.getTitle(), know.getContent())));
        return dtos;
    }

    public KnowDto findById(Long id){
        return knowSearchRepository.findById(id)
                .map(KnowDto::from)
                .orElseThrow(() -> new IllegalArgumentException("해당ID 가 존재하지 않습니다."));
    }

}