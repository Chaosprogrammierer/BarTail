package com.example.karsten_adolf.bartail_demo.DBFiles;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.karsten_adolf.bartail_demo.AddbarActivity;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by jonas on 14.05.15.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "Bartail";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DatabaseHelper";

    private Dao<Bar, Integer> barDAO = null;
    private RuntimeExceptionDao<Bar, Integer> barRuntimeDAO = null;

    private Dao<User, Integer> userDAO = null;
    private RuntimeExceptionDao<User, Integer> userRuntimeDAO = null;

    public DatabaseHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION
                //,R.raw.ormlite_config.txt  -> Constructor with config file!
        );

    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try{
            TableUtils.createTable(connectionSource, Bar.class);
            TableUtils.createTable(connectionSource, User.class);
        }
        catch (SQLException e){
            e.printStackTrace();
            Log.d(TAG, "onCreate() failed while creating Tables");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try{
            TableUtils.dropTable(connectionSource, Bar.class, true);
            onCreate(database, connectionSource);

            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(database, connectionSource);
        }
        catch (SQLException e){
            e.printStackTrace();
            Log.d(TAG, "onUpgrade() failed while dropping/creating Tables: " + e.getMessage());
        }

    }

    public Dao<Bar, Integer> getBarDAO() throws SQLException {
        if(barDAO == null)
            barDAO = getDao(Bar.class);
        return barDAO;
    }

    public RuntimeExceptionDao<Bar, Integer> getBarRuntimeExceptionDAO() {
        if(barRuntimeDAO == null)
            barRuntimeDAO = getRuntimeExceptionDao(Bar.class);
        return barRuntimeDAO;
    }

    public Dao<User, Integer> getUserDAO() throws SQLException {
        if(userDAO == null)
            userDAO = getDao(User.class);
        return userDAO;
    }

    public RuntimeExceptionDao<User, Integer> getUserRuntimeExceptionDAO() {
        if(userRuntimeDAO == null)
            userRuntimeDAO = getRuntimeExceptionDao(User.class);
        return userRuntimeDAO;
    }
}



