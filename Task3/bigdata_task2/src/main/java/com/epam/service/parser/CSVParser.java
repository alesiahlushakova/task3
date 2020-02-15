package com.epam.service.parser;

import com.epam.entity.Location;
import com.epam.service.exception.ServiceException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVParser {
    public List<Location> parseCsv(String filePath) throws ServiceException {
        List<Location> locations = new ArrayList<>();
        try {
            List<String> fileLines = Files.readAllLines(Paths.get(filePath));
            for (String fileLine : fileLines) {
                String[] splattedText = fileLine.split(",");
                ArrayList<String> columnList = new ArrayList<>(Arrays.asList(splattedText));
                Location location = new Location();
                float longitude = checkIsFloat(columnList.get(1));
                float latitude = checkIsFloat(columnList.get(2));
                location.setLongitude(longitude);
                location.setLatitude(latitude);

                locations.add(location);
            }
        } catch (IOException ex) {
           throw new ServiceException(ex.getMessage(), ex.getCause());
        }
        return locations;
    }

    private float checkIsFloat(String parameter) throws ServiceException {
    try {
        return Float.parseFloat(parameter);
    } catch (NumberFormatException ex) {
        throw new ServiceException("unable to parse coords", ex.getCause());
    }
    }

}
