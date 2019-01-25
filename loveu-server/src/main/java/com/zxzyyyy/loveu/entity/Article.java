package com.zxzyyyy.loveu.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "tb_article")
@EntityListeners(AuditingEntityListener.class)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //标题
    private String title;

    private String content;

    @JoinColumn(name = "g_id", nullable = false, foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    @OneToOne(cascade = CascadeType.ALL)
    private Group group;

    @JoinColumn(name = "a_id", nullable = false, foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    @OneToOne(cascade = CascadeType.ALL)
    private Auth auth;

    @Column(nullable = false)
    @CreatedDate
    private Date createDate;
}
