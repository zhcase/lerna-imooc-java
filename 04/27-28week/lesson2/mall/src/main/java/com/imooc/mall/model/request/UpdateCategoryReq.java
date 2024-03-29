package com.imooc.mall.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateCategoryReq {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull(message = "id不能为空")
    private  Integer id;

    /**
     * AddCategoryReq
     */
        @Size(min = 2, max = 5)
        private String name;

        @Max(3)
        private Integer type;

        private Integer parentId;
        private Integer orderNum;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public Integer getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(Integer orderNum) {
            this.orderNum = orderNum;
        }


}
