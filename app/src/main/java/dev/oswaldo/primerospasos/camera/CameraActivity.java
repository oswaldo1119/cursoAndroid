package dev.oswaldo.primerospasos.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.oswaldo.primerospasos.R;

public class CameraActivity extends AppCompatActivity {

    private static final int CAMERA_PICTURE = 2342;
    private static final int GALLERY_PIC = 78;

    @BindView(R.id.imageViewCamera)
    ImageView mImageViewCamera;

    String[] PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    int PERMISSION_ALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonCamera) public void tryGetCameraImage(){
        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }else{
            getCameraImage();
        }

    }

    @OnClick(R.id.buttonMedia) public void tryGetMediaImage(){
        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }else{
            getMediaImage();
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if(context != null && permissions!= null) {
            for (String permission : permissions){
                if(ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void getCameraImage() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_PICTURE);
    }

    private void getMediaImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_PIC);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            //User Give Permissions
        } else{
            //User dont give permissions
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CAMERA_PICTURE:
                if (resultCode == Activity.RESULT_OK){
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    mImageViewCamera.setImageBitmap(photo);
                } else{

                }
                break;
            case GALLERY_PIC:
                if(resultCode == RESULT_OK){
                    if(data != null){
                        Uri pickedImage = data.getData();
                        //Let's read picked image path using content resolver
                        String[] filePath = { MediaStore.Images.Media.DATA };
                        Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
                        cursor.moveToFirst();
                        String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
                        Uri myUri = Uri.parse(imagePath);

                        mImageViewCamera.setImageBitmap(bitmap);//rotateImageIfRequired
                        //Do something with the bitmap

                        //At the end remember to close the cursor or you will end with the RuntimeException!
                        cursor.close();
                    } else {

                    }
                } else{

                }
                break;
        }
    }

    private static Bitmap rotateImageIfRequired(Bitmap img, Uri selectedImage) throws IOException {

        ExifInterface ei = new ExifInterface(selectedImage.getPath());
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }
    }

    private static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    private Bitmap scaleBitmap(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        int maxWidth = 900;
        int maxHeight = 900;
        if (width > height) {
            // landscape
            float ratio = (float) width / maxWidth;
            width = maxWidth;
            height = (int)(height / ratio);
        } else if (height > width) {
            // portrait
            float ratio = (float) height / maxHeight;
            height = maxHeight;
            width = (int)(width / ratio);
        } else {
            // square
            height = maxHeight;
            width = maxWidth;
        }
        return  Bitmap.createScaledBitmap(bm, width, height, true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
