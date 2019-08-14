package com.example.module.api.admin;

import com.example.module.ag.admin.entity.SysUser2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * description:
 * Created by lpfei on 2019/8/14
 */
@Service
public interface JpaSysUser2Service extends JpaRepository<SysUser2, Integer> {
}
