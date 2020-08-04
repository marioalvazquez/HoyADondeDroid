package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToWeb(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.ita.mx/"));
        startActivity(intent);
    }

    public void makeCall(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:3325062677"));
        startActivity(intent);
    }

    public void goToMaps(View view){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:41.656313,-0.877351"));
            startActivity(intent);
        }
        catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), "No se puede lanzar Google Maps", Toast.LENGTH_SHORT);
            toast.setMargin(25,100);
            toast.show();
        }
    }

    public void sendMail(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "asunto");
        intent.putExtra(Intent.EXTRA_TEXT, "texto del correo");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"marioal.vazquez@gmail.com"});
        startActivity(intent);
    }

    public void takePhoto(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }
}
