package com.mysticarts.newapp.dto;

import lombok.Getter;

@Getter
public enum ServiceConstant {

    FILE_PATH("D://newyearproject//profile/"),
    FROM_MAIL("indrajn59@gmail.com"),
    PASSWORD("ratp txck xuhp qzcc"),
    FROM_NAME("New Year");
    private String path;
    ServiceConstant(String path) {

        this.path=path;
    }


}
