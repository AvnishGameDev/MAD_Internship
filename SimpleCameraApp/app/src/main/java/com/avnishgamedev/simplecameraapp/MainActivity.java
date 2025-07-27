package com.avnishgamedev.simplecameraapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Button captureButton;
    private ImageView thumbView;
    private String lastImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    100);  // any request code
        }

        surfaceView = findViewById(R.id.surfaceView);
        captureButton = findViewById(R.id.captureButton);
        thumbView = findViewById(R.id.thumbView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (camera != null) {
                    camera.takePicture(null, null, pictureCallback);
                }
            }
        });

        thumbView.setOnClickListener(v -> {
            if (lastImagePath != null) {
                Intent intent = new Intent(MainActivity.this, FullscreenImageActivity.class);
                intent.putExtra(FullscreenImageActivity.EXTRA_IMAGE_PATH, lastImagePath);
                startActivity(intent);
            }
        });
    }

    private Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFile = new File(getExternalFilesDir(null), "photo.jpg");
            try (FileOutputStream fos = new FileOutputStream(pictureFile)) {
                fos.write(data);
                lastImagePath = pictureFile.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Decode JPEG data into Bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

            // Rotate the bitmap by 90 degrees (portrait)
            Matrix matrix = new Matrix();
            matrix.postRotate(90);

            Bitmap rotatedBitmap = Bitmap.createBitmap(
                    bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true
            );

            // Set to thumbnail view
            thumbView.setImageBitmap(rotatedBitmap);

            camera.startPreview();  // restart preview after picture is taken
        }
    };

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            camera = Camera.open();
            camera.setDisplayOrientation(90); // Fix orientation for portrait
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (surfaceHolder.getSurface() == null) return;

        try {
            camera.stopPreview();
        } catch (Exception ignored) {}

        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted - start camera setup
            } else {
                Toast.makeText(this, "Camera permission is required", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }
}
