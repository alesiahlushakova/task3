package com.epam.service;

import com.epam.dao.*;
import com.epam.dao.exception.DAOException;
import com.epam.entity.*;
import com.epam.service.exception.ServiceException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class CrimeLevelAnalyzerService {
    private static final Logger LOGGER = LogManager.getLogger(CrimeLevelAnalyzerService.class);
    private static final String FILE_TO = "./src/main/resources/crimes.txt";
    private CrimeDAO crimeDAO = new CrimeDAO();
    private LocationDAO locationDAO = new LocationDAO();
    private StreetDAO streetDAO = new StreetDAO();
    private OutcomeStatusDAO outcomeStatusDAO = new OutcomeStatusDAO();
    private StopForceDAO stopForceDAO = new StopForceDAO();



    private boolean insertCrime(Crime crime, int x) throws ServiceException {
        boolean isCrimeInserted = false;
        try {

            Location location = crime.getLocation();
            crime.setLocationId(x);
            crime.setOutcomeStatusId(x);
            Street street = location.getStreet();
            OutcomeStatus outcomeStatus = crime.getOutcomeStatus();
            outcomeStatusDAO.insert(outcomeStatus);
            streetDAO.insert(street);
            locationDAO.insert(location);
            isCrimeInserted = crimeDAO.insert(crime);
            LOGGER.info("crime inserted");
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex.getCause());
        }
        return isCrimeInserted;
    }

    void processRequestToFile(String connectionString) throws ServiceException {
        try {
            URLConnection connection = new URL(connectionString).openConnection();
            InputStream inputStream = connection.getInputStream();

            File file = new File(FILE_TO);
            FileUtils.copyInputStreamToFile(inputStream, file);
        } catch (IOException ex) {
            throw new ServiceException(ex.getMessage(), ex.getCause());
        }
    }

    public void processRequestForCrimeLevelApi(String connectionString) {
        JsonHandlerService<Crime> jsonHandlerService = new JsonHandlerService<>();
        try {
            URLConnection connection = new URL(connectionString).openConnection();
            ArrayList<Crime> responseAsArrayList = jsonHandlerService.
                    formArrayListFromURL(connection.getInputStream(), Crime[].class);

            for (int i = 0, x = 1; i < responseAsArrayList.size(); i++, x++) {
                Crime crimeDto = responseAsArrayList.get(i);
                new CrimeLevelAnalyzerService().insertCrime(crimeDto, x);
            }
        } catch (IOException | ServiceException ex) {
            LOGGER.error(ex.getMessage(), ex.getCause());
        }
    }



}
