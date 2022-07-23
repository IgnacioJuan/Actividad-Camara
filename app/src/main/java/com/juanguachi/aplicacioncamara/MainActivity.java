package com.juanguachi.aplicacioncamara;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
int REQUEST_CODE=200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verificarpermisos();
    }

    public void tomarFoto(View view){
        Intent intentCamara=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intentCamara.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intentCamara, 1);
        }
    }

    private void verificarpermisos(){
        int permisoCamara= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(permisoCamara== PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permiso de camara concedido", Toast.LENGTH_SHORT).show();

        }else{
            requestPermissions(new String[]{Manifest.permission.CAMERA},REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK){
            Bundle info=data.getExtras();
            Bitmap imagen=(Bitmap) info.get("data");

            ImageView imagev=(ImageView) findViewById(R.id.iv_Foto);
            imagev.setImageBitmap(imagen);
        }
    }
}