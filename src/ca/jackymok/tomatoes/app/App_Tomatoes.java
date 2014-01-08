package ca.jackymok.tomatoes.app;

import android.app.Application;

/**
 * Application class for the demo. Used to ensure that MyVolley is initialized. {@see MyVolley}
 * @author Ognyan Bankov
 *
 */
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
