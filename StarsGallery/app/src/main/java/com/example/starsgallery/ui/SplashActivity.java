package com.example.starsgallery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.starsgallery.R;

public class SplashActivity extends AppCompatActivity {

    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.logo);
        logo.setAlpha(1f); // assure que le logo est visible au départ

        // Enchaînement des animations
        logo.animate()
                .rotation(360f)
                .scaleX(0.5f).scaleY(0.5f)
                .translationYBy(200f) // translation plus douce
                .setDuration(3000)
                .withEndAction(() ->
                        logo.animate()
                                .alpha(0f)
                                .setDuration(1000)
                                .withEndAction(() -> {
                                    // Redirection après animation
                                    startActivity(new Intent(SplashActivity.this, ListActivity.class));
                                    finish();
                                })
                )
                .start();
    }
}
