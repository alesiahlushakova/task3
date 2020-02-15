package com.epam.dao;

import com.epam.config.DataSource;
import com.epam.dao.exception.DAOException;
import com.epam.entity.OutcomeObject;
import com.epam.entity.Street;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OutcomeObjectDAO implements DAO<OutcomeObject> {
    private static final String INSERT_QUERY = "INSERT INTO outcome_object (id, name) VALUES (?,?)" +
            "on conflict (id)  do update set name=excluded.name";
    private static final String SELECT_QUERY = "SELECT id, name FROM outcome_object";

    @Override
    public boolean insert(OutcomeObject outcomeObject) throws DAOException {
        boolean isSuccess = false;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_QUERY);
        ) {
            pst.setString(1, outcomeObject.getId());
            pst.setString(2, outcomeObject.getName());
            int rs = pst.executeUpdate();
            if (rs > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            throw new DAOException("Street already exists", e.getCause());
        }
        return isSuccess;

    }


}
