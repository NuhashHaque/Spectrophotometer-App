package com.example.myapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
    public static final int GALLARY_REQUEST_CODE=123;


     Uri imageUri1;
    ImageView image;
    Button Button1;
    Button Button2;
    TextView textView;
    private ProgressBar progressBar;
    double val=0;
    double val1=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image= findViewById(R.id.image_view);
        Button1=findViewById(R.id.button1);
        Button2=findViewById(R.id.button2);
        progressBar = findViewById(R.id.progress_bar);
        textView= findViewById(R.id.text_view);

        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLARY_REQUEST_CODE);
                textView.setVisibility(View.INVISIBLE);


            }
        });
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                textView.setVisibility(View.INVISIBLE);
                ExampleAsyncTask task = new ExampleAsyncTask(MainActivity.this);
                task.execute(10);

            }
        });
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                imageUri1=imageUri;
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap bitmap1 = BitmapFactory.decodeStream(imageStream);
                image.setImageBitmap(bitmap1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(MainActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }







    private  class ExampleAsyncTask extends AsyncTask<Integer, Integer, String> {
        private WeakReference<MainActivity> activityWeakReference;

        ExampleAsyncTask(MainActivity activity) {
            activityWeakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }

            activity.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            try {

                final InputStream imageStream = getContentResolver().openInputStream(imageUri1);
                final Bitmap bitmap1 = BitmapFactory.decodeStream(imageStream);


                int count = 0;
                int p=0;
                int q=0;
                int r=0;
                int s=0;
                int x=0;
                int y=0;
                int g=0;
                int h=0;
                int z=100;
                int u=100;
                int w=0;
                int k=0;
                double d=0;
                double a=0;
                double R=0;
                double t=0;

                for (int i = 0; i < bitmap1.getHeight(); i++)
                {
                    for (int j = 0; j < bitmap1.getWidth(); j++)
                    {
                        int c = bitmap1.getPixel(j, i);


                        count++;
                        p = Color.red(c);
                        q = Color.green(c);
                        r = Color.blue(c);


                        s= (p+q+r)/3;
                        if(s<z){
                            z=s;
                            x=i;
                            y=j;
                        }
                        if(s>u){
                            u=s;
                            g=i;
                            h=j;
                        }
                    }
                }

                a=(x+y);
                k=(h-y);
                w=k-137;
                R=0.000002*w*w*w - 0.0001*w*w+0.0023*w+ 1.3272;
                t=0.0012*w*w*w-0.0615*w*w+1.4974*w-0.238;
                System.out.println("x " +x+ " y " +y);
                System.out.println("g " +g+ " h " +h);

                System.out.println("d "  + a );
                System.out.println("pixel" );
                System.out.println("k "  + R );
                val=R;
                val1=t;

                //image.setImageBitmap(bitmap1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
//            for (int i = 0; i < integers[0]; i++) {
//                publishProgress((i * 100) / integers[0]);
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
              //value=R;
            return "Finished!";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }

            activity.progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }

            Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
            activity.progressBar.setProgress(0);
            activity.progressBar.setVisibility(View.INVISIBLE);
            activity.textView.setVisibility(View.VISIBLE);
            activity.textView.setText("Refractive Index: "+val+"\n"+"Water Salinity(%):"+val1);
        }
    }
}

