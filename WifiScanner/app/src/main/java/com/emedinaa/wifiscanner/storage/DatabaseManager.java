package com.emedinaa.wifiscanner.storage;

import android.content.Context;

import com.emedinaa.wifiscanner.model.WifiEntity;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;


/**
 * Created by alienware18 on 9-8-13.
 */
public class DatabaseManager {


    private DatabaseHelper databaseHelper = null;

    //gets a helper once one is created ensures it doesnt create a new one
    public DatabaseHelper getHelper(Context context)
    {
        if (databaseHelper == null) {
            databaseHelper =
                    OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    //releases the helper once usages has ended
    public void releaseHelper()
    {
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
    public void clearDatabase(Context context)
    {
        DatabaseHelper helper = getHelper(context);
        try {
            TableUtils.clearTable(helper.getConnectionSource(), WifiEntity.class);
            //dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
