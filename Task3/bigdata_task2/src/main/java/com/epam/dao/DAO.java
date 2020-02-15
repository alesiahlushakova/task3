package com.epam.dao;


import com.epam.dao.exception.DAOException;

public interface DAO<T > {
    boolean insert(T entity) throws DAOException;

}
