package com.emedinaa.wifiscanner.storage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.emedinaa.wifiscanner.model.WifiEntity;
import com.j256.ormlite.dao.Dao;

public class WifiRepository {
	private final static String LOG_TAG = "WifiRepository";

	private Dao<WifiEntity, Integer> appDao;

	public WifiRepository(final DatabaseHelper databaseHelper) {
		this.appDao = getAppDao(databaseHelper);
	}

	public void clearData() {
		final List<WifiEntity> wifiEntities = getWifiEntities();
		/*for (final WifiEntity wifiEntity : wifiEntities) {
			deleteWifi(wifiEntity);
		}*/
	}

	public List<WifiEntity> getWifiEntities() {
		try {
			return this.appDao.queryForAll();
		}
		catch (final SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<WifiEntity>();
	}

	public void saveOrUpdateWifi(final WifiEntity wifiEntity) {
		try {
			this.appDao.createOrUpdate(wifiEntity);
		}
		catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	private Dao<WifiEntity, Integer> getAppDao(final DatabaseHelper databaseHelper) {
		if (null == this.appDao) {
			try {
				this.appDao = databaseHelper.getAppDao();
			}
			catch (final SQLException e) {
				Log.e(LOG_TAG, "Unable to load DAO: " + e.getMessage());
				e.printStackTrace();
			}
		}
		return this.appDao;
	}
}
