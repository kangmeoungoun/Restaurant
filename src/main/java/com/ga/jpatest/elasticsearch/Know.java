package com.ga.jpatest.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;

@Document(indexName = "know",type = "kn")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Know {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    @Lob
    private String content;

    @PersistenceConstructor
    public Know( String title, String content) {
        this.title = title;
        this.content = content;
    }

}
