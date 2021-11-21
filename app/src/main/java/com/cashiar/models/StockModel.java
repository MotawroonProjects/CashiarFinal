package com.cashiar.models;

import java.io.Serializable;

public class StockModel implements Serializable {

    private int id;
    private String ar_title;
    private String en_title;
    private String ar_desc;
    private String en_desc;
    private String is_basic;
    private int added_by_id;
    private String created_at;
    private String updated_at;

    public int getId() {
        return id;
    }

    public String getAr_title() {
        return ar_title;
    }

    public String getEn_title() {
        return en_title;
    }

    public String getAr_desc() {
        return ar_desc;
    }

    public String getEn_desc() {
        return en_desc;
    }

    public String getIs_basic() {
        return is_basic;
    }

    public int getAdded_by_id() {
        return added_by_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
