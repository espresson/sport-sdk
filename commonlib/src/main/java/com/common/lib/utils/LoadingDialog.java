package com.common.lib.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.common.lib.R;

import java.util.HashMap;


public class LoadingDialog extends Dialog {

    private static final int ID = 0XCCAA0001;

    public static final int REFRESH = ID + 1;
    private Context mContext;
    private int dlgType;
    private HashMap<Integer, Integer> layoutMap;
    private String msgStr;
    private TextView msg_tx;

    private LoadingDialog(Context context, int dlgType) {
        super(context, R.style.dlg_refresh_style);
        setCanceledOnTouchOutside(false);
        this.dlgType = dlgType;
        this.mContext = context;
        initLayoutMap();
    }

    private LoadingDialog(Context context, int dlgType, String msgStr) {
        super(context, R.style.dlg_refresh_style);
        setCanceledOnTouchOutside(false);
        this.dlgType = dlgType;
        this.mContext = context;
        this.msgStr = msgStr;
        initLayoutMap();
    }

    public static LoadingDialog createDialog(Context context) {
        return new LoadingDialog(context, REFRESH);
    }

    public static LoadingDialog createDialog(Context context, int dlgType) {
        return new LoadingDialog(context, dlgType);
    }

    public static LoadingDialog createDialog(Context context, String msgStr) {
        return new LoadingDialog(context, REFRESH, msgStr);
    }

    /**
     * (non-Javadoc).
     *
     * @param savedInstanceState
     * @see android.app.AlertDialog#onCreate(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutMap.get(this.dlgType));
        initViews();
    }

    /**
     * Description: .
     */
    @SuppressLint("UseSparseArrays")
    private void initLayoutMap() {
        layoutMap = new HashMap<Integer, Integer>();
        layoutMap.put(REFRESH, R.layout.view_dlg_refresh);// 刷新
    }

    /**
     * Description: .
     */
    private void initViews() {
        msg_tx = (TextView) findViewById(R.id.dlg_tv_message);
        switch (dlgType) {
            case REFRESH:
                if (msgStr == null) {
                    msgStr = "正在加载...";
                }
                msg_tx.setText(msgStr);
                break;
            default:

                break;
        }
    }

    public void refresh(String msg) {
        msg_tx.setText(msg);
    }

    public void setMessage(int messageId) {
        this.msgStr = mContext.getResources().getString(messageId);
    }

    public void setMessage(String message) {
        this.msgStr = message;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == KeyEvent.KEYCODE_BACK) {
            if (isShowing()) {
                this.dismiss();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void show() {
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        this.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                fullScreenImmersive(getWindow().getDecorView());
            }
        });
        super.show();
        fullScreenImmersive(getWindow().getDecorView());
    }

    private final void fullScreenImmersive(View view) {
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        if (Build.VERSION.SDK_INT >= 19) {
            uiOptions |= 0x00001000;
        } else {
            uiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
    }


}
