package com.collegemangement.fci.MainPosts;

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
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.collegemangement.fci.About;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterMainBylevel;
import com.collegemangement.fci.ClassesModel.GetComments;
import com.collegemangement.fci.ClassesModel.Postsmodel;
import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getReplayes;
import com.collegemangement.fci.CreatedoctorEmail.CreatedoctorEmail;
import com.collegemangement.fci.Createquiz.Createquiz;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.FirstGrade.Firstgrdae;
import com.collegemangement.fci.FourthGrade.fourthgrade;
import com.collegemangement.fci.LoginApiActivity.Login;
import com.collegemangement.fci.Payment.Payment;
import com.collegemangement.fci.ProfileInfromation.profile;
import com.collegemangement.fci.R;
import com.collegemangement.fci.SecendGrade.secendgrdae;
import com.collegemangement.fci.Student.Student;
import com.collegemangement.fci.ThirdGrade.thirdgrade;
import com.collegemangement.fci.Doctors.doctors;
import com.collegemangement.fci.GetMaterial.getMateria;
import com.collegemangement.fci.Events.events;
import com.collegemangement.fci.fourgradeis.fourgradeis;
import com.collegemangement.fci.getQuizes.Onliequiz;
import com.collegemangement.fci.message.message;
import com.collegemangement.fci.thirdgradeis.thirdgradeis;
import com.github.siyamed.shapeimageview.CircularImageView;

