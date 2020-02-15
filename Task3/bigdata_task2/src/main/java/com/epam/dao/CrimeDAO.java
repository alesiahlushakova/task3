package com.epam.dao;

import com.epam.config.DataSource;
import com.epam.dao.exception.DAOException;
import com.epam.entity.Crime;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.logging.SimpleFormatter;

public class CrimeDAO implements DAO<Crime>{

    private static final String INSERT_QUERY = "INSERT INTO crime (id, category, location_type, location_id, context,"
           + " persistent_id, outcome_status_id, location_subtype, month) VALUES (?,?,?,?,?,?,?,?,?) " +
            "on conflict (id)  do update set category=excluded.category," +
            " location_type=excluded.location_type," +
            "location_id=excluded.location_id," +
            "context=excluded.context," +
            "persistent_id=excluded.persistent_id," +
            "outcome_status_id=excluded.outcome_status_id," +
            "location_subtype=excluded.location_subtype," +
            "month=excluded.month ";


@Override
    public boolean insert(Crime crime) throws DAOException {
        boolean isSuccess = false;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_QUERY);
        ) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String dateInString = crime.getMonth();
            java.util.Date date = sdf.parse(dateInString);
            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());


            pst.setInt(1, crime.getId());
            pst.setString(2, crime.getCategory());
            pst.setString(3, crime.getLocationType());
            pst.setInt(4, crime.getLocationId());
            pst.setString(5, crime.getContext());
            pst.setString(6, crime.getPersistentId());
            pst.setInt(7, crime.getOutcomeStatusId());
            pst.setString(8, crime.getLocationSubtype());
            pst.setDate(9, sqlStartDate);
            int rs = pst.executeUpdate();
            if (rs > 0) {
                isSuccess = true;
            }
        } catch (SQLException | ParseException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return isSuccess;
    }
}
