package com.vcvb.chenyu.shop.mycenter.userinfo;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.base.BaseRecyclerViewActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.user.userlogo.UserPhotoItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.adapter.itemdecoration.DefaultItemDecoration;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.material.Material;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class UserLogoActivity extends BaseRecyclerViewActivity {

    private List<Material> photos = new ArrayList<>();

    String path = ConstantManager.ImgPath.PATH;
    File mCameraFile = new File(path, "IMAGE_FILE_NAME.jpg");//照相机的File对象
    File mCropFile = new File(path, "IMAGE_CROP_NAME.jpg");//裁剪后输出图片

    private String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_photo);
        context = this;
        changeStatusBarTextColor(true);

        //创建文件路径
        File file = new File(path);
        file.mkdirs();

        //获取传古来的文件名
        Intent intent = getIntent();
        uri = intent.getStringExtra("card_uri");
        if(uri != null){
            mCropFile = new File(path, uri);
        }
        setNavBack();
        initView();
        initListener();
        getPhotoAlbum();
    }

    @Override
    public void setNavBack() {
        ImageView nav_back = findViewById(R.id.imageView23);
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        TextView titleView = findViewById(R.id.textView123);
        titleView.setText(R.string.all_photo);
        titleView.setTextColor(Color.parseColor("#000000"));
        titleView.setTextSize(18);
        titleView.setSingleLine();

        TextView add = findViewById(R.id.textView122);
        add.setAlpha(0);
    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = findViewById(R.id.user_logo);
        DefaultItemDecoration defaultItemDecoration = new DefaultItemDecoration(context,
                ToolUtils.dip2px(context, 2));
        mRecyclerView.addItemDecoration(defaultItemDecoration);
        mLayoutManager = new GridLayoutManager(context, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        super.initListener();
        CYCItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new CYCItemClickSupport
                .OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, View itemView, int position) {
                goToCropImage(position);
            }
        });
    }

    public void getPhotoAlbum() {
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);
        Material bean = new Material();
        bean.setIsType(2);
        photos.add(bean);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                //获取图片的名称
                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media
                        .DISPLAY_NAME));
                String uri = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                bean = new Material();
                bean.setName(name);
                bean.setFilePath(uri);
                bean.setIsType(1);
                photos.add(bean);
            }
        }
        mAdapter.addAll(getItems(photos));
    }

    protected List<Item> getItems(List<Material> list) {
        List<Item> cells = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getIsType()) {
                case 1:
                    cells.add(new UserPhotoItem(list.get(i), context));
                    break;
                case 2:
                    cells.add(new UserPhotoItem(list.get(i), context));
                    break;
            }
        }
        return cells;
    }

    //打开照相机
    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void goToCamera() {
        mCameraFile = new File(path, "IMAGE_FILE_NAME.jpg");//照相机的File对象
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
            Uri uriForFile = FileProvider.getUriForFile(this, "com.vcvb.chenyu.shop" +
                    ".provider", mCameraFile);
            intentFromCapture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intentFromCapture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
        } else {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraFile));
        }
        startActivityForResult(intentFromCapture, ConstantManager.PhotoAlbum.REQUEST_IMAGE_CAPTURE);
    }

    public void goToCropImage(int pos) {
        if (pos == 0) {
            //打开照相机
            UserLogoActivityPermissionsDispatcher.goToCameraWithCheck(this);
        } else {
            //直接裁剪
            String path = photos.get(pos).getFilePath();
            mCameraFile = new File(path);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri inputUri = FileProvider.getUriForFile(this, "com.vcvb.chenyu.shop" +
                        ".provider", mCameraFile);//通过FileProvider创建一个content类型的Uri
                startPhotoZoom(inputUri);//设置输入类型
            } else {
                Uri inputUri = Uri.fromFile(mCameraFile);
                startPhotoZoom(inputUri);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ConstantManager.PhotoAlbum.REQUEST_IMAGE_CAPTURE:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri inputUri = FileProvider.getUriForFile(this, "com.vcvb.chenyu.shop" +
                                ".provider", mCameraFile);//通过FileProvider创建一个content类型的Uri
                        startPhotoZoom(inputUri);//设置输入类型
                    } else {
                        Uri inputUri = Uri.fromFile(mCameraFile);
                        startPhotoZoom(inputUri);
                    }
                    break;
                case ConstantManager.PhotoAlbum.CROP_PHOTO:
                    Uri inputUri = FileProvider.getUriForFile(this, "com.vcvb.chenyu.shop" +
                            ".provider", mCropFile);//通过FileProvider创建一个content类型的Uri
                    Intent intent = new Intent();
                    intent.putExtra("uri", inputUri);
                    setResult(RESULT_OK, intent);
                    UserLogoActivity.this.finish();
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param inputUri
     */
    public void startPhotoZoom(Uri inputUri) {
        if (inputUri == null) {
            Log.e("error", "The uri is not exist.");
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        //sdk>=24
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri outPutUri = Uri.fromFile(mCropFile);
            intent.setDataAndType(inputUri, "image/*");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
        } else {
            Uri outPutUri = Uri.fromFile(mCropFile);
            if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                String url = ToolUtils.getPath(this, inputUri);//这个方法是处理4.4以上图片返回的Uri对象不同的处理方法
                intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
            } else {
                intent.setDataAndType(inputUri, "image/*");
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
        }


        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
//        intent.putExtra("outputX", 250);
//        intent.putExtra("outputY", 250);
        intent.putExtra("return-data", false);
        //去除默认的人脸识别，否则和剪裁匡重叠
        intent.putExtra("noFaceDetection", false);
        intent.putExtra("outputFormat", "JPEG");
        startActivityForResult(intent, ConstantManager.PhotoAlbum.CROP_PHOTO);
    }

}
