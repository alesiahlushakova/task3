package com.epam.dao;

import com.epam.config.DataSource;

import com.epam.dao.exception.DAOException;
import com.epam.entity.Force;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;


public class ForceDAO implements DAO<Force> {


    private static final String INSERT_QUERY = "INSERT INTO force (id, name) VALUES (?,?)" +
            "on conflict (id)  do update set name=excluded.name";


    @Override
    public boolean insert(Force force) throws DAOException {
        boolean isSuccess = false;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_QUERY);
        ) {
            pst.setString(1, force.getId());
            pst.setString(2, force.getName());
            int rs = pst.executeUpdate();
            if (rs > 0) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        }
        return isSuccess;

    }

}
