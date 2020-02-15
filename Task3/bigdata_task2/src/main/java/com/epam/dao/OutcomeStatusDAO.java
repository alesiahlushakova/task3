package com.epam.dao;

import com.epam.config.DataSource;

import com.epam.dao.exception.DAOException;
import com.epam.entity.OutcomeStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OutcomeStatusDAO implements DAO<OutcomeStatus> {
    private static final String INSERT_QUERY = "INSERT INTO outcome_status (category, date) VALUES (?,?)";


    @Override
    public boolean insert(OutcomeStatus outcomeStatus) throws DAOException {
        boolean isSuccess = false;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_QUERY);
        ) {
            if (outcomeStatus == null) {
                pst.setString(1, null);
                pst.setString(2,null);
                int rs = pst.executeUpdate();
                if (rs > 0) {

                    isSuccess = true;
                }
            } else {
                pst.setString(1, outcomeStatus.getCategory());
                pst.setString(2, outcomeStatus.getDate());
                int rs = pst.executeUpdate();
                if (rs > 0) {

                    isSuccess = true;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Status already exists", e.getCause());
        }
        return isSuccess;
    }

}
