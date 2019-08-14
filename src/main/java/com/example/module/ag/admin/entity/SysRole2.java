package com.example.module.ag.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.module.ag.user.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author lpfei
 * @since 2019-08-14
 */
@Entity
@Table(name = "sys_role2")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String role;

    private String description;

    private Long available;

    //角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "sys_role_permission2",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")}
    )
    @TableField(exist = false)
    private List<SysPermission2> permissions = new ArrayList<>();

    // 用户 - 角色关系定义;
    @ManyToMany
    @JoinTable(
            name = "sys_user_role2",//指定表名
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},//指定主键
            inverseJoinColumns = {@JoinColumn(name = "uid", referencedColumnName = "id")}//指定主键
    )
    @TableField(exist = false)
    private List<SysUser2> userInfos = new ArrayList<>();

}
