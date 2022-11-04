package com.pm.cameracore;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.util.Size;


public interface DelegateCallback {
    void onChangeViewSize(Size size);
    void onTransformView(Matrix matrix);
    SurfaceTexture getSurfaceTexture();
    void onCaptureResult(Bitmap bitmap);
    void onRecordResult(Bitmap coverBitmap,String videoAbsolutePath);
}
