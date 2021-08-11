package com.zw.mylibrary.bean;

import java.util.List;

/**
 * @ProjectName: JetPackMVVMDemo
 * @Package: com.zw.mylibrary.bean
 * @ClassName: MeiZiApiBean
 * @Description: java类作用描述
 * @Author: ltt
 * @CreateDate: 2021/8/11 14:36
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/11 14:36
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */


public class WanAndroidBean {

        private int courseId;
        private int id;
        private String name;
        private int order;
        private int parentChapterId;
        private boolean userControlSetTop;


        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getParentChapterId() {
            return parentChapterId;
        }

        public void setParentChapterId(int parentChapterId) {
            this.parentChapterId = parentChapterId;
        }

        public boolean isUserControlSetTop() {
            return userControlSetTop;
        }

        public void setUserControlSetTop(boolean userControlSetTop) {
            this.userControlSetTop = userControlSetTop;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        private int visible;

}
