package com.epam.service.converter;

public class UrlParamConverter {
    public  static String convertStringToUrlParam(String param) {
        String[] params = param.toLowerCase().split(" ");
        param = params[0];
        for (int i = 1; i<params.length -1; i++) {
            param +="-" + params[i];
        }
        return param;
    }
}
