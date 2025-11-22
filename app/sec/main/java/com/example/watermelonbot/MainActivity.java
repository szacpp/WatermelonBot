package com.example.watermelonbot;

import android.app.Activity;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int REQUEST_CAPTURE = 1000;
    private MediaProjectionManager projectionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        projectionManager = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);

        Button b = findViewById(R.id.btn_request_capture);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = projectionManager.createScreenCaptureIntent();
                startActivityForResult(intent, REQUEST_CAPTURE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAPTURE) {
            if (resultCode == RESULT_OK && data != null) {
                // Store intent in a static holder for simplicity (not ideal for production)
                ScreenCaptureActivity.screenIntent = data;
                ScreenCaptureActivity.screenResult = resultCode;
                Toast.makeText(this, "Screen capture permission granted. Start the game and Accessibility service.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Screen capture permission denied.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
