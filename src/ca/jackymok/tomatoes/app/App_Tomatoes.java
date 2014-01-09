package ca.jackymok.tomatoes.app;

import android.app.Application;


public class App_Tomatoes extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        
        init();
    }

    private void init() {
        MyVolley.init(this);
    }
}
