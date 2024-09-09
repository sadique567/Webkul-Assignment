package com.org.assignmentpractice;

public class CompanyModel {
    String nameCo  , catchPharesCo , bsCo;

    public CompanyModel(String nameCo, String catchPharesCo, String bsCo) {
        this.nameCo = nameCo;
        this.catchPharesCo = catchPharesCo;
        this.bsCo = bsCo;
    }

    public String getNameCo() {
        return nameCo;
    }

    public void setNameCo(String nameCo) {
        this.nameCo = nameCo;
    }

    public String getCatchPharesCo() {
        return catchPharesCo;
    }

    public void setCatchPharesCo(String catchPharesCo) {
        this.catchPharesCo = catchPharesCo;
    }

    public String getBsCo() {
        return bsCo;
    }

    public void setBsCo(String bsCo) {
        this.bsCo = bsCo;
    }
}
