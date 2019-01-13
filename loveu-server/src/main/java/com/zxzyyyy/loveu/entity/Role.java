package com.zxzyyyy.loveu.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @Description:
 */
@Entity
@Data
@Table(name = "tb_role")
@EntityListeners(AuditingEntityListener.class)

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_name_zh")
    private String roleNameZh;
}
