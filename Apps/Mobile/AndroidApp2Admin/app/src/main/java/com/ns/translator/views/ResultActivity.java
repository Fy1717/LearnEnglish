package com.ns.translator.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageProxy;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.mlkit.vision.common.InputImage;
import com.ns.translator.OCR.OCR;
import com.ns.translator.R;
import com.ns.translator.models.CameraXImages;
import com.ns.translator.models.CameraXImagesOperations;
import com.ns.translator.models.GalleryImages;
import com.ns.translator.models.GalleryImagesOperations;
import java.io.IOException;

public class ResultActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;

    String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView5);

        Intent infoIntent = getIntent();
        info = infoIntent.getStringExtra("info");

        setImageView();
        runOCR();
    }

    public void setImageView() {
//        if (info.matches("gallery")) {
//            GalleryImages galleryImages = GalleryImages.getInstance();
//            Bitmap galleryBitmap = galleryImages.getImageBitmap();
//
//            GalleryImagesOperations galleryImagesOperations = new GalleryImagesOperations();
//            galleryBitmap = galleryImagesOperations.scaleImage(galleryBitmap);
//            imageView.setImageBitmap(galleryBitmap);
//        } else if (info.matches("camerax")) {
        CameraXImages cameraXImages = CameraXImages.getInstance();
        Bitmap cameraBitmap = cameraXImages.getBitmapImage();

        CameraXImagesOperations cameraXImagesOperations = new CameraXImagesOperations();
        cameraBitmap = cameraXImagesOperations.scaleBitmap(cameraBitmap);
        imageView.setImageBitmap(cameraBitmap);
        //}
    }

    public void runOCR() {
//        if (info.matches("gallery")) {
//            GalleryImages galleryImages = GalleryImages.getInstance();
//
//            Bitmap bm = galleryImages.getImageBitmap();
//            InputImage inputImage = InputImage.fromBitmap(bm, 0);
//
//            OCR ocr = new ViewModelProvider(ResultActivity.this).get(OCR.class);
//            ocr.setInputImage(inputImage);
//            ocr.getResult().observe(ResultActivity.this, new Observer<String>() {
//                @Override
//                public void onChanged(String s) {
//                    s = s.replace("\n"," ");
//                    textView.setText(s);
//                    galleryImages.setTextEnglish(s);
//                }
//            });
//        } else if (info.matches("camerax")) {
        CameraXImages cameraXImages = CameraXImages.getInstance();

        Bitmap bm = cameraXImages.getBitmapImage();
        InputImage inputImage = InputImage.fromBitmap(bm, 0);

        OCR ocr = new ViewModelProvider(ResultActivity.this).get(OCR.class);
        ocr.setInputImage(inputImage);
        ocr.getResult().observe(ResultActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                s = s.replace("\n"," ");
                textView.setText(s);
                cameraXImages.setTextEnglish(s);
            }
        });
        //}

//        Intent intent = new Intent(ResultActivity.this, WordsActivity.class);
//        startActivity(intent);
    }

    public void intentWords(View view) {
        Intent intent = new Intent(ResultActivity.this, WordsActivity.class);
        //intent.putExtra("info",info);
        startActivity(intent);
    }

//    public void intentText() {
//        Intent intent = new Intent(ResultActivity.this, TextActivity.class);
//        intent.putExtra("info", info);
//        startActivity(intent);
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ResultActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}