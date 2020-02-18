package com.epam.controller;


import com.epam.entity.Force;
import com.epam.entity.Location;
import com.epam.service.CrimeLevelAnalyzerService;
import com.epam.service.DateRangeService;
import com.epam.service.ForceAnalyzerService;
import com.epam.service.converter.UrlParamConverter;
import com.epam.service.exception.ServiceException;
import com.epam.service.parser.CMDOptionsParser;
import com.epam.service.parser.CSVParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ApplicationRunner {

    private static final Logger LOGGER = LogManager.getLogger(ApplicationRunner.class);

    public static void main(String[] args) {
        try {


            String[] optionsArray = CMDOptionsParser.getOptions(args);
            String dateTime = optionsArray[0];
            String endDateTime = optionsArray[1];
            String pathToCSV = optionsArray[2];
            String crimeLevelApi = optionsArray[3];
            List<Location> params = new CSVParser().parseCsv(pathToCSV);

            switch (crimeLevelApi) {
                case "crime_level":  processCrimeLevelApi(params, dateTime, endDateTime);
                break;
                case "stop_at_location":  processStopsAtLocationApi(params, dateTime, endDateTime);
                break;
                case "stop_and_search" :  processStopsAndSearchesApi(dateTime, endDateTime);
                break;
                default: break;
            }



        } catch (
                ServiceException ex) {
            LOGGER.info(ex.getMessage());
        }
    }

    private static void processCrimeLevelApi(List<Location> params, String dateTime, String endDateTime) {
        params.parallelStream().forEach(param -> {
            CrimeLevelAnalyzerService crimeLevelAnalyzerService = new CrimeLevelAnalyzerService();
            String connectionString = "https://data.police.uk/api/crimes-street/all-crime?lat=";
            connectionString += param.getLatitude() + "&lng=" + param.getLongitude();
            if (dateTime != null && !dateTime.equals("")) {
                List<String> dateRangeList = DateRangeService.
                        getDateRange(dateTime, endDateTime);
                for (String yearMonth : dateRangeList) {
                    connectionString = "https://data.police.uk/api/crimes-street/all-crime?lat="
                            + param.getLatitude() + "&lng=" + param.getLongitude();
                    connectionString += "&date=" + yearMonth;
                    crimeLevelAnalyzerService.processRequestForCrimeLevelApi(connectionString);
                }

            } else {
                crimeLevelAnalyzerService.processRequestForCrimeLevelApi(connectionString);
            }

        });
    }

    private static void processStopsAndSearchesApi(String dateTime, String endDateTime) {
        List<Force> forceList = new ArrayList<>();

        ForceAnalyzerService forceAnalyzerService = new ForceAnalyzerService();
        String connectionStrings = "https://data.police.uk/api/forces";
        forceList = forceAnalyzerService.fetchForce(connectionStrings);


        forceList.parallelStream().forEach(param -> {
            String connectionString = "https://data.police.uk/api/forces";
            if (dateTime != null && !dateTime.equals("")) {
                List<String> dateRangeList = DateRangeService.
                        getDateRange(dateTime, endDateTime);
                for (String yearMonth : dateRangeList) {
                    connectionString = "https://data.police.uk/api/stops-force";
                    connectionString += "?date=" + yearMonth + "&force=" + UrlParamConverter.convertStringToUrlParam(param.getName());
                    forceAnalyzerService.processRequestForStopsAtLocationsApi(connectionString);
                }
            } else {
                connectionString += UrlParamConverter.convertStringToUrlParam(param.getName());
                forceAnalyzerService.processRequestForStopsAtLocationsApi(connectionString);
            }


            forceAnalyzerService.processRequestForStopsAtLocationsApi(connectionString);
        });


    }

    private static void processStopsAtLocationApi(List<Location> params, String dateTime, String endDateTime) {
        params.parallelStream().forEach(param -> {
            ForceAnalyzerService forceAnalyzerService = new ForceAnalyzerService();

            String connectionString = "https://data.police.uk/api/stops-street?lat=";
            connectionString += param.getLatitude() + "&lng=" + param.getLongitude();
            if (dateTime != null && !dateTime.equals("")) {
                List<String> dateRangeList = DateRangeService.
                        getDateRange(dateTime, endDateTime);
                for (String yearMonth : dateRangeList) {
                    connectionString = "https://data.police.uk/api/stops-street?lat="
                            + param.getLatitude() + "&lng=" + param.getLongitude();
                    connectionString += "&date=" + yearMonth;
                    forceAnalyzerService.processRequestForStopsAtLocationsApi(connectionString);
                }

            } else {
                forceAnalyzerService.processRequestForStopsAtLocationsApi(connectionString);
            }
        });
    }
}
