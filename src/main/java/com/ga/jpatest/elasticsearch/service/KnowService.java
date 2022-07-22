package com.ga.jpatest.elasticsearch.service;

import com.ga.jpatest.elasticsearch.Know;
import com.ga.jpatest.elasticsearch.dto.KnowDto;
import com.ga.jpatest.elasticsearch.dto.KnowCreateForm;
import com.ga.jpatest.elasticsearch.repository.KnowRepository;
import com.ga.jpatest.elasticsearch.repository.search.KnowSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class KnowService {

    private final KnowRepository knowRepository;
    private final KnowSearchRepository knowSearchRepository;


    @Transactional
    public Long save(KnowCreateForm knowCreateForm) {
        Know know = new Know(knowCreateForm.getTitle(), knowCreateForm.getContent());
        Know newKnow = knowRepository.save(know);
        knowSearchRepository.save(newKnow);
        return newKnow.getId();


    }

    public List<KnowDto> searchByTitle(String title) {
        List<KnowDto> list = new ArrayList<>();
        if(StringUtils.isEmpty(title)){
            return findAll(list);
        }
        return knowSearchRepository.findByTitleContaining(title)
                .stream()
                .map(KnowDto::from)
                .collect(Collectors.toList());
    }

    private List<KnowDto> findAll(List<KnowDto> list) {
        knowSearchRepository.findAll()
                .forEach(know -> list.add(new KnowDto(know.getId(), know.getTitle(), know.getContent())));
        return list;
    }

    public KnowDto findById(Long id){
        return knowSearchRepository.findById(id)
                .map(KnowDto::from)
                .orElseThrow(() -> new IllegalArgumentException("해당ID 가 존재하지 않습니다."));
    }

}