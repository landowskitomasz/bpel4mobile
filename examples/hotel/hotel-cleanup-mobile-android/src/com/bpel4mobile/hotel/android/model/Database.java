package com.bpel4mobile.hotel.android.model;

import java.sql.SQLException;

import android.accounts.Account;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


public class Database extends OrmLiteSqliteOpenHelper{
	
	private static String DATABASE_NAME = "localstorage.db";
	
	private static int DATABASE_VERSION = 1;

	public Database(Context context, Account account) {
		super(context, DATABASE_NAME + "_"+ account.name, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connection) {
		try{
			Log.i(this.getClass().getName(), "Creating tables.");
            TableUtils.createTable(connection, Category.class);
            TableUtils.createTable(connection, Room.class);
            TableUtils.createTable(connection, Request.class);
			TableUtils.createTable(connection, Task.class);
		}
		catch(SQLException e){
			Log.e(this.getClass().getName(), "Unable to create database.");
			throw new IllegalStateException("Unable to create database.", e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connection, int arg2,
			int arg3) {
		try {
			Log.i(this.getClass().getName(), "Removing old tables.");
			TableUtils.dropTable(connection, Task.class, true);
			TableUtils.dropTable(connection, Request.class, true);
            TableUtils.dropTable(connection, Room.class, true);
            TableUtils.dropTable(connection, Category.class, true);
			
			onCreate(database,connectionSource);
		} catch (SQLException e) {
			Log.e(this.getClass().getName(), "Unable to remove old version.");
			e.printStackTrace();
		}
		
	}

}
