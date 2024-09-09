package com.org.assignmentpractice;

import java.util.List;

public class UserDetailsModel {
    String nameUser , emailUser,phoneUser,websiteUser , companyDetailsUser;
    String addressUser;
    int idUserSearch;
    String userIdSearch;


    CompanyModel companyModel;
    AddressModel addressModels;

    public CompanyModel getCompanyModel() {
        return companyModel;
    }

    public void setCompanyModel(CompanyModel companyModel) {
        this.companyModel = companyModel;
    }

    public UserDetailsModel(String nameUser, String emailUser, String phoneUser, String websiteUser, AddressModel addressModels, CompanyModel companyModel, int idUserSearch ) {
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.phoneUser = phoneUser;
        this.websiteUser = websiteUser;
        //this.addressUser = addressUser;
        this.companyModel = companyModel;
        this.idUserSearch = idUserSearch;
        this.addressModels = addressModels;
    }

    public UserDetailsModel(int idUserSearch , String nameUser, String emailUser, String phoneUser, String websiteUser, String addressUser, String companyDetailsUser) {
        this.idUserSearch = idUserSearch;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.phoneUser = phoneUser;
        this.websiteUser = websiteUser;
        this.addressUser = addressUser;
        this.companyDetailsUser = companyDetailsUser;
    }

    public UserDetailsModel() {

    }

    public AddressModel getAddressModels() {
        return addressModels;
    }

    public void setAddressModels(AddressModel addressModels) {
        this.addressModels = addressModels;
    }

    public UserDetailsModel(int idUserSearch, String userIdSearch) {
        this.idUserSearch = idUserSearch;
        this.userIdSearch = userIdSearch;
    }


    public int getIdUserSearch() {
        return idUserSearch;
    }

    public void setIdUserSearch(int idUserSearch) {
        this.idUserSearch = idUserSearch;
    }

    public String getUserIdSearch() {
        return userIdSearch;
    }

    public void setUserIdSearch(String userIdSearch) {
        this.userIdSearch = userIdSearch;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getWebsiteUser() {
        return websiteUser;
    }

    public void setWebsiteUser(String websiteUser) {
        this.websiteUser = websiteUser;
    }

    public String getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(String addressUser) {
        this.addressUser = addressUser;
    }

    public String getCompanyDetailsUser() {
        return companyDetailsUser;
    }

    public void setCompanyDetailsUser(String companyDetailsUser) {
        this.companyDetailsUser = companyDetailsUser;
    }
}
