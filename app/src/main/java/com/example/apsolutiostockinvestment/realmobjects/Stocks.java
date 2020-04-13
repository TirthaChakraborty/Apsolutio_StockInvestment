package com.example.apsolutiostockinvestment.realmobjects;

import java.sql.Time;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Stocks extends RealmObject {
    @PrimaryKey
    private int stock_id;
    private String type;
    private String symbol;
    private String company_ame;
    private String listing_date;
    private String series;
    private int paid_up_value;
    private int market_lot;
    private String isin;
    private int face_value;
    private  int security_code;
    private  String status;
    private String security_id;
    private String security_name;
    private String group;
    private String industry;
    private  String instrument;



    public Stocks() {
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompany_ame() {
        return company_ame;
    }

    public void setCompany_ame(String company_ame) {
        this.company_ame = company_ame;
    }

    public String getListing_date() {
        return listing_date;
    }

    public void setListing_date(String listing_date) {
        this.listing_date = listing_date;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getPaid_up_value() {
        return paid_up_value;
    }

    public void setPaid_up_value(int paid_up_value) {
        this.paid_up_value = paid_up_value;
    }

    public int getMarket_lot() {
        return market_lot;
    }

    public void setMarket_lot(int market_lot) {
        this.market_lot = market_lot;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public int getFace_value() {
        return face_value;
    }

    public void setFace_value(int face_value) {
        this.face_value = face_value;
    }

    public int getSecurity_code() {
        return security_code;
    }

    public void setSecurity_code(int security_code) {
        this.security_code = security_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSecurity_id() {
        return security_id;
    }

    public void setSecurity_id(String security_id) {
        this.security_id = security_id;
    }

    public String getSecurity_name() {
        return security_name;
    }

    public void setSecurity_name(String security_name) {
        this.security_name = security_name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
}
