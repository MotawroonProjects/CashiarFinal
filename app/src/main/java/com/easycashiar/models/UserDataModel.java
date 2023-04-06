package com.easycashiar.models;

import java.io.Serializable;
import java.util.List;

public class UserDataModel implements Serializable {
private List<UserModel> data;

    public List<UserModel> getData() {
        return data;
    }
}
