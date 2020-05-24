package com.sxt.bean;

public class SpouseImage {

    /**照片id**/
    private String id;

    /**照片url**/
    private String imgurl;

    /**照片描述**/
    private String userdesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getUserdesc() {
        return userdesc;
    }

    public void setUserdesc(String userdesc) {
        this.userdesc = userdesc;
    }

    @Override
    public String toString() {
        return "SpouseImage{" +
                "id=" + id +
                ", imgurl='" + imgurl + '\'' +
                ", userdesc='" + userdesc + '\'' +
                '}';
    }
}
