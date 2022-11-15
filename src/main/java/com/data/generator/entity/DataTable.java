package com.data.generator.entity;

import org.hibernate.annotations.DynamicInsert;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_table")
@lombok.Data
@DynamicInsert
public class DataTable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String email;

    private  String phone;
}
