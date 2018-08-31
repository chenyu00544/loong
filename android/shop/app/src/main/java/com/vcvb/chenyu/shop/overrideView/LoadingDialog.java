package com.vcvb.chenyu.shop.overrideView;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.vcvb.chenyu.shop.R;
import com.wang.avi.AVLoadingIndicatorView;

public class LoadingDialog extends AlertDialog {
    private static LoadingDialog loadingDialog;
    private AVLoadingIndicatorView avi;

    public static LoadingDialog getInstance(Context context) {
        if(loadingDialog == null){
            loadingDialog = new LoadingDialog(context, R.style.TransparentDialog); //设置AlertDialog背景透明
            loadingDialog.setCancelable(false);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        return loadingDialog;
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context,themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_loading);
        avi = (AVLoadingIndicatorView)this.findViewById(R.id.avi);
    }

    public LoadingDialog setType(String indicator){
        avi.setIndicator(indicator);
        return loadingDialog;
    }

    @Override
    public void show() {
        super.show();
        avi.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        avi.hide();
    }

    /**
     * 样式：
     Row 1
     BallPulseIndicator
     BallGridPulseIndicator
     BallClipRotateIndicator
     BallClipRotatePulseIndicator

     Row 2
     SquareSpinIndicator
     BallClipRotateMultipleIndicator
     BallPulseRiseIndicator
     BallRotateIndicator

     Row 3
     CubeTransitionIndicator
     BallZigZagIndicator
     BallZigZagDeflectIndicator
     BallTrianglePathIndicator

     Row 4
     BallScaleIndicator
     LineScaleIndicator
     LineScalePartyIndicator
     BallScaleMultipleIndicator

     Row 5
     BallPulseSyncIndicator
     BallBeatIndicator
     LineScalePulseOutIndicator
     LineScalePulseOutRapidIndicator

     Row 6
     BallScaleRippleIndicator
     BallScaleRippleMultipleIndicator
     BallSpinFadeLoaderIndicator
     LineSpinFadeLoaderIndicator

     Row 7
     TriangleSkewSpinIndicator
     PacmanIndicator
     BallGridBeatIndicator
     SemiCircleSpinIndicator
     */
}