import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.Menu;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomeView {

    RecyclerView recyclerView;
    ConstraintLayout constraintLayout;
    Boolean flag;
    SharedPreferences sp;
    SharedPreferences.Editor speditor;
    MainPresenter mainPresenter;
    TextView demo;
    Helper helper;
    String cursor;
    SwipeRefreshLayout swipeRefreshLayout;
    EditText writepost;
    Button publish;
    Button uploadmedia;
    String idofstudent;
    LottieAnimationView progressBar2;
    String mediaPath = "";
    String type;
    String[] mediaColumns = {MediaStore.Video.Media._ID};
    TextView urlofmedia;
    ImageView deleteicon;

    LinearLayout upload;
    Button uploadImageVideo;
    Button uploadFile;

    LinearLayout writepostLinear;
    Button publichAdd;
    EditText writepostAdd;
    String levelGlobal = "";
    String posttypeGlobal = "null";
    TextView num;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        Button quiz = findViewById(R.id.quiz);
        progressBar2 = findViewById(R.id.progressBar2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        demo = findViewById(R.id.demo);
        recyclerView = findViewById(R.id.recyvlerview);
        swipeRefreshLayout = findViewById(R.id.swipe);
        writepost = findViewById(R.id.writepost);
        publish = findViewById(R.id.publish);
        uploadmedia = findViewById(R.id.uploadmedia);
        urlofmedia = findViewById(R.id.urlofmedia);
        deleteicon = findViewById(R.id.deleteicon);
        upload = findViewById(R.id.upload);
        uploadImageVideo = findViewById(R.id.uploadImageVideo);
        uploadFile = findViewById(R.id.uploadFile);
        writepostLinear = findViewById(R.id.writepostlinear);
        publichAdd = findViewById(R.id.publishAdd);
        writepostAdd = findViewById(R.id.writepostAdd);


        helper = new Helper(getApplicationContext());


        quiz.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Createquiz.class)));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("FCI");
        cursor = helper.getAlldata();
        demo.setText(cursor);


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


        mainPresenter = new MainPresenter(this);
        mainPresenter.getstudentinfo(cursor);
        mainPresenter.unreadMessages(cursor);


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
                    levelGlobal);
            mainPresenter.Createpost(map, contentapp, id, level, posttype);

        });


        deleteicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPath = "";
                urlofmedia.setText("");
                deleteicon.setVisibility(View.GONE);
                urlofmedia.setVisibility(View.GONE);
                posttypeGlobal = "";

            }
        });


        writepostLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writepostLinear.setVisibility(View.GONE);
            }
        });


        swipeRefreshLayout.setRefreshing(true);


        swipeRefreshLayout.setOnRefreshListener(() -> {
            mainPresenter.unreadMessages(cursor);
            mainPresenter.GetAllStudentsLevel(cursor, levelGlobal);

        });


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

                }
            }
        });


        flag = true;

        // LOGOUT

        final Button l = findViewById(R.id.doctor);
        l.setOnClickListener(v -> {

            startActivity(new Intent(getApplicationContext(), CreatedoctorEmail.class));

        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


        navigationView.setCheckedItem(R.id.message);
        navigationView.getMenu().getItem(1).setActionView(R.layout.messagenum);
        View x = navigationView.getMenu().getItem(1).getActionView();
        num = x.findViewById(R.id.num);

        List<String> list = new ArrayList<>();
        list.add("");
        list.add(" علـــــــوم ");
        list.add(" نـــظــــــم ");
        Spinner spinner = (Spinner) navigationView.getMenu().findItem(R.id.navigation_drawer_item3).getActionView();
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.preference_category, list));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(Main2ActivityDoctor.this,language[position],Toast.LENGTH_SHORT).show();
                if (position == 1)
                {
                    startActivity(new Intent(getApplicationContext(), thirdgrade.class));

                }else if (position == 2)
                {
                    startActivity(new Intent(getApplicationContext(), thirdgradeis.class));

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner.getBackground().setColorFilter(getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP);



        Spinner spinner2 = (Spinner) navigationView.getMenu().findItem(R.id.navigation_drawer_item4).getActionView();
        spinner2.setAdapter(new ArrayAdapter<String>(this,android.R.layout.preference_category, list));
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(Main2ActivityDoctor.this,language[position],Toast.LENGTH_SHORT).show();
                if (position == 1)
                {
                    startActivity(new Intent(getApplicationContext(), fourthgrade.class));

                }else if (position == 2)
                {
                    startActivity(new Intent(getApplicationContext(), fourgradeis.class));

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner2.getBackground().setColorFilter(getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.student) {
            startActivity(new Intent(getApplicationContext(), Student.class));

        } else if (id == R.id.profile) {
            startActivity(new Intent(getApplicationContext(), profile.class));

        } else if (id == R.id.pay) {
            startActivity(new Intent(getApplicationContext(), Payment.class));

        } else if (id == R.id.firstgrade) {
            startActivity(new Intent(getApplicationContext(), Firstgrdae.class));
        } else if (id == R.id.message) {
            startActivity(new Intent(getApplicationContext(), message.class));


        } else if (id == R.id.event) {
            startActivity(new Intent(getApplicationContext(), events.class));


        } else if (id == R.id.secendgrade) {
            startActivity(new Intent(getApplicationContext(), secendgrdae.class));

        } else if (id == R.id.doctors) {
            startActivity(new Intent(getApplicationContext(), doctors.class));

        } else if (id == R.id.material) {
            startActivity(new Intent(getApplicationContext(), getMateria.class));

        } else if (id == R.id.quiz) {
            startActivity(new Intent(getApplicationContext(), Onliequiz.class));

        } else if (id == R.id.logout) {

            sp = getApplicationContext().getSharedPreferences("login", 0);
            speditor = sp.edit();
            speditor.putBoolean("logged", false);
            speditor.apply();
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));

        }
        return false;
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
    public void info(List<Postsmodel.PostCreator> info) {

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
            posttypeGlobal = "";


        } else
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void student(Studentmodel.Student student) {


        TextView nameofmenu = findViewById(R.id.nameofmenu);
        TextView email = findViewById(R.id.email);
        TextView level = findViewById(R.id.level);

        levelGlobal = student.getLevel();

        CircularImageView photoofmenu = findViewById(R.id.photoofmenu);
        String photo = student.getProfileImagePath();

        mainPresenter.GetAllStudentsLevel(cursor, levelGlobal);

        nameofmenu.setText(student.getName().toUpperCase());
        email.setText(student.getEmail());
        level.setText("Level " + student.getLevel());

        try {
            Picasso.get().load(photo).placeholder(R.drawable.demp).into(photoofmenu);

        } catch (Exception e) {

        }


    }


    @Override
    public void like(String idPost, String idStudent, ImageView view, TextView textView
            , TextView textView1, ImageView imageView, TextView textView2) {

    }

    @Override
    public void GetComments(List<GetComments.Comment> GetComments) {

    }

    @Override
    public void ResultOfComment(String result) {

    }

    @Override
    public void ResultOfLikeComment(String result, String noComment, ImageView imageView, TextView textView) {

    }

    @Override
    public void ResultOfCommentRelay(String result) {

    }

    @Override
    public void GetReplays(List<getReplayes.Replaye> GetReplays) {

    }

    @Override
    public void ResultOfLikeReply(String result, String noComment, ImageView imageView, TextView textView) {

    }

    @Override
    public void messagenum(String message) {

        num.setText(message);

    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onResume() {


        super.onResume();
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

            if (requestCode == 500 && resultCode == RESULT_OK && null != data) {
                Uri fileuri = data.getData();
                String x;


                String extension = MimeTypeMap.getFileExtensionFromUrl(String.valueOf(fileuri));
                if (extension != null) {
                    x = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);


                    posttypeGlobal = x;

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

    private String getFileNameByUri(Context context, Uri uri) {
        String filepath = "";//default fileName
        //Uri filePathUri = uri;
        File file;
        if (uri.getScheme().toString().compareTo("content") == 0) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{android.provider.MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.ORIENTATION}, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            cursor.moveToFirst();

            String mImagePath = cursor.getString(column_index);
            cursor.close();
            filepath = mImagePath;

        } else if (uri.getScheme().compareTo("file") == 0) {
            try {
                file = new File(new URI(uri.toString()));
                if (file.exists())
                    filepath = file.getAbsolutePath();

            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            filepath = uri.getPath();
        }
        return filepath;
    }


    @Override
    public void onBackPressed() {


        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:

                startActivity(new Intent(getApplicationContext(), About.class));

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
