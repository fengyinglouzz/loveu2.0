package com.zxzyyyy.loveu.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_group")
@EntityListeners(AuditingEntityListener.class)
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "group_name")
    private String groupName;


}
