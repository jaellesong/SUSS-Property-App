package com.example.susspropertyapp.model;

import java.io.Serializable;

public class Listing implements Serializable {

    private String title;
    private String address;
    private String latitude;
    private String longitude;
    private String description;
    private String type;
    private String bedrooms;
    private String bathrooms;
    private String price;
    private String area;
    private String year;
    private String tenure;
    private String status;
    private String keywords;

    private String agentId;
    private String isSold = "false";


    // 1. Title
    public String getTitle() {
        return title;
    }
    public void setTitle(String thisTitle) {
       this.title = thisTitle;
    }

    // 2. Address
    public String getAddress() {
        return address;
    }
    public void setAddress(String thisAddress){
        this.address = thisAddress;
    }

    // 3. Latitude
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String thisLatitude){
        this.latitude = thisLatitude;
    }

    // 4. Longitude
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String thisLongitude){
        this.longitude = thisLongitude;
    }
    // 5. Description

    public String getDescription(){
        return description;
    }
    public void setDescription(String thisDescription){
        this.description = thisDescription;
    }

    // 6. Type
    public String getType() {
        return type;
    }
    public void setType(String thisType){
        this.type = thisType;
    }

    // 7. Bedrooms
    public String getBedrooms() {
        return bedrooms;
    }
    public void setBedrooms(String thisBedrooms){
        this.bedrooms= thisBedrooms;
    }

    // 8. Bathrooms
    public String getBathrooms() {
        return bathrooms;
    }
    public void setBathrooms(String thisBathrooms){
        this.bathrooms = thisBathrooms;
    }


    // 9. Price
    public String getPrice() {
        return price;
    }
    public void setPrice(String thisPrice){
        this.price = thisPrice;
    }


    // 10. Area
    public String getArea() {
        return area;
    }
    public void setArea(String thisArea){
        this.area = thisArea;
    }

    // 11. Tenure
    public String getYear() {
        return year;
    }
    public void setYear(String thisYear){
        this.year = thisYear;
    }

    // 12. Tenure
    public String getTenure() {
        return tenure;
    }
    public void setTenure(String thisTenure){
        this.tenure = thisTenure;
    }

    // 13. Status
    public String getStatus() {
        return status;
    }
    public void setStatus(String thisStatus){
        this.status = thisStatus;
    }

    // 14. Keywords
    public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String thisKeywords){
        this.keywords = thisKeywords;
    }

    // 15. AgentId
    public String getAgentId() {
        return agentId;
    }
    public void setAgentId(String agentId){
        this.agentId = agentId;
    }

    // 16. isSold
    public String getIsSold(){return isSold; }
    public void setIsSold() { this.isSold = "true"; }
}


