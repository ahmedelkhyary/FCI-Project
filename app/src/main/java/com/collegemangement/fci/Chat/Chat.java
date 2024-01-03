package com.collegemangement.fci.Chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterChat;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterGetMessage;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterMainBylevel;
import com.collegemangement.fci.ApiChat.ChatApplication;
import com.collegemangement.fci.ChatClass;
import com.collegemangement.fci.ClassesModel.getMessages;
import com.collegemangement.fci.ClassesModel.studentOrDocInfo;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Chat extends AppCompatActivity implements HomeView {

    CircularImageView image ;
    ImageView backl ;
    TextView name ;
    EditText writemessage ;
    ImageView send;
    ChatPresenter chatPresenter ;
    Helper helper ;
    String cursor;
    String id ;
    RecyclerView recyclerView ;
    List <ChatClass> mMessages  ;
    RecyclerViewHomeAdapterGetMessage mAdapter;
    TextView typing ;
    final Handler handler = new Handler();


    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("https://fciapi.herokuapp.com");
        } catch (URISyntaxException e) {
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        

        image = findViewById(R.id.imageView);
        backl = findViewById(R.id.back);
        name = findViewById(R.id.name);
        writemessage = findViewById(R.id.writemessage);
        send = findViewById(R.id.send);
        recyclerView = findViewById(R.id.recyclerView);
        typing = findViewById(R.id.typing);

        mMessages = new ArrayList<>();


        mAdapter = new RecyclerViewHomeAdapterGetMessage(mMessages , this);

        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();


        Intent intent = getIntent();
         id = intent.getStringExtra("idResever");

        chatPresenter = new ChatPresenter(this);
        chatPresenter.GetStudentInfo(id);
        chatPresenter.GetMessages(id , cursor);
        chatPresenter.unreadMessages(cursor , id);


        mSocket.on("newMessage", newMessage);
        mSocket.on("userTyping", userTyping);

        mSocket.connect();



        backl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        writemessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {



                JSONObject data = new JSONObject();
                try {
                    data.put("sender", cursor);
                    data.put("reciver", id);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mSocket.emit("typing", data);

            }
        });




        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = writemessage.getText().toString().trim();
                String name = "text" ;



                Map<String, RequestBody> map = new HashMap<>();


                RequestBody idsender = RequestBody.create(MediaType.parse("text/plain"),
                        cursor);
                RequestBody idrecevier = RequestBody.create(MediaType.parse("text/plain"),
                        id);
                RequestBody messagesend = RequestBody.create(MediaType.parse("text/plain"),
                        message);
                RequestBody nametype = RequestBody.create(MediaType.parse("text/plain"),
                        name);

                if (message.isEmpty())
                {
                    return;
                }else
                {
                    String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                    String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                    chatPresenter.Sendmessage( map , idrecevier , idsender , messagesend , nametype);

                    addMessage(message, cursor , currentDate+" "+currentTime);

                    writemessage.setText("");

                }





            }
        });
    }


    private void addMessage( String content , String sender , String time) {
        mMessages.add(new ChatClass(content , sender , time));
       mAdapter.notifyItemInserted(mMessages.size() - 1);
       recyclerView.setAdapter(mAdapter);

        scrollToBottom();
    }

    private void scrollToBottom() {
        recyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
    }


    private Emitter.Listener newMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    JSONObject data = (JSONObject) args[0];
                    String sender = null;
                    String recevierid = null;
                    String timestemp = null;
                    String message = null;
                    try {
                        message = data.getString("content");
                        recevierid = data.getString("reciver");
                        timestemp = data.getString("timestemp");
                        sender = data.getString("sender");
                    } catch (JSONException e) {
                    }

                    // add the message to view
                    if (recevierid.equals(cursor))
                    {
                        addMessage( message , sender, timestemp);

                    }
                }

            });


        }
    };

    private Emitter.Listener userTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    JSONObject data = (JSONObject) args[0];
                    String recevierid = null;

                    try {

                        recevierid = data.getString("reciver");
                    } catch (JSONException e) {
                    }


                    if (recevierid.equals(cursor))
                    {
                        typing.setText("typing Now");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                typing.setText("");
                            }
                        }, 1500);


                    }
                }

            });


        }
    };




    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void info(studentOrDocInfo.User info) {

        name.setText(info.getName());

        try {
            Picasso.get().load(info.getProfileImagePath()).placeholder(R.drawable.demp).into(image);

        }catch (Exception e){}


    }

    @Override
    public void messages(List<getMessages.Message> messages) {

        for (int i = 0; i <messages.size() ; i++) {

            mMessages.add(new ChatClass(messages.get(i).getContent() ,
                    messages.get(i).getSender() , messages.get(i).getTimestemp()));

        }

        RecyclerViewHomeAdapterGetMessage  mAdapter = new RecyclerViewHomeAdapterGetMessage(mMessages, this);
        recyclerView.setAdapter(mAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.scrollToPosition(mAdapter.getItemCount() - 1);



    }

    @Override
    public void addmessage(String message) {

    }

    @Override
    public void readedMessges(String readedMessges) {

    }
}
