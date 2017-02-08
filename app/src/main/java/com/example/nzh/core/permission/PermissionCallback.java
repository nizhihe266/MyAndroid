package com.example.nzh.core.permission;

/**
 * 权限动态授权回调
 *
 * Created by NIZHIHE on 2017/2/8.
 */
public interface PermissionCallback {
    void permissionGranted(String[] permissions);
}
