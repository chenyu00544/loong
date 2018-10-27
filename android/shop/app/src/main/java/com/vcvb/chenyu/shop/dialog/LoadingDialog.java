package com.vcvb.chenyu.shop.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.vcvb.chenyu.shop.R;
import com.wang.avi.AVLoadingIndicatorView;

public class LoadingDialog extends AlertDialog {
    private AVLoadingIndicatorView avi;

    public LoadingDialog(Context context, int themeResId) {
        super(context,themeResId);
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_loading);
        avi = this.findViewById(R.id.avi);
    }

    @Override
    public void show() {
        super.show();
        avi.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dismiss() {
        super.dismiss();
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
