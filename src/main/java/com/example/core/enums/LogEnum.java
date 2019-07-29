package com.example.core.enums;

/**
 * @Author: lpfei.
 * @Description:操作日志
 * @Date:Created in 2017/8/2.
 * @Modified By:
 */
public class LogEnum {
    /**
     * 操作类型
     */
    public enum Type {
        SPACE(""),
        IN_UP("新增or修改"),
        IN("新增"),
        UP("修改"),
        AU("审核"),
        DE("删除"),
        QU("查询");//不使用

        private String description;

        private Type(String string) {
            description = string;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 模块类型
     */
    public enum Module {
        STUDY("学习中心"),
        MANAGER("人员管理"),
        EXAM("考试中心"),
        SHARE("分润中心"),
        DECL("报单中心"),
        SOCIAL("社交推广"),
        PSP("攻略"),
        RESOURCE("权限资源链接");

        private String description;

        private Module(String string) {
            description = string;
        }

        public String getDescription() {
            return description;
        }
    }


}
