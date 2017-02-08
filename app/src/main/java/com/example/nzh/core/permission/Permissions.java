package com.example.nzh.core.permission;

import android.Manifest;


/**
 * Created by NIZHIHE on 2017/1/23.
 */

public final class Permissions {

    /**
     * 允许一个应用程序读取用户日历数据
     */
    public final static String PERMISSION_READ_CALENDAR = Manifest.permission.READ_CALENDAR;

    /**
     * 允许应用程序只写用户日历数据
     */
    public final static String PERMISSION_WRITE_CALENDAR = Manifest.permission.WRITE_CALENDAR;

    /**
     * 访问摄像头设备必须具备的权限
     */
    public final static String PERMISSION_CAMERA = Manifest.permission.CAMERA;

    /**
     * 允许一个应用程序读取用户联系人列表
     */
    public final static String PERMISSION_READ_CONTACTS = Manifest.permission.READ_CONTACTS;

    /**
     * 允许应用程序只写用户联系人数据
     */
    public final static String PERMISSION_WRITE_CONTACTS = Manifest.permission.WRITE_CONTACTS;

    /**
     * 允许访问账号服务的账号列表
     */
    public final static String PERMISSION_GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;

    /**
     * 允许一个应用程序通过访问GPS等方式获取较精确的本地位置
     * 在注册监听LocationManager.NETWORK_PROVIDER位置变化时，需要申请
     */
    public final static String PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;

    /**
     * 允许一个应用程序通过访问CellID和WiFi热点等方式获取粗略的本地位置
     * 在注册监听LocationManager.NETWORK_PROVIDER位置变化时，需要申请
     */
    public final static String PERMISSION_ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    /**
     * 许一个应用程序录音
     */
    public final static String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;

    /**
     * 允许读取手机状态
     */
    public final static String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;

    /**
     * 允许应用程序不经过用户拨号界面而直接拨号
     */
    public final static String PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE;

    /**
     *
     */
    public final static String PERMISSION_READ_CALL_LOG = Manifest.permission.READ_CALL_LOG;

    /**
     *
     */
    public final static String PERMISSION_WRITE_CALL_LOG = Manifest.permission.WRITE_CALL_LOG;

    /**
     *
     */
    public final static String PERMISSION_ADD_VOICEMAIL = Manifest.permission.ADD_VOICEMAIL;

    /**
     *
     */
    public final static String PERMISSION_USE_SIP = Manifest.permission.USE_SIP;

    /**
     *
     */
    public final static String PERMISSION_PROCESS_OUTGOING_CALLS = Manifest.permission.PROCESS_OUTGOING_CALLS;

    /**
     *
     */
    public final static String PERMISSION_BODY_SENSORS = Manifest.permission.BODY_SENSORS;

    /**
     *
     */
    public final static String PERMISSION_SEND_SMS = Manifest.permission.SEND_SMS;

    /**
     *
     */
    public final static String PERMISSION_RECEIVE_SMS = Manifest.permission.RECEIVE_SMS;

    /**
     *
     */
    public final static String PERMISSION_READ_SMS = Manifest.permission.READ_SMS;

    /**
     *
     */
    public final static String PERMISSION_RECEIVE_WAP_PUSH = Manifest.permission.RECEIVE_WAP_PUSH;

    /**
     *
     */
    public final static String PERMISSION_RECEIVE_MMS = Manifest.permission.RECEIVE_MMS;

    /**
     *
     */
    public final static String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;

    /**
     *
     */
    public final static String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

}
