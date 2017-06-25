package com.CYinHttpClient.Download.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.srx.github.dbgenerator.DownloadEntity;
import com.android.srx.github.dbgenerator.gen.DaoMaster;
import com.android.srx.github.dbgenerator.gen.DaoSession;
import com.android.srx.github.dbgenerator.gen.DownloadEntityDao;
import static com.android.srx.github.dbgenerator.gen.DownloadEntityDao.Properties;

import java.util.List;

/**
 * Created by sunrongxin on 2017/6/25.
 */

public class DownloadHelper {
	private static DownloadHelper instance = new DownloadHelper();
	private DownloadHelper(){

	}
	public static DownloadHelper getInstance(){
		return instance;
	}

	public void init(Context context){

		DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "download.db", null);
		SQLiteDatabase db = helper.getWritableDatabase();
		mMaster = new DaoMaster(db);
		mSession = mMaster.newSession();
		mDao = mSession.getDownloadEntityDao();
	}
	DaoMaster mMaster;
	DaoSession mSession;
	DownloadEntityDao mDao;

	public long insert(DownloadEntity entity){
		return mDao.insertOrReplace(entity);
	}

	public void insertSyn(DownloadEntity entity){
		entity.setId(mDao.insertOrReplace(entity));
	}

	public List<DownloadEntity> getAll(String url){
		List<DownloadEntity> list = mDao.queryBuilder()
				                            .where(Properties.Download_url.eq(url))
				                            .orderAsc(Properties.Thread_id)
				                            .list();
		return list;
	}
}
