package com.epam.service;

import com.epam.entity.Crime;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.StringWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class JsonHandlerService<T> {
    private static final Logger LOGGER = LogManager.getLogger(JsonHandlerService.class);

    JsonHandlerService() {}

    public  ArrayList<T> formArrayListFromURL(InputStream inputStream, Class<T[]> tClass) {
        String resultString = null;
        Gson gson  = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, "UTF-8");
            resultString = writer.toString();
        } catch ( Exception ex) {
            LOGGER.info(ex.getMessage());
        }
        T[] arr = gson.fromJson(resultString, tClass);
        return new ArrayList<T> (Arrays.asList(arr));

    }
}
