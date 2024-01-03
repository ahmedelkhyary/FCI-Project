package com.collegemangement.fci.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterMessages;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterStudent;
import com.collegemangement.fci.Chat.Chat;
import com.collegemangement.fci.ClassesModel.messagesContacts;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.R;

import java.util.List;


public class message extends AppCompatActivity implements HomeView {

    RecyclerView recyclerView;
    LottieAnimationView progressBar;
    RecyclerViewHomeAdapterMessages homeAdapter ;
    messagePresenter messagePresenter ;
    Helper helper ;
    String cursor ;
    EditText search ;
    ImageView back ;
    @SuppressLint("ClickableViewAccessibility")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        search = findViewById(R.id.searchtousers);
        back = findViewById(R.id.back);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();

         messagePresenter = new messagePresenter(this);
         messagePresenter.messagesContacts(cursor);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    messagePresenter.SearchByKey(search.getText().toString() , cursor );


                    return true;
                }
                return false;
            }
        });



        search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (search.getRight() - search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        search.setText("");

                        return true;
                    }
                }
                return false;
            }
        });





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
    public void messagesContacts(List<messagesContacts.Contact> messagesContacts) {

        homeAdapter = new RecyclerViewHomeAdapterMessages(messagesContacts, this);
        recyclerView.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();
        homeAdapter.setOnItemClickListener((view, position) -> {

            Intent intent1 = new Intent(getApplicationContext(), Chat.class);
            intent1.putExtra("idResever" , messagesContacts.get(position).getId());
            startActivity(intent1);

        });

    }

    @Override
    public void SearchBykey(List<messagesContacts.Contact> SearchBykey) {

        RecyclerViewHomeAdapterMessages homeAdapter = new RecyclerViewHomeAdapterMessages(SearchBykey, this);
        recyclerView.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();
        homeAdapter.setOnItemClickListener((view, position) -> {

            Intent intent1 = new Intent(getApplicationContext(), Chat.class);
            intent1.putExtra("idResever" , SearchBykey.get(position).getId());
            startActivity(intent1);

        });


    }

    @Override
    public void unreadMessagesBetween2Users(String unreadMessagesBetween2Users, TextView textView) {

    }


}