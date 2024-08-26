package com.seongjoon.springbasic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "st2")
@Table(name = "sample_table_2")
public class SampleTable2Entity {
  @Id
  // @GeneratedValue(strategy = GenerationType.UUID) 동일
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  // sql과 이름이 동일할 시 어노테이션 따로 걸어줄 필요 x
  private Integer sampleAi;
  private Boolean sample_column;
}
