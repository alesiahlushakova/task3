package com.epam.dao;

import com.epam.config.DataSource;
import com.epam.dao.exception.DAOException;
import com.epam.entity.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LocationDAO implements DAO<Location> {



    private static final String INSERT_QUERY = "INSERT INTO location ( latitude, longitude, street_id) VALUES (?,?,?)";
    private static final String SELECT_QUERY = "SELECT id, latitude, longitude, street_id FROM street";


    @Override
    public boolean insert(Location location) throws DAOException {
        boolean isSuccess = false;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_QUERY);
        ) {

            pst.setFloat(1, location.getLatitude());
            pst.setFloat(2, location.getLongitude());
            pst.setInt(3, location.getStreet().getId());
            int rs = pst.executeUpdate();
            int x = 0;
            if (rs > 0) {

                isSuccess = true;
            }
        } catch (SQLException e) {
            throw new DAOException("Location already exists", e.getCause());

        }
        return isSuccess;
    }


    public List<Location> select() throws DAOException {
        List<Location> streets = null;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_QUERY);
             ResultSet rs = pst.executeQuery();) {
            streets = new ArrayList<>();
            Location street;
            while (rs.next()) {
                street = new Location();
                street.setId(rs.getInt("id"));
                street.setLatitude(rs.getFloat("latitude"));
                street.setLongitude(rs.getFloat("longitude"));
                streets.add(street);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception during location selection");
        }
        return streets;
    }
}
