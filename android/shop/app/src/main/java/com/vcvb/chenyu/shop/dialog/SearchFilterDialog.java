package com.vcvb.chenyu.shop.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.vcvb.chenyu.shop.R;

public class SearchFilterDialog extends DialogFragment {

    Window window;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_filter_dialog, container);
        window = getDialog().getWindow();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        WindowManager windowManager = window.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        window.setGravity(Gravity.RIGHT);
        window.setWindowAnimations(R.style.RightShow);
        window.setLayout((int) (display.getWidth() * 0.8), display.getHeight());
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
