package com.pm.cameracore;

public interface CameraDelegate {
    void openCamera(int width,int height);
    void closeCamera();
    void startBackgroundThread();
    void stopBackgroundThread();
}
