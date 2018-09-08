package com.vcvb.chenyu.shop.mycenter.userinfo;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vcvb.chenyu.shop.BaseActivity;
import com.vcvb.chenyu.shop.R;
import com.vcvb.chenyu.shop.adapter.CYCSimpleAdapter;
import com.vcvb.chenyu.shop.adapter.base.Item;
import com.vcvb.chenyu.shop.adapter.item.user.userlogo.UserPhotoItem;
import com.vcvb.chenyu.shop.adapter.itemclick.CYCItemClickSupport;
import com.vcvb.chenyu.shop.adapter.spacesitem.DefaultItemDecoration;
import com.vcvb.chenyu.shop.constant.ConstantManager;
import com.vcvb.chenyu.shop.javaBean.material.Material;
import com.vcvb.chenyu.shop.tools.ToolUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class UserLogoActivity extends BaseActivity {

    Context context;
    private RecyclerView mRecyclerView;
    private CYCSimpleAdapter mAdapter = new CYCSimpleAdapter();
    private List<Material> photos = new ArrayList<>();
    private GridLayoutManager mLayoutManager;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_photo);
        context = this;
        changeStatusBarTextColor(true);
        setNavBack();
        initView();
        initListener();
        getPhotoAlbum();
    }

    @Override
    public void setNavBack() {
        ImageView nav_back = (ImageView) findViewById(R.id.imageView23);
        if (nav_back != null) {
            nav_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        TextView titleView = (TextView) findViewById(R.id.textView123);
        titleView.setText(R.string.all_photo);
        titleView.setTextColor(Color.parseColor("#000000"));
        titleView.setTextSize(18);
        titleView.setSingleLine();

        TextView add = (TextView) findViewById(R.id.textView122);
        add.setAlpha(0);
    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = (RecyclerView) findViewById(R.id.user_logo);
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
                //获取图片的生成日期
//            byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                //获取图片的详细信息
//            String desc = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media
// .DESCRIPTION));

                String uri = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));bean = new Material();
                bean.setName(name);
                bean.setFilePath(uri);
                bean.setIsType(1);
                photos.add(bean);

                File file = new File(uri);
                if (file.exists()) {
                } else {
//                    if (file.delete()) {
//                        //已经删除文件
//                        Toast.makeText(context, file.getPath() + "删除成功", Toast.LENGTH_SHORT).show();
//                        String params[] = new String[]{file.getPath()};
//                        getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                                MediaStore.Images.Media.DATA + " LIKE ?", params);
//                    }
                }
//                Toast.makeText(context, file.getPath() + "删除成功", Toast.LENGTH_SHORT).show();
//                String params[] = new String[]{file.getPath()};
//                getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                        MediaStore.Images.Media.DATA + " LIKE ?", params);
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

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void goToCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (photoFile != null) {
            if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                imageUri = Uri.fromFile(photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            } else {
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, photoFile.getAbsolutePath());
                imageUri = context.getContentResolver().insert(MediaStore.Images.Media
                        .EXTERNAL_CONTENT_URI, contentValues);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(context,
//                        BuildConfig.APPLICATION_ID + ".provider", photoFile));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }
            startActivityForResult(intent, ConstantManager.PhotoAlbum.REQUEST_IMAGE_CAPTURE);
        }
    }

    public void goToCropImage(int pos) {
        if (pos == 0) {
            UserLogoActivityPermissionsDispatcher.goToCameraWithCheck(this);
        } else {
            String path = photos.get(pos).getFilePath();
            imageUri = Uri.parse(path);
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(imageUri, "image/*");
            intent.putExtra("scale", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, ConstantManager.PhotoAlbum.CROP_PHOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ConstantManager.PhotoAlbum.REQUEST_IMAGE_CAPTURE:
                    // 启动裁剪程序
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, ConstantManager.PhotoAlbum.CROP_PHOTO);
                    break;
                case ConstantManager.PhotoAlbum.CROP_PHOTO:
                    Intent backIntent = new Intent();
                    backIntent.putExtra("uri", imageUri);
                    setResult(RESULT_OK, backIntent);
                    UserLogoActivity.this.finish();
                    break;
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,      /* prefix */
                ".jpg",             /* suffix */
                storageDir          /* directory */);

        return image;
    }
}
