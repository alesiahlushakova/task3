package com.epam.service.parser;

import com.epam.service.exception.ServiceException;
import org.apache.commons.cli.*;

public class CMDOptionsParser {
    private static final String DATE ="date";

    private CMDOptionsParser() {
    }

    public static String[] getOptions(String[] args) throws ServiceException {
        Options options = new Options();

        Option fileLocation = new Option("path", "path_to_csv", true, "date of crime");
        fileLocation.setRequired(true);
        options.addOption(fileLocation);

        Option crimeLevel= new Option("api", "chosen_api", true, "implementation of api");
        crimeLevel.setRequired(true);
        options.addOption(crimeLevel);



        Option date = new Option(DATE, DATE, true, "date of crime");
        date.setRequired(false);
        options.addOption(date);

        Option dateRangeEnd = new Option("dateEnd", "dateEnding", true, "date range of crime (ending param)");
        date.setRequired(false);
        options.addOption(dateRangeEnd);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        CommandLine cmd;
        String path = null;
        String dateTimeStart = null;
        String dateTimeEnd = null;
        String crimeLevelValue = null;
        try {
            cmd = parser.parse(options, args);
            path = cmd.getOptionValue("path");
            dateTimeStart = cmd.getOptionValue(DATE);
            dateTimeEnd = cmd.getOptionValue("dateEnd");
            crimeLevelValue = cmd.getOptionValue("chosen_api");

        } catch (ParseException e) {
            formatter.printHelp("utility-name", options);
            throw new ServiceException(e.getMessage(), e.getCause());
        }
        return new String[]{dateTimeStart, dateTimeEnd, path, crimeLevelValue};
    }
}
