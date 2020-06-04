package com.sxt.bean;

public class TArea {


    /**地区编号**/
    private String areaId;

    /**地域父id**/
    private String parentAreaId;

    /**地区或单位**/
    private String areaOrCompany;

    /**状态 1:启用 0:不启用**/
    private Integer status;

    /**是否为父节点**/
    private Boolean isParent;

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        isParent = isParent;
    }

    public String getPid() {
        return parentAreaId;
    }

    public void setPid(String pid) {
        this.parentAreaId = pid;
    }

    public String getId() {
        return areaId;
    }

    public void setId(String id) {
        this.areaId = id;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getParentAreaId() {
        return parentAreaId;
    }

    public void setParentAreaId(String parentAreaId) {
        this.parentAreaId = parentAreaId;
    }

    public String getAreaOrCompany() {
        return areaOrCompany;
    }

    public String getName() {
        return areaOrCompany;
    }

    public void setName(String name){
        this.areaOrCompany=name;
    }

    public void setAreaOrCompany(String areaOrCompany) {
        this.areaOrCompany = areaOrCompany;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TArea{" +
                ", areaId='" + areaId + '\'' +
                ", parentAreaId='" + parentAreaId + '\'' +
                ", areaOrCompany='" + areaOrCompany + '\'' +
                ", status=" + status +
                '}';
    }
}
