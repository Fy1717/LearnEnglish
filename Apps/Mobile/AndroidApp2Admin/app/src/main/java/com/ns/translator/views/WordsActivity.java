package com.ns.translator.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ns.translator.R;
import com.ns.translator.RecyclerViewAdapters.RecyclerViewAdapter;
import com.ns.translator.Translate.EnglishToTurkish;
import com.ns.translator.models.CameraXImages;
import com.ns.translator.models.CameraXImagesOperations;
import com.ns.translator.models.GalleryImages;
import com.ns.translator.models.GalleryImagesOperations;
import com.ns.translator.models.Languages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordsActivity extends AppCompatActivity {
    TextView firstView;
    TextView secondView;

    RecyclerView recyclerView;

    //String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        firstView = findViewById(R.id.textView2);
        secondView = findViewById(R.id.textView3);
        recyclerView = findViewById(R.id.recyclerView);

        //Intent infoIntent = getIntent();
        //info = infoIntent.getStringExtra("info");

        Languages languages = Languages.getInstance();
        secondView.setText(languages.getSelectedSecondLanguage());
        firstView.setText(languages.getSelectedFirstLanguage());

        getWords();
    }

    public void getWords() {
//        if (info.matches("gallery")) {
//            GalleryImages galleryImages = GalleryImages.getInstance();
//            String text = galleryImages.getTextEnglish();
//
//            GalleryImagesOperations galleryImagesOperations = new ViewModelProvider(WordsActivity.this).get(GalleryImagesOperations.class);
//            galleryImagesOperations.setText(text);
//
//            galleryImagesOperations.getParsedWords().observe(WordsActivity.this, new Observer<String[]>() {
//                @Override
//                public void onChanged(String[] strings) {
//                    List<String> englishList=new ArrayList<>();
//
//                    englishList = Arrays.asList(strings);
//                    galleryImages.setListEnglish(englishList);
//                    translateWords();
//                }
//            });
//        } else if (info.matches("camerax")) {
        CameraXImages cameraXImages = CameraXImages.getInstance();
        String text = cameraXImages.getTextEnglish();

        CameraXImagesOperations cameraXImagesOperations = new ViewModelProvider(WordsActivity.this).get(CameraXImagesOperations.class);
        cameraXImagesOperations.setText(text);

        cameraXImagesOperations.getParsedWords().observe(WordsActivity.this, new Observer<String[]>() {
            @Override
            public void onChanged(String[] strings) {
                List<String> englishList=new ArrayList<>();

                englishList = Arrays.asList(strings);
                cameraXImages.setListEnglish(englishList);
                translateWords();
            }
        });
        //}
    }

    public void translateWords() {
//        if (info.matches("gallery")) {
//            GalleryImages galleryImages = GalleryImages.getInstance();
//            List<String> englishList = galleryImages.getListEnglish();
//
//            EnglishToTurkish englishToTurkish = new ViewModelProvider(WordsActivity.this).get(EnglishToTurkish.class);
//            englishToTurkish.setEnglishList(englishList);
//
//            englishToTurkish.getTurkishList().observe(WordsActivity.this, new Observer<List<String>>() {
//                @Override
//                public void onChanged(List<String> strings) {
//                    galleryImages.setListTurkish(strings);
//
//                    recyclerView.setLayoutManager(new LinearLayoutManager(WordsActivity.this));
//                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(englishList,strings);
//                    recyclerView.setAdapter(recyclerViewAdapter);
//                }
//            });
//        } else if (info.matches("camerax")) {
        CameraXImages cameraXImages = CameraXImages.getInstance();
        List<String> englishList = cameraXImages.getListEnglish();

        EnglishToTurkish englishToTurkish = new ViewModelProvider(WordsActivity.this).get(EnglishToTurkish.class);
        englishToTurkish.setEnglishList(englishList);

        englishToTurkish.getTurkishList().observe(WordsActivity.this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                cameraXImages.setListTurkish(strings);

                recyclerView.setLayoutManager(new LinearLayoutManager(WordsActivity.this));
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(englishList, strings, getApplicationContext());
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });

        //}
    }
}