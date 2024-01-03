package com.collegemangement.fci.FirstGrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterMain;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterMainBylevel;
import com.collegemangement.fci.ClassesModel.GetComments;
import com.collegemangement.fci.ClassesModel.Postsmodel;
import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getReplayes;

import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.MainPosts.HomeView;
import com.collegemangement.fci.MainPosts.MainPresenter;
import com.collegemangement.fci.R;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Firstgrdae extends AppCompatActivity implements  HomeViewFristGrade {

    RecyclerView recyclerView;
    Boolean flag;
    Helper helper;
    String cursor;
    SwipeRefreshLayout swipeRefreshLayout;
    EditText writepost;
    Button publish;
    Button uploadmedia;
    LottieAnimationView progressBar2;
    String mediaPath = "";
    String type;
    String[] mediaColumns = {MediaStore.Video.Media._ID};
    TextView urlofmedia;
    ImageView deleteicon;
    LinearLayout upload ;
    Button uploadImageVideo ;
    Button uploadFile ;
    LinearLayout writepostLinear ;
    Button publichAdd ;
    EditText writepostAdd ;
    String posttypeGlobal =" ";
    FisrtGradePresenter fisrtGradePresenter;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstgrdae);

        getSupportActionBar().setTitle("الفرقه الاوله");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fisrtGradePresenter = new FisrtGradePresenter(this);


        progressBar2 = findViewById(R.id.progressBar2);
        recyclerView = findViewById(R.id.recyvlerview);
        swipeRefreshLayout = findViewById(R.id.swipe);
        writepost = findViewById(R.id.writepost);
        publish = findViewById(R.id.publish);
        uploadmedia = findViewById(R.id.uploadmedia);
        urlofmedia = findViewById(R.id.urlofmedia);
        deleteicon = findViewById(R.id.deleteicon);
        upload =findViewById(R.id.upload);
        uploadImageVideo =findViewById(R.id.uploadImageVideo);
        uploadFile =findViewById(R.id.uploadFile);
        writepostLinear =findViewById(R.id.writepostlinear);
        publichAdd =findViewById(R.id.publishAdd);
        writepostAdd =findViewById(R.id.writepostAdd);


        helper = new Helper(getApplicationContext());


        cursor = helper.getAlldata();


        writepost.setInputType(InputType.TYPE_NULL);
        writepost.setOnTouchListener((view, motionEvent) -> {
//            writepost.setInputType(InputType.TYPE_CLASS_TEXT |
//                    InputType.TYPE_TEXT_FLAG_MULTI_LINE |
//                    InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);



            writepostLinear.setVisibility(View.VISIBLE);

            return false;
        });

        publichAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writepostLinear.setVisibility(View.GONE);
                writepost.setText(writepostAdd.getText().toString());
            }
        });


        fisrtGradePresenter.GetPosts(cursor ,"1");


        publish.setOnClickListener(view -> {
            String content = writepost.getText().toString();

            Map<String, RequestBody> map = uploadFile();
            RequestBody id = RequestBody.create(MediaType.parse("text/plain"),
                    cursor);
            RequestBody contentapp = RequestBody.create(MediaType.parse("text/plain"),
                    content);
            RequestBody posttype = RequestBody.create(MediaType.parse("text/plain"),
                    posttypeGlobal);

            RequestBody level = RequestBody.create(MediaType.parse("text/plain"),
                    "1");
            fisrtGradePresenter.Createpost(map, contentapp, id   , level , posttype);

        });


        deleteicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPath = "";
                urlofmedia.setText("");
                deleteicon.setVisibility(View.GONE);
                urlofmedia.setVisibility(View.GONE);
                posttypeGlobal = "" ;

            }
        });


        writepostLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writepostLinear.setVisibility(View.GONE);
            }
        });


        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> fisrtGradePresenter.GetPosts(cursor , "1"));


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload.setVisibility(View.GONE);
            }
        });


        uploadmedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                upload.setVisibility(View.VISIBLE);

            }
        });


        uploadImageVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                upload.setVisibility(View.GONE);

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                        return;

                    }

                } else {
                    Intent pictureActionIntent = new Intent(
                            Intent.ACTION_PICK);
                    pictureActionIntent.setType("video/*, image/*");

                    startActivityForResult(pictureActionIntent, 0);
                }
            }

        });

        uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                upload.setVisibility(View.GONE);

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

                } }
        });

        flag = true;









    }

    @Override
    public void showLoading() {
        progressBar2.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        progressBar2.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, "عفوا يوجد مشكله في الاتصال", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void posts(List<PostsmodelBylevel.Post> posts) {
        RecyclerViewHomeAdapterMainBylevel homeAdapter = new RecyclerViewHomeAdapterMainBylevel(posts, this);
        recyclerView.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();
    }




    @Override
    public void service(String result) {
        if (result.equals("true")) {
            Toast.makeText(this, "تم اضافه المنشور بنجاح", Toast.LENGTH_SHORT).show();
            writepost.setText("");
            writepostAdd.setText("");
            mediaPath = "";
            urlofmedia.setText("");
            deleteicon.setVisibility(View.GONE);
            urlofmedia.setVisibility(View.GONE);
            posttypeGlobal = "" ;




        } else
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    Intent pictureActionIntent = new Intent(
                            Intent.ACTION_PICK);
                    pictureActionIntent.setType("video/*, image/*");

                    startActivityForResult(pictureActionIntent, 0);


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


                    posttypeGlobal = x ;

                }

                mediaPath = getFileNameByUri(this, fileuri);


                urlofmedia.setText(fileuri.toString());
                urlofmedia.setVisibility(View.VISIBLE);
                deleteicon.setVisibility(View.VISIBLE);
            }
            // When an Image is picked
            else if (requestCode == 0 && resultCode == RESULT_OK && null != data) {


                Uri selectedImage = data.getData();


                ContentResolver cr = this.getContentResolver();
                String mime = cr.getType(selectedImage);
                posttypeGlobal = cr.getType(selectedImage);

                Log.e("posttypeGlobal", posttypeGlobal.toString());


                if (mime.equals("image/png") || mime.equals("image/jpg") || mime.equals("image/gif") || mime.equals("image/jpeg") || mime.equals("image/tif")) {


                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                    // Set the Image in ImageView for Previewing the Media
                    // imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    urlofmedia.setText(selectedImage.toString());
                    cursor.close();

                    type = "image";

                    urlofmedia.setVisibility(View.VISIBLE);

                    deleteicon.setVisibility(View.VISIBLE);


                } // When an Video is picked
                else {

                    // Get the Video from data
                    Uri selectedVideo = data.getData();


                    ContentResolver c = this.getContentResolver();
                    String m = cr.getType(selectedImage);
                    posttypeGlobal = c.getType(selectedImage);

                    Log.e("posttypeGlobal", posttypeGlobal.toString());


                    String[] filePathColumn = {MediaStore.Video.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedVideo, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                    // Set the Video Thumb in ImageView Previewing the Media
                    // imageView.setImageBitmap(getThumbnailPathForLocalFile(this, selectedVideo));
                    urlofmedia.setText(selectedVideo.toString());
                    cursor.close();

                    type = "video";

                    deleteicon.setVisibility(View.VISIBLE);
                    urlofmedia.setVisibility(View.VISIBLE);


                }

            } else {
                Toast.makeText(this, "You haven't picked Image/Video/File", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }


    public Bitmap getThumbnailPathForLocalFile(Activity context, Uri fileUri) {
        long fileId = getFileId(context, fileUri);
        return MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(),
                fileId, MediaStore.Video.Thumbnails.MICRO_KIND, null);
    }

    // Getting Selected File ID
    public long getFileId(Activity context, Uri fileUri) {
        Cursor cursor = context.managedQuery(fileUri, mediaColumns, null, null, null);
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            return cursor.getInt(columnIndex);
        }
        return 0;
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
        String filepath = "";//default fileName
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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else
        {
            filepath = uri.getPath();
        }
        return filepath;
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
}
