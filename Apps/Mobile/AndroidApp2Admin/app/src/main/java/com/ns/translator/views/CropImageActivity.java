package com.ns.translator.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageProxy;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.ns.translator.R;
import com.ns.translator.models.CameraXImages;
import com.ns.translator.models.CameraXImagesOperations;
import com.ns.translator.models.GalleryImages;
import com.ns.translator.models.GalleryImagesOperations;
import com.theartofdev.edmodo.cropper.CropImageView;

public class CropImageActivity extends AppCompatActivity {
    CropImageView cropImageView;

    GalleryImages galleryImages;
    CameraXImages cameraXImages;

    String info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);

        cropImageView = findViewById(R.id.cropImageView);

//        Intent infoIntent = getIntent();
//        info = infoIntent.getStringExtra("info");

        galleryImages = GalleryImages.getInstance();
        cameraXImages = CameraXImages.getInstance();

        setImageView();

    }
    public void setImageView() {
//        if (info.matches("gallery")) {
//            GalleryImages galleryImages = GalleryImages.getInstance();
//            Bitmap bitmapImage = galleryImages.getImageBitmap();
//            cropImageView.setImageBitmap(bitmapImage);
//
//        } else if (info.matches("camerax")) {
        CameraXImages cameraXImages = CameraXImages.getInstance();
        ImageProxy imageProxy = cameraXImages.getImageProxy();

        CameraXImagesOperations cameraXImagesOperations = new ViewModelProvider(CropImageActivity.this).get(CameraXImagesOperations.class);
        cameraXImagesOperations.setImageProxy(imageProxy);

        cameraXImagesOperations.getBitmapImage().observe(CropImageActivity.this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                cropImageView.setImageBitmap(bitmap);
            }
        });
        //}
    }

    public void cropImage(View view) {
//        if (info.matches("gallery")) {
//            Bitmap bm = cropImageView.getCroppedImage();
//            galleryImages.setImageBitmap(bm);
//            //intentToResult(info);
//            //intentWords(info);
//        } else if (info.matches("camerax")) {
        Bitmap bm = cropImageView.getCroppedImage();
        cameraXImages.setBitmapImage(bm);
        intentToResult();
        //intentWords(info);
        //}
    }

    public void rotateImage(View view) {
        cropImageView.rotateImage(90);
    }

    public void intentToResult() {
        Intent intent = new Intent(CropImageActivity.this, ResultActivity.class);
        //intent.putExtra("info",info);
        startActivity(intent);
        finish();
    }

//    public void intentWords() {
//        //Intent intent = new Intent(ResultActivity.this, WordsActivity.class);
//        Intent intent = new Intent(CropImageActivity.this, WordsActivity.class);
//        //intent.putExtra("info", info);
//        startActivity(intent);
//    }
}