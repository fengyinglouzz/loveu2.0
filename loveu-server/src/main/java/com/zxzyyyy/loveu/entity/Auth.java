package com.zxzyyyy.loveu.entity;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "tb_auth")
@EntityListeners(AuditingEntityListener.class)
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String username;

    private String password;

    //注册邮箱
    private String email;

    @JoinColumn(name = "g_id", nullable = false, foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(cascade = CascadeType.ALL)
    private Group group;

    @JoinColumn(name = "r_id", nullable = false, foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;

    //注册日期
    @Column(nullable = false)
    @CreatedDate
    private Date createDate;

}
