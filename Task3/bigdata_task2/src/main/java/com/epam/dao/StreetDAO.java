package com.epam.dao;

import com.epam.config.DataSource;

import com.epam.dao.exception.DAOException;
import com.epam.entity.Street;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StreetDAO implements DAO<Street> {


    private static final String INSERT_QUERY = "INSERT INTO street (id, name) VALUES (?,?)" +
            "on conflict (id)  do update set name=excluded.name";
    private static final String SELECT_QUERY = "SELECT id, name FROM street";

@Override
    public boolean insert(Street street) throws DAOException {
            boolean isSuccess = false;
            try (Connection con = DataSource.getConnection();
                 PreparedStatement pst = con.prepareStatement(INSERT_QUERY);
            ) {
                pst.setInt(1, street.getId());
                pst.setString(2, street.getName());
                int rs = pst.executeUpdate();
                if (rs > 0) {
                    isSuccess = true;
                }
            } catch (SQLException e) {
                throw new DAOException("Street already exists", e.getCause());
            }
            return isSuccess;

    }

    public  List<Street> select() throws DAOException{
        List<Street> streets = null;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_QUERY);
             ResultSet rs = pst.executeQuery();) {
            streets = new ArrayList<Street>();
            Street street;
            while (rs.next()) {
                street = new Street();
                street.setId(rs.getInt("id"));
                street.setName(rs.getString("name"));

                streets.add(street);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception during street selection");
        }
        return streets;
    }
}
