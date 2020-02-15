package com.epam.service;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class DateRangeService {
    private DateRangeService() {}
    public static List<String> getDateRange(String dateTime,
                                            String endDateTime) {

        List<String> dateRangeList = new ArrayList<>();
        YearMonth start = YearMonth.parse(dateTime);
        if (endDateTime != null && !endDateTime.equals("")) {
            YearMonth stop = YearMonth.parse(endDateTime);
            int initialCapacity = ((int) ChronoUnit.MONTHS.between(start, stop)) + 1;
            List<YearMonth> yms = new ArrayList<>(initialCapacity);

            YearMonth ym = start.plusMonths(1);
            while (ym.isBefore(stop)) {
                yms.add(ym);
                ym = ym.plusMonths(1);
            }

            for (YearMonth yearMonth : yms) {
                dateRangeList.add(yearMonth.toString());
            }

        }
        return dateRangeList;
    }
}

