package com.common.lib.utils;

import android.Manifest;

import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public final class RxPermissionsUtil {

    public static void requestSDCardPermissions(FragmentActivity activity) {
        requestPermissions(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO);
    }


    public static void requestPermissions(FragmentActivity activity, String... permissions) {
        new RxPermissions(activity).requestEach(permissions)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            //Log.i("xxx", permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            //Log.i("xxx", permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            //Log.i("xxx", permission.name + " is denied.");
                        }
                    }
                });
    }

}
