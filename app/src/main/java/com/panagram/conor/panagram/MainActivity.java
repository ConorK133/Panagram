package com.panagram.conor.panagram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final int OPEN_REQUEST_CODE = 69;
    private String fileString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView)findViewById(R.id.test);
        Picasso.with(this).load(R.drawable.test1).into(imageView);

        Button btn = (Button) findViewById(R.id.click);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                openFile(v);
            }
        });
    }
    public void openFile(View view)
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/jpeg");
        startActivityForResult(intent, OPEN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == OPEN_REQUEST_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                if (data != null)
                {
                    Uri imageuri = data.getData();
                    try
                    {
                        InputStream inputStream = MainActivity.this.getContentResolver().openInputStream(imageuri);
                        Bitmap bm = BitmapFactory.decodeStream(inputStream);
                        ImageView imageView = (ImageView)findViewById(R.id.test);
                        imageView.setImageBitmap(bm);
                    }
                    catch(Exception e)
                    {

                    }
                }
            }
        }
    }
}
