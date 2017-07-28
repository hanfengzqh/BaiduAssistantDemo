package com.example.slidedemo.model;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.zng.library.Constants;

/***
 * 单个app应用详情
 * 打开单个app详情则会将相关数据存储到数据库
 * @author zqh
 *
 */
public class AppApk extends BaseInfor {

	/** 1.应用app所属分类 */
	public String mAppTypeCode;
	/** 2.应用app包名 */
	public String mPackageName;// packageName 包名
	/** 3.应用app名称 */
	public String mAppName;// name 应用名称
	/** 4.应用app的版本描述 */
	public String mVersion;
	/** 5.应用app版本号码 */
	public int mVersionCode;// versioncode 版本号码
	/** 6.支持的语言 */
	public String mSupportedLanguage;
	/** 7.是否是精品推荐 */
	public int mExcellent;
	/** 8.是否是官方发布应用 */
	public int mOffical;
	/** 9.安全性 */
	public int mSecurity;
	/** 10.应用app的详细描述 */
	public String mDetailDesc;
	/** 11.应用app的简洁描述 */
	public String mShortDesc;
	/** 12.是否是首次发布 */
	public int mFirstPublish;
	/** 13.正式版还是测试版 */
	public int mVersionBeta;
	/** 14.程序发布的时间 */
	public String mPublishDate;
	/** 15.发行商id 00100007 */
	public String mVidCode;
	/** 16.发行商名称 河南银联 */
	public String mVidName;
	/** 17.发行商sn H12160011060 */
	public String mPsnCode;
	/** 18.设备SN 122006000075 */
	public String mDsnCode;
	/** 19.下载次数 279万次 */
	public String mDownloadCount;
	/** 20.下载详细次数 274595 */
	public int mRealDownloadCount;
	/** 21.应用app的惟一标识 */
	public int mLcaId;
	/** 22.应用app的图片地址 */
	public String mIconAddr;// iconAddr 图片地址
	/** 23.应用app的大小 */
	public long mSize;
	/** 24.应用app 大小 */
	public String mSizeSpace;
	/** 25.应用app的快照列表，图片经过压缩处理 */
	public String[] mSnapList;
	/** 26.应用app的快照列表全屏 */
	public String[] mFullSnapList;
	/** 27.应用app所属类型 */
	public String mAppType;
	/** 28.应用app所属的分类 */
	public String[] categoryname;
	/** 29.支持的最低安卓版本 */
	public int minSdk;
	/** 30.SDK的名字 */
	public String minSdkName;
	/** 31.详情界面数据请求回来的下发时间 */
	public long mDetailCheckTime;
	/** 32.app使用的次数频率 */
	public String mAppPlayTimes;
	/** 33.当前系统版本号*/
	public String mSysVersion;
	/** 34.新增功能标签 */
	public String[] featuretags;
	/**35用户检测时间*/
	public long mCheckTime;
	/**36hState 热门标识 0否 1是*/
	public int mHot = 0;
	
	
	// ---------非数据库列---------------
	public String mCommentMd5;
    public boolean mIsInstalled = false;// 是否已经安装
    public boolean mIsDownload = false;// 是否已经下载
    public int mIsRecommend = 0;// 是否是分类列表中的推荐应用,既不存数据库，也不是拉下来的，而是判断第一次拉数据的第一个就是推荐的
	
    
    public String mCategoryName; // 类别名称(十分类)
    
	public static final AppApk createFromCursor(Cursor c) {
		AppApk app = new AppApk();
		app.mAppTypeCode = c.getString(0);
		app.mPackageName = c.getString(1);
		app.mAppName = c.getString(2);
		app.mVersion = c.getString(3);
		app.mVersionCode = c.getInt(4);
		app.mSupportedLanguage = c.getString(5);
		app.mExcellent = c.getInt(6);
		app.mOffical = c.getInt(7);
		app.mSecurity = c.getInt(8);
		app.mDetailDesc = c.getString(9);
		app.mShortDesc = c.getString(10);
		app.mFirstPublish = c.getInt(11);
		app.mVersionBeta = c.getInt(12);
		app.mPublishDate = c.getString(13);
		app.mVidCode = c.getString(14);
		app.mVidName = c.getString(15);
		app.mPsnCode = c.getString(16);
		app.mDsnCode = c.getString(17);
		app.mDownloadCount = c.getString(18);
		app.mRealDownloadCount = c.getInt(19);
		app.mLcaId = c.getInt(20);
		app.mIconAddr = c.getString(21);
		app.mSize = c.getInt(22);
		app.mSizeSpace = c.getString(23);
		String snap = c.getString(24);
		if (snap == null) {
            app.mSnapList = null;
        } else {
            app.mSnapList = snap.split("\\|");
        }
		
		String fullSnap = c.getString(25);
        if (fullSnap == null) {
            app.mFullSnapList = null;
        } else {
            app.mFullSnapList = fullSnap.split("\\|");
        }
		app.mAppType = c.getString(26);
        
		String categoryName = c.getString(27);
		if (categoryName == null) {
			app.categoryname = null;
		}else{
			app.categoryname = categoryName.split("\\|");
		}
		
		app.minSdk = c.getInt(28);
		app.minSdkName = c.getString(29);
		app.mCheckTime = c.getLong(30);
		app.mDetailCheckTime = c.getLong(31);
		app.mAppPlayTimes = c.getString(32);
		app.mSysVersion = c.getString(33);
		app.mHot = c.getInt(34);
		
		return app;
	}
	
	/**
	 * 更新一下app下载状态
	 * @param context
	 */
	public void updateDownloadState(Context context){
       
    }
	
	public AppApk updateAppState(){
    	
    	return this;
    }
	
}
