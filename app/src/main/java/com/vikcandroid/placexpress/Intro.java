package com.vikcandroid.placexpress;

import android.content.Intent;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro2;

public class Intro extends AppIntro2 {

    @Override
    public void init(Bundle bundle) {
        addSlide(SampleSlide.newInstance(R.layout.intro));
        addSlide(SampleSlide.newInstance(R.layout.intro2));
        addSlide(SampleSlide.newInstance(R.layout.intro3));
        addSlide(SampleSlide.newInstance(R.layout.intro3_2));
        addSlide(SampleSlide.newInstance(R.layout.intro4));
    }

    private void loadMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Called when done is pressed
     */
    @Override
    public void onDonePressed() {
        loadMainActivity();
        finish(); // will kill the activity so that it wont be recreated when back is pressed inside main activity
    }
}
