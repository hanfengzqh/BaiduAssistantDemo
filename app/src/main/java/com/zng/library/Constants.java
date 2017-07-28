package com.zng.library;

import android.os.Environment;

public class Constants {

	public static final String HOME_ACTIVITY = "com.zng.appcenter.action.MAINACTIVITY";
//	public static final String OWN_PACKAGENAME = ConfigFactory.getConfig()
//			.getPackageName();
	public static final String BASEPATH = Environment.getExternalStorageDirectory().getAbsolutePath();
	public static final int INVALID = -1;

	/** 100开头 */
	public static class Key {

		public static final String KEY_SPLASH = "key_splash";
		public static final String KEY_SPLASH_DISPLAY_TIME = "key_splash_display_time";
		public static final String KEY_TOTAL_MEMORY = "key_total_memory";
		public static final String KEY_SEARCH_WORD = "key_search_word";
		public static final String KEY_SEARCH_SOURCE = "key_search_source";
		public static final String PUBLISHER_ID = "publisher_id";
		public static final String DEVICE_CODE = "device_code";
		public static final String GC_SYS_SCAN_CLEANUP = "gc_sys_scan_cleanup";
		public static final String FIRST_BOOT = "first_boot";
		public static final String TYPE_GUIDE = "type_guide";
		public static final String GUIDE_SERVER_PATH = "guide_server_path";
		public static final String KEY_INDEX_TAB_ID = "index_tab_id";// 底部标签ID
		public static final String KEY_DOWNLOAD_LOCATION = "key_download_location";
		public static final String KEY_INDEX = "key_index";
		public static final String KEY_LOCAID = "key_mlcaid";
		public static final String KEY_PACKAGE_NAME = "key_package_name";
		public static final String KEY_VERSIONCODE = "key_versioncode";
		public static final String KEY_APP_NAME = "key_app_name";
		public static final String KEY_ICON_ADDR = "key_icon_addr";
		public static final String KEY_AUTO_INSTALL = "key_auto_install";
		public static final String KEY_PERCENTAGE = "key_percentage";
		public static final String KEY_EXCEPTION = "key_exception";

	}

	public static final class Time {
		public static final int SECOND = 1000;
		public static final int MINUTE = SECOND * 60;
		public static final int HOUR = MINUTE * 60;
		public static final int DAY = HOUR * 24;
	}

	/** 200开头 */
	public static final class Message {
		/** 网络不可用 */
		public static final int MSG_NETWORK_UNAVAILABLE = 200;
		/** 网络可用 */
		public static final int MSG_NETWORK_AVAILABLE = 201;
		/** 输入法控制面板显示 */
		public static final int MSG_SHOW_IME = 204;
		/***/
		// public static final int MSG_SHOW_VIBRATOR_TIP = 167;
		/***/
		public static final int MSG_HIDE_SEARCH_WORD_DROPVIEW = 205;

		/** 缓存垃圾扫描完成 */
		public static final int MSG_CACHE_GC_SCAN = 206;
		/** 缓存垃圾清理完成 */
		public static final int MSG_CACHE_GC_CLEANUP = 207;
		/** 未安装app扫描完成 */
		public static final int MSG_NOTINSTALL_GC_SCAN = 208;
		/** 未安装app清理完成 */
		public static final int MSG_NOTINSTALL_GC_CLEANUP = 209;
		/** 卸载残留扫描完成 */
		public static final int MSG_UNINSTALL_GC_SCAN = 210;
		/** 卸载残留清理完成 */
		public static final int MSG_UNINSTALL_GC_CLEANUP = 211;
		/** 广告垃圾扫描完成 */
		public static final int MSG_AD_GC_SCAN = 212;
		/** 广告垃圾清理完成 */
		public static final int MSG_AD_GC_CLEANUP = 213;
		/** 无用安装包扫描完成 */
		public static final int MSG_NOTUSE_GC_SCAN = 214;
		/** 无用安装包清理完成 */
		public static final int MSG_NOTUSE_GC_CLEANUP = 215;
		/** 显示Home界面 */
		public static final int MSG_SPLASH_DELAY_DISPLAY = 216;
		/** 引导页轮播图轮播显示 */
		public static final int MSG_SPLASH_REPEAT = 217;
		/**信息服务注册*/
		public static final int CALL_MESSAGE_SERVICE_REGISTER = 218;
	    public static final int CALL_MESSAGE_SERVICE_UNREGISTER = 219;
	    
	    public static final int MSG_PACKAGE_CHANGED = 220;
	    public static final int MSG_NOTICE_UPDATE = 221;
	    public static final int MSG_NO_DOWNLOAD_ADDRESS = 222;
	    public static final int MSG_DOWNLOAD_ADD = 223;
	    public static final int MSG_DOWNLOAD_START = 224;
	    public static final int MSG_DOWNLOAD_GOING = 225;
	    public static final int MSG_DOWNLOAD_COMPLETE = 226;
	    public static final int MSG_DOWNLOAD_PAUSED = 227;
	    public static final int MSG_DOWNLOAD_FAILURE = 228;
	    public static final int MSG_DOWNLOAD_DELETED = 229;
	    public static final int MSG_SILENT_INSTALL = 230;
	    public static final int MSG_INSTALL_FAILED = 231;
	    public static final int MSG_SMART_UPGRADE = 232;
	    
	    public static final int MSG_DECOMPRESS_GOING = 233;
        public static final int MSG_DECOMPRESS_COMPLETE = 234;
        public static final int MSG_DECOMPRESS_FAILED = 235;
        
        public static final int VALUE_PACKAGE_REMOVED = 236;
        public static final int VALUE_PACKAGE_INSTALLED = 237;
        
        public static final int MSG_UPDATE_VIEW = 238;
        public static final int MSG_NOT_FOUND = 239;
	}

	/** 300开头 */
	public static final class App {

		/** 搜索结果 */
		public static final String FRAGMENT_TAG_SEARCH_RESULT = "fg_tag_search_result";
		/** 搜索热点文字 */
		public static final String FRAGMENT_TAG_SEARCH_HOT_WORD = "fg_tag_search_HOT_WORD";
		// public static final String FRAGMENT_TAG_SEARCH_ASSACTION =
		// "fg_tag_search_assaction";
		/** 垃圾扫描 */
		public static final String FRAGMENT_TAG_SCAN = "fg_tag_scan";
		/** 垃圾扫描结果 */
		public static final String FRAGMENT_TAG_SCAN_RESULT = "fg_tag_scan_result";

		public static final String POST = "POST";
		public static final String GET = "GET";
		public static final String CONTENT_TYPE = "Content-Type";
		public static final String USER_AGENT = "User-Agent";
		public static final String CHARSET = "Charset";
		public static final String CACHE_CONTROL = "Cache-Control";
		public static final String CLIENT_ID = "clientid";
		public static final String COOKIE = "Cookie";

	}

	/** 400开头 */
	public static final class Status {
		/** 显示 **/
		public static final int VIEW_SHOW = 401;
		/** 显示断网 **/
		public static final int VIEW_WIFIFAILUER = 402;
		/** 显示加载数据失败 **/
		public static final int VIEW_LOADFAILURE = 403;
		/** 正在加载中 **/
		public static final int VIEW_LOADING = 404;
		/** 没有数据 **/
		public static final int VIEW_NODATA = 405;

		// ---------Http Status-----------------
		public static final String STATUS_CODE_OK = "200";
		public static final String STATUS_CODE_REREGISTER = "308";
		public static final String STATUS_CODE_BAD_REQUEST = "400";

		public static final int STATUS_SUCCESS = 406;
		public static final int STATUS_FAILED = 407;
		public static final int STATUS_ILLEGAL_DATA = 408;

		public static final int STATUS_SCAN = 409;// 扫描
		public static final int STATUS_SCAN_ING = 410;// 扫描中

		public static final int STATUS_CLEANUP = 411;// 清理
		public static final int STATUS_CLEANUP_ING = 412;// 清理中
		public static final int STATUS_CLEANUP_OK = 413;// 清理完成

		// ---------App Status-------------
		/** 默认状态 */
		public static final int STATUS_INVALID = -1;
		// normal
		/** 下载 */
		public static final int STATUS_DOWNLOAD = 414;
		// download
		/** 正在下载 */
		public static final int STATUS_GOING = 415;
		/** 暂停 */
		public static final int STATUS_PAUSED = 416;
		/** 下载失败 */
		public static final int STATUS_PAUSED_BY_APP = 417;
		/** 下载完成 */
		public static final int STATUS_COMPLETE = 418;
		/** 正在安装 */
		public static final int STATUS_INSTALLING = 419;
		// installed
		/** 打开应用 */
		public static final int STATUS_PLAY = 420;
		/** 更新 */
		public static final int STATUS_UPGRADE = 421;
		/** 智能更新 */
		public static final int STATUS_SMART_UPGRADE = 422;
		
		public final static int PAUSED_WAITING_TO_RETRY = 423;
        public final static int PAUSED_WAITING_FOR_NETWORK = 424;
        public final static int PAUSED_QUEUED_FOR_WIFI = 425;
        public final static int PAUSED_UNKNOWN = 426;
        public final static int PAUSED_PAUSED_BY_APP = 427;
        
        public final static int ERROR_INSUFFICIENT_SPACE = 428;
        
	}

	/** 500开头 */
	public static final class Path {

		/** 下载文件存储地址 */
//		public static final String DOWNLOADPATH = BASEPATH + File.separator
//				+ "downloadapp/";

//		public static File downloadDir = new File(DOWNLOADPATH);
//		public static final String APP_WORLD = ConfigFactory.getConfig().getCacheRootDir();
		public static final String TRACE_FILES = "trace_files";
		public static final String DOWNLOAD = "downloadapp";
		
	}

	/** 600开头 */
	public static final class Type {

		public static final int TYPE_CACHE = 600;// 缓存
		public static final int TYPE_NOT_INSTALL = 601;// 未安装应用
		public static final int TYPE_UNINSTALL = 602;// 卸载残留
		public static final int TYPE_NOT_USELES = 603;// 无用安装包
		public static final int TYPE_AD = 604;// 广告垃圾

	}

	/** 700开头 */
	public static final class Statistics {
		/** 推荐 */
		public static final int EVENT_VIEW_RECOMD = 700;
		/** 必备 */
		public static final int EVENT_VIEW_ESSIEN = 701;
		/** 行业 */
		public static final int EVENT_VIEW_INDUSTRY = 702;
		/** 应用管理 */
		public static final int EVENT_VIEW_APPMANAGE = 703;

	}

	public static final class Server {

		// public static final

	}

	
	public static enum EmptySate {
        NetError(1), NoData(2);

        private int val;

        private EmptySate(int value) {
            val = value;
        }

        public int getValue() {
            return this.val;
        }
    }
}
