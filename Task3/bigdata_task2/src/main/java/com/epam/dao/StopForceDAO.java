package com.epam.dao;

import com.epam.config.DataSource;
import com.epam.dao.exception.DAOException;
import com.epam.entity.StopForce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StopForceDAO implements DAO<StopForce> {
    private static final String INSERT_QUERY = "INSERT INTO stop_force ( age_range, self_defined_ethnicity," +
            "outcome_linked_to_object_of_search," +
            "datetime, removal_of_more_than_outer_clothing, operation, " +
            "officer_defined_ethnicity, object_of_search, involved_person, gender," +
            "location_id, outcome, type, operation_name, outcome_object_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    @Override
    public boolean insert(StopForce stopForce) throws DAOException {
        boolean isSuccess = false;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_QUERY);
        ) {

            pst.setString(1, stopForce.getAgeRange());
            pst.setString(2, stopForce.getSelfDefinedEthnicity());
            pst.setObject(3,  stopForce.getOutcomeLinkedToObjectOfSearch());
            pst.setDate(4, stopForce.getDatetime());
            pst.setObject(5,  stopForce.getRemovalOfMoreThanOuterClothing());
            pst.setObject(6, stopForce.getOperation());
            pst.setString(7, stopForce.getOfficerDefinedEthnicity());
            pst.setString(8, stopForce.getObjectOfSearch());
            pst.setBoolean(9, stopForce.getInvolvedPerson());
            pst.setString(10, stopForce.getGender());
            pst.setInt(11, stopForce.getLocationId());
            pst.setBoolean(12, stopForce.getOutcome());
            pst.setString(13, stopForce.getType());
            pst.setObject(14,  stopForce.getOperationName());
            pst.setObject(15, stopForce.getOutcomeObject().getId());
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

}
