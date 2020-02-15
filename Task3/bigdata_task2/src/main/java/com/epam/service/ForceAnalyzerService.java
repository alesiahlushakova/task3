package com.epam.service;

import com.epam.dao.*;
import com.epam.dao.exception.DAOException;
import com.epam.entity.*;
import com.epam.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ForceAnalyzerService {
    private static final Logger LOGGER = LogManager.getLogger(ForceAnalyzerService.class);
    private LocationDAO locationDAO = new LocationDAO();
    private StreetDAO streetDAO = new StreetDAO();
    private OutcomeObjectDAO outcomeObjectDAO = new OutcomeObjectDAO();
    private OutcomeStatusDAO outcomeStatusDAO = new OutcomeStatusDAO();
    private StopForceDAO stopForceDAO = new StopForceDAO();

    private boolean insertStopForce(StopForce stopForce, int id) throws ServiceException {
        boolean isForceInserted = false;
        try {

            Location location = stopForce.getLocation();
            stopForce.setLocationId(id);
            OutcomeObject outcomeObject = stopForce.getOutcomeObject();
            stopForce.setOutcomeObjectId(outcomeObject.getId());
            if (location != null) {
                Street street = location.getStreet();
                streetDAO.insert(street);
                locationDAO.insert(location);
            }
            if (outcomeObject != null) {
                outcomeObjectDAO.insert(outcomeObject);
            }
            isForceInserted =stopForceDAO.insert(stopForce);
            LOGGER.info("info about force stop inserted");
        } catch (DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex.getCause());
        }
        return isForceInserted;
    }

    public void processRequestForStopsAtLocationsApi(String connectionString) {
        try {
            JsonHandlerService<StopForce> jsonHandlerService = new JsonHandlerService<>();
            URLConnection connection = new URL(connectionString).openConnection();
            List<StopForce> responseAsArrayList = jsonHandlerService.
                    formArrayListFromURL(connection.getInputStream(), StopForce[].class);

            for (int i = 0, x = 1; i < responseAsArrayList.size(); i++, x++) {
                StopForce crimeDto = responseAsArrayList.get(i);
                new ForceAnalyzerService().insertStopForce(crimeDto, x);
            }
        } catch (IOException | ServiceException ex) {
            LOGGER.error(ex.getMessage(), ex.getCause());
        }
    }

    public List<Force> fetchForce(String connectionString) {
        List<Force> forceList =  new ArrayList<>();
        try {
            JsonHandlerService<Force> jsonHandlerService = new JsonHandlerService<>();
            URLConnection connection = new URL(connectionString).openConnection();
            forceList = jsonHandlerService.
                    formArrayListFromURL(connection.getInputStream(), Force[].class);

        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex.getCause());
        }
        return forceList;
    }

}
