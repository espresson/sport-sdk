package com.pm.cameraui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import com.pm.cameraui.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author pm
 * @date 2019/9/20
 * @email puming@zdsoft.cn
 */
public class VideoViewController extends FrameLayout {

    private CustomerVideoView mVideoView;
    private ImageView mCoverImageView;
    private ImageButton mPlayImageButton;

    public VideoViewController(@NonNull Context context) {
        this(context, null);
    }

    public VideoViewController(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public VideoViewController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, -1);
    }

    public VideoViewController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mVideoView = new CustomerVideoView(context);
        //取控件当前的布局参数
      //  LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mVideoView.getLayoutParams();
//设置宽度值
       // params.width = dip2px(context, LayoutParams.MATCH_PARENT);
//设置高度值
      //  params.height = dip2px(context, LayoutParams.MATCH_PARENT);
        addView(mVideoView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        mCoverImageView = new ImageView(context);
        mCoverImageView.setBackgroundColor(Color.BLACK);
        addView(mCoverImageView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));


        mPlayImageButton = new ImageButton(context);
        mPlayImageButton.setBackgroundResource(android.R.color.transparent);
        mPlayImageButton.setImageResource(R.drawable.ic_play);
        LayoutParams playParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        playParams.gravity = Gravity.CENTER;
        addView(mPlayImageButton, playParams);
    }

    /**
     * dp转为px
     *
     * @param context  上下文
     * @param dipValue dp值
     * @return
     */
    private int dip2px(Context context, float dipValue) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }

    private void resetState() {
        this.setVisibility(VISIBLE);
        mVideoView.setVisibility(INVISIBLE);
        mCoverImageView.setVisibility(VISIBLE);
        mPlayImageButton.setVisibility(VISIBLE);
    }

    public void show(Bitmap coverBitmap, String videoPath) {
        resetState();
        mCoverImageView.setImageBitmap(coverBitmap);
        mVideoView.setOnCompletionListener(mp -> {
           resetState();
           mp.release();
        });
        mPlayImageButton.setOnClickListener(v -> {
            mVideoView.setVisibility(VISIBLE);
            mCoverImageView.setVisibility(INVISIBLE);
            mPlayImageButton.setVisibility(INVISIBLE);
            mVideoView.setVideoPath(videoPath);

            mVideoView.start();
        });
    }

    public void hide() {
        if(mVideoView.isPlaying()){
            mVideoView.stopPlayback();
        }
        this.setVisibility(INVISIBLE);
        mVideoView.setVisibility(INVISIBLE);
        mCoverImageView.setVisibility(INVISIBLE);
        mPlayImageButton.setVisibility(INVISIBLE);
    }
}
