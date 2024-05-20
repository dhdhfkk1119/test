package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(unique = true)
    private Long q;
    private Long w;
    private Long e;
    private Long r;
    private Long a;
    private Long s;
    private Long d;
    private Long f;

    private LocalDateTime createAt;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private Integer views;

    public Integer getViews() {
        return this.views != null ? this.views : 0; // 만약 view가 null이면 0을 반환하도록 수정
    }

}
