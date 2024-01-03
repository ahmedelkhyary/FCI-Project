package com.collegemangement.fci.UploadMaterial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.R;
import com.collegemangement.fci.SignAvtivity.Signup;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class uploadmaterial extends AppCompatActivity implements HomeView {

    UploadPresenter uploadPresenter ;
    Helper helper;
    String cursor;
    ImageView imageView ;
    EditText des ;
    TextView level ;
    Button save ;
    AlertDialog.Builder builderSingle;
    ProgressBar progressBar ;
    String mediaPath ="";
    TextView urlofmedia ;
    ImageView delete ;
    String postype ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadmaterial);

        getSupportActionBar().setTitle("Upload material");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        uploadPresenter = new UploadPresenter(this);
        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();
        imageView = findViewById(R.id.image);
        des = findViewById(R.id.des);
        level = findViewById(R.id.level);
        save = findViewById(R.id.save);
        progressBar = findViewById(R.id.progressBar);
        urlofmedia = findViewById(R.id.urlofmedia);
        delete = findViewById(R.id.deleteicon);
        save.setVisibility(View.GONE);



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPath = "";
                urlofmedia.setText("");
                delete.setVisibility(View.GONE);
                urlofmedia.setVisibility(View.GONE);
                save.setVisibility(View.GONE);

            }
        });



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                        return;

                    }

                } else {

                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("application/msword,application/pdf");
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(intent, 500);

                }
            }
        });



        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builderSingle = new AlertDialog.Builder(uploadmaterial.this);
                builderSingle.setIcon(R.drawable.logoapp);
                builderSingle.setTitle("Select One Level :-");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(uploadmaterial.this, android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("1");
                arrayAdapter.add("2");
                arrayAdapter.add("3is");
                arrayAdapter.add("3cs");
                arrayAdapter.add("4is");
                arrayAdapter.add("4cs");



                builderSingle.setNegativeButton("cancel", (dialog, which) -> dialog.dismiss());

                builderSingle.setAdapter(arrayAdapter, (dialog, which) -> {
                    String x = arrayAdapter.getItem(which);
                    level.setText(x);
                    AlertDialog.Builder builder = new AlertDialog.Builder(uploadmaterial.this);
                    builder.setMessage(x);
                    builder.setTitle(x);
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });


                });

                builderSingle.show();
            }


        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String content = des.getText().toString();
                String levellocal = level.getText().toString();
                if (levellocal.isEmpty())
                {
                    level.setError("مطلوب");
                }else
                {



                Map<String, RequestBody> map = uploadFile();
                RequestBody id = RequestBody.create(MediaType.parse("text/plain"),
                        cursor);
                RequestBody des = RequestBody.create(MediaType.parse("text/plain"),
                        content);


                    RequestBody type = RequestBody.create(MediaType.parse("text/plain"),
                            postype);


                RequestBody level = RequestBody.create(MediaType.parse("text/plain"),
                        levellocal);
                uploadPresenter.UploadPresenter( map , id , des , type , level);

            }  }
        });



    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 500: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("application/msword,application/pdf");
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(intent, 500);


                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(), "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }


                return;
            }


        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == 500 && resultCode == RESULT_OK && null != data)
            {
                Uri fileuri = data.getData();

                String x ;
                String extension = MimeTypeMap.getFileExtensionFromUrl(String.valueOf(fileuri));
                if (extension != null) {
                    x = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
                    postype = x ;
                }

                mediaPath = getFileNameByUri(this, fileuri);
                urlofmedia.setVisibility(View.VISIBLE);
                urlofmedia.setText(mediaPath);
                delete.setVisibility(View.VISIBLE);
                save.setVisibility(View.VISIBLE);



            }


            else {
                Toast.makeText(this, "You haven't picked a File", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void result(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        if (message.equals("material uploadded successfully"))
        {
            mediaPath = "";
            urlofmedia.setText("");
            delete.setVisibility(View.GONE);
            urlofmedia.setVisibility(View.GONE);
            des.setText("");
            save.setVisibility(View.GONE);


        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private Map<String, RequestBody> uploadFile() {


        if (mediaPath.isEmpty()) {
            Map<String, RequestBody> mapp = new HashMap<>();
            return mapp;

        } else {
            // Map is used to multipart the file using okhttp3.RequestBody
            Map<String, RequestBody> map = new HashMap<>();
            File file = new File(mediaPath);

            // Parsing any Media type file
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);

            return map;
        }

    }

    private String getFileNameByUri(Context context, Uri uri)
    {
        String filepath = ""; //default fileName
        //Uri filePathUri = uri;
        File file;
        if (uri.getScheme().toString().compareTo("content") == 0)
        {
            Cursor cursor = context.getContentResolver().query(uri, new String[] { android.provider.MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.ORIENTATION }, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            cursor.moveToFirst();

            String mImagePath = cursor.getString(column_index);
            cursor.close();
            filepath = mImagePath;

        }
        else if (uri.getScheme().compareTo("file") == 0)
        {
            try
            {
                file = new File(new URI(uri.toString()));
                if (file.exists())
                    filepath = file.getAbsolutePath();

            }
            catch (URISyntaxException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            filepath = uri.getPath();
        }
        return filepath;
    }

}
