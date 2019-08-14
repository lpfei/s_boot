package com.example.module.ag.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author lpfei
 * @since 2019-08-14
 */
@Entity
@Table(name = "sys_permission2")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysPermission2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String resourceType;

    private String url;

    private String permission;

    private Integer parentId;

    private String parentIds;

    private Long available;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "sys_role_permission2",
            joinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    @TableField(exist = false)
    private List<SysRole2> roles = new ArrayList<>();

}
