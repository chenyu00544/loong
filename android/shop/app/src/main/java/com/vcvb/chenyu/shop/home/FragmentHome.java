package com.vcvb.chenyu.shop.home;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.image.GlideImageLoader;
import com.vcvb.chenyu.shop.image.Images;
import com.youth.banner.Banner;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    View view;
    Context context;

    private Banner banner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity();
        initSearchView();
        initBanner();
        return view;
    }

    private void initSearchView() {
        RelativeLayout nav_back = view.findViewById(R.id.nav_back);
        final EditText editText = view.findViewById(R.id.search_text);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                editText.setCursorVisible(true);
                return false;
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                switch (i) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        editText.setCursorVisible(false);
                        break;
                }
                return false;
            }
        });

        final LinearLayout linearLayout = view.findViewById(R.id.search_wrap);
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                linearLayout.getWindowVisibleDisplayFrame(r);
                int screenHeight = linearLayout.getRootView()
                        .getHeight();
                int heightDifference = screenHeight - (r.bottom);
                if (heightDifference > 200) {
                    //软键盘显示
                } else {
                    //软键盘隐藏
                    editText.setCursorVisible(false);
                }
            }
        });
    }

    private void initBanner() {
        ArrayList<Uri> imageUrls = new ArrayList<>();
        for (int i = 0; i < Images.imgUrls.length; i++) {
            imageUrls.add(Uri.decode(Images.imgUrls[i]));
        }
        banner = view.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imageUrls);
        banner.start();
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        banner.stopAutoPlay();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        banner.startAutoPlay();
//    }
}
