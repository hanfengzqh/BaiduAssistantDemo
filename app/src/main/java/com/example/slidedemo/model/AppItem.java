package com.example.slidedemo.model;

import java.util.Arrays;

import android.text.TextUtils;

public class AppItem extends BaseInfor {
	/** 应用app的大小 */
	public long mSize;
	/** 应用app的版本号码 */
	public int mVersionCode;
	/** 应用app的惟一标识 */
	public int mLcaId;
	/** 应用app的名称 */
	public String mAppName;
	/** 应用app的包名 */
	public String mPackageName;
	/** 应用app的版本 */
	public String mVersion;
	/** 应用app的图片地址 */
	public String mIconAddr;
	// /** 应用app的平均评分 */
	// public float mRating;
	/** 应用app的热门标识 0否 1是 */
	public int mHot;
	/** 应用app的下载量中文描述 */
	public String mDownloadCount;
	/** 应用app的类别名称 */
	public String mCategoryName;
	/** 应用app的简洁描述 */
	public String mShortDesc;
	/** 应用app的排名描述 */
	public String mRankDesc;
	/** 应用app支持的最小Sdk */
	public int mMinSdk;
	/** 应用app的功能标签信息集合 */
	public String[] featuretags;
	// public List<FeatureTagsInfo> featuretags = new
	// ArrayList<FeatureTagsInfo>();
	/** 应用app的快照列表，图片经过压缩处理 */
	public String[] mSnapList;

	public void setSnapList(String snapList) {
		if (!TextUtils.isEmpty(snapList))
			mSnapList = snapList.split(",");
	}

	// public void setFeatureTags(List<FeatureTagsInfo> featuretags) {
	// this.featuretags = featuretags;
	// }
	public long getSize() {
		return this.mSize;
	}

	public void setSize(long size) {
		this.mSize = size;
	}

	public int getVersioncode() {
		return this.mVersionCode;
	}

	public void setVersioncode(int versioncode) {
		this.mVersionCode = versioncode;
	}

	public int getLcaid() {
		return this.mLcaId;
	}

	public void setLcaid(int lcaid) {
		this.mLcaId = lcaid;
	}

	public String getName() {
		return this.mAppName;
	}

	public void setName(String name) {
		this.mAppName = name;
	}

	public String getPackageName() {
		return this.mPackageName;
	}

	public void setPackageName(String packageName) {
		this.mPackageName = packageName;
	}

	public String getVersion() {
		return this.mVersion;
	}

	public void setVersion(String version) {
		this.mVersion = version;
	}

	public String getIconAddr() {
		return this.mIconAddr;
	}

	public void setIconAddr(String iconAddr) {
		this.mIconAddr = iconAddr;
	}

	// public float getAverageStar() {
	// return this.mRating;
	// }
	//
	// public void setAverageStar(float averageStar) {
	// this.mRating = averageStar;
	// }

	public int getHState() {
		return this.mHot;
	}


	public String getDownloadCount() {
		return this.mDownloadCount;
	}

	public void setDownloadCount(String downloadCount) {
		this.mDownloadCount = downloadCount;
	}

	public String getCategoryName() {
		return this.mCategoryName;
	}

	public void setCategoryName(String categoryName) {
		this.mCategoryName = categoryName;
	}

	public String getShortDesc() {
		return this.mShortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.mShortDesc = shortDesc;
	}

	public void setmRankDesc(String mRankDesc) {
		this.mRankDesc = mRankDesc;
	}

	public void setMinSdkVersion(int mMinSdk) {
		this.mMinSdk = mMinSdk;
	}

	@Override
	public String toString() {
		return "GameItem [mSize=" + mSize + ", mVersionCode=" + mVersionCode
				+ ", mLcaId=" + mLcaId + ", mAppName=" + mAppName
				+ ", mPackageName=" + mPackageName + ", mVersion=" + mVersion
				+ ", mIconAddr=" + mIconAddr + ", mRating=" + ", mHot=" + mHot
				+ ", mDownloadCount=" + mDownloadCount + ", mCategoryName="
				+ mCategoryName + ", mShortDesc=" + mShortDesc + ", mRankDesc="
				+ mRankDesc + ", mMinSdk=" + mMinSdk + ", featuretags="
				+ ", mSnapList=" + Arrays.toString(mSnapList) + "]";
	}

}
