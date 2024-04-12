package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//데이터 저장할 엔티티 클래스를 생성.

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublicData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String sigunNm;
    @Column
    private String bizNm;    // 사업명

}
