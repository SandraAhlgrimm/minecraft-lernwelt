package com.lernwelt;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Locale;

public class MainActivity extends Activity {

    private WebView web;
    private TextToSpeech tts;
    private boolean ttsReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Bildschirm anlassen waehrend des Spiels
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Deutsche Vorlese-Stimme vorbereiten
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int r = tts.setLanguage(Locale.GERMAN);
                    ttsReady = (r != TextToSpeech.LANG_MISSING_DATA
                            && r != TextToSpeech.LANG_NOT_SUPPORTED);
                }
            }
        });

        web = new WebView(this);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        s.setMediaPlaybackRequiresUserGesture(false);
        s.setCacheMode(WebSettings.LOAD_DEFAULT);

        web.addJavascriptInterface(new Bridge(), "AndroidBridge");

        // Vollbild / immersiv
        web.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        setContentView(web);
        web.loadUrl("file:///android_asset/index.html");
    }

    // Von JavaScript aufrufbar: window.AndroidBridge.*
    public class Bridge {
        @JavascriptInterface
        public void sprich(final String text) {
            sprich(text, null);
        }

        @JavascriptInterface
        public void sprich(final String text, final String lang) {
            if (tts != null && ttsReady && text != null) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override public void run() {
                        if (lang != null && lang.length() >= 2) {
                            try { tts.setLanguage(Locale.forLanguageTag(lang)); } catch (Exception e) {}
                        }
                        String id = "u" + System.currentTimeMillis();
                        if (Build.VERSION.SDK_INT >= 21) {
                            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, id);
                        } else {
                            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                });
            }
        }

        @JavascriptInterface
        public void stopp() {
            if (tts != null) tts.stop();
        }

        @JavascriptInterface
        public void beenden() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override public void run() { finishAndRemoveTaskCompat(); }
            });
        }
    }

    private void finishAndRemoveTaskCompat() {
        if (Build.VERSION.SDK_INT >= 21) {
            finishAndRemoveTask();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) { tts.stop(); tts.shutdown(); }
        super.onDestroy();
    }
}
