package demo.hpg.org.pauldemo.system.getphoto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import demo.hpg.org.pauldemo.R;

/**
 * 获取相册图片
 * Author huarizhong
 * Date 2015/3/11
 * Time 10:09
 */
public class GetPhotoActivity extends Activity {
    private ImageView getphoto;
    private AlertDialog dialog;
    private static final int IMAGE_FROM_CAMERA = 1;
    private static final int IMAGE_FROM_PHOTOS = 2;
    private static final int CROP_IMAGE=5;//启动裁剪编辑
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getphoto);
        getphoto = (ImageView) findViewById(R.id.getphoto);
        getphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(GetPhotoActivity.this).setTitle("")
                        .setItems(new String[]{"从相册获取", "拍照"}, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                switch (which) {
                                    case 1:
                                        String status = Environment.getExternalStorageState();
                                        if (status.equals(Environment.MEDIA_MOUNTED)) {
                                            getFromCamera();// 从相机获取
                                        } else {
                                            Toast.makeText(GetPhotoActivity.this, "没有发现存储卡,打开相机失败。", Toast.LENGTH_LONG).show();
                                            // 没有SD卡;
                                        }
                                        break;
                                    case 0:
                                        getFromPhotos();// 从相相册获取
                                        break;
                                }
                                dialog.dismiss();
                            }
                        }).create().show();
            }
        });
    }
    private void getFromCamera() {
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // File out = new
            // File(Environment.getExternalStorageDirectory().toString() +
            // AsynImageLoader.CAMERA_TEMP);
            // Uri uri = Uri.fromFile(out);
            Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp4.jpg"));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, IMAGE_FROM_CAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void getFromPhotos() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, IMAGE_FROM_PHOTOS);
    }
    // 拍照需要
    public Bitmap photo;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {

            case IMAGE_FROM_CAMERA:
//			String pathString = Environment.getExternalStorageDirectory() + "/temp.jpg";
//			setPicBitmap(pathString);
//			doImageUpdate();
                Uri tempPicUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "temp4.jpg"));
                cropImage(tempPicUri);
                break;
            case IMAGE_FROM_PHOTOS:
                if (data == null) {
                    return;
                }
                cropImage(data.getData());
                break;
            case CROP_IMAGE:
                try {

                    //if return the bitmap
                    photo = data.getParcelableExtra("data");

                    //if return the uri
                    if (photo == null) {
                        Uri uri = data.getData();
                        photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    }

                    //still null ......
                    if (photo == null) {
                        return;
                    }
                    getphoto.setBackgroundResource(0);
                    getphoto.setImageBitmap(photo);
                    // image.setScaleType(ScaleType.CENTER_INSIDE);
                    getphoto.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * 保存bitmap到文件
     * @param bitmap
     */
    public static void saveBitmapToFile(Bitmap bitmap) {
        File file = new File(Environment.getExternalStorageDirectory() + "/temp5.jpg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void cropImage(Uri tempPicUri) {
        Intent intent = new Intent();
        intent.setAction("com.android.camera.action.CROP");
        intent.setDataAndType(tempPicUri, "image/*");
        File file = new File(Environment.getExternalStorageDirectory()+"/temp4.jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/temp4.jpg");
        int wi = bitmap.getWidth();
        int he = bitmap.getHeight();
        intent.putExtra("crop", "true");
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300*wi/he);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_IMAGE);
    }
}
