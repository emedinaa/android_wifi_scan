package com.emedinaa.wifiscanner.storage;

import android.content.Context;

import com.emedinaa.wifiscanner.model.WifiEntity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by alienware18 on 9-8-13.
 */
public class WifiRepository {

    private DatabaseHelper db;
    Dao<WifiEntity, Integer> wifiDao;

    public Dao<WifiEntity, Integer> getWifiDao() {
        return wifiDao;
    }

    public WifiRepository(Context ctx)
    {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(ctx);
            wifiDao = db.getWifiDao();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }

    }

    public int create(WifiEntity wifiEntity)
    {
        try {
            return wifiDao.create(wifiEntity);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public void createUpdate(WifiEntity wifiEntity)
    {
        try {
             wifiDao.createOrUpdate(wifiEntity);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
    }

    public int update(WifiEntity wifiEntity)
    {
        try {
            return wifiDao.update(wifiEntity);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }
    public int delete(WifiEntity wifiEntity)
    {
        try {
            return wifiDao.delete(wifiEntity);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return 0;
    }

    public List<WifiEntity> getAll()
    {
        try {
            return wifiDao.queryForAll();
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }


    public WifiEntity getById(String idCourse)
    {
        try {
            QueryBuilder<WifiEntity, Integer> qb = wifiDao.queryBuilder();
            qb.where().eq("id", idCourse);

            PreparedQuery<WifiEntity> pq = qb.prepare();
            return wifiDao.queryForFirst(pq);

        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }


}
