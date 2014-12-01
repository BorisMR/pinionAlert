package org.tallerdam.smogoff;

import java.util.Timer;
import java.util.TimerTask;
 
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
 
public class SplashScreenActivity extends Activity {
 
    // Duracion de la pantalla de bienvenida
    private static final long SPLASH_SCREEN_DELAY = 3000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        // Set orientacion
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // esconder barra de titulo
        requestWindowFeature(Window.FEATURE_NO_TITLE);
 
        setContentView(R.layout.splash_screen);
 
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
 
                // iniciar actividad principal
                Intent mainIntent = new Intent().setClass(
                        SplashScreenActivity.this, MainActivity.class);
                startActivity(mainIntent);
 
                // cerrar la pantalla de bienvenida a fin de no volver a cargarla con el boton "atras"
                finish();
            }
        };
 
        // Simula tiempo de carga.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
 
}