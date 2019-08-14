package com.example.module.ag.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@Table(name = "sys_user2")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userName;//帐号

    private String name;

    private String passWord;

    private String salt;

    private Integer state;//用户状态,0:创建未认证, 1:正常状态,2：用户被锁定.
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)//CascadeType 级联操作权限
    @JoinTable(
            name = "sys_user_role2",//指定表名
            joinColumns = {@JoinColumn(name = "uid", referencedColumnName = "id")},//指定主键
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}//指定主键
    )
    @TableField(exist = false)
    private List<SysRole2> roleList = new ArrayList<>();
}
