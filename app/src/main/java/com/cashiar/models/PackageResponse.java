package com.cashiar.models;

import java.io.Serializable;

public class PackageResponse implements Serializable {
    private int status;
    private String data;

    public int getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }
}
