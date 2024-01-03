package com.collegemangement.fci.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.collegemangement.fci.ClassesModel.GetComments;
import com.collegemangement.fci.ClassesModel.Postsmodel;
import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getReplayes;
import com.collegemangement.fci.ClassesModel.getReplys;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.MainPosts.HomeView;
import com.collegemangement.fci.MainPosts.MainPresenter;
import com.collegemangement.fci.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterComment extends RecyclerView.Adapter<RecyclerViewHomeAdapterComment.RecyclerViewHolder> implements HomeView {

    private List<GetComments.Comment> categories;
    private Context context;
    private static ClickListener clickListener;
    private String cursor;
    private Helper helper;
    MainPresenter mainPresenter;
    EditText write;
    RecyclerView recyclerView;
    ArrayList<getReplys> arrayList;
    int index;
    ProgressBar progressBar ;


    public RecyclerViewHomeAdapterComment(List<GetComments.Comment> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterComment.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.desofcomment,
                viewGroup, false);

        helper = new Helper(context);
        cursor = helper.getAlldata();
        mainPresenter = new MainPresenter(this);
        arrayList = new ArrayList<>();

        return new RecyclerViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterComment.RecyclerViewHolder viewHolder, int i) {


        String name = categories.get(i).getContent();

        String nameofownercomment = categories.get(i).getCommentUploader().getName();
        String imageofownercomment = categories.get(i).getCommentUploader().getProfileImagePath();


        viewHolder.comment.setText(name);
        viewHolder.nameofownercomment.setText(nameofownercomment);
        String likenum = String.valueOf(categories.get(i).getLikesNum());


        boolean check = categories.get(i).getIsLiked();


        if (check) {

            viewHolder.likecomment.setImageResource(R.drawable.ic_favorite);


        } else {
            viewHolder.likecomment.setImageResource(R.drawable.heart);

        }

        viewHolder.numoflike.setText("("+likenum+")");


        try {
            Picasso.get().load(imageofownercomment).placeholder(R.drawable.demp).into(viewHolder.circularImageView);

        } catch (Exception e) {
        }


        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mainPresenter.LikeComment(categories.get(i).getId(), cursor, viewHolder.likecomment, viewHolder.numoflike);
            }
        });

        viewHolder.linearreply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog bottomSheetDialog = new Dialog(context, android.R.style.Theme_DeviceDefault_DialogWhenLarge_NoActionBar);
                View view1 = LayoutInflater.from(context).inflate(R.layout.replys, null);

                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();

                write = view1.findViewById(R.id.writecomment);
                recyclerView = view1.findViewById(R.id.recyclerView);
                progressBar = view1.findViewById(R.id.progressBar);


                mainPresenter.getReplayes(categories.get(i).getId());


                view1.findViewById(R.id.addcomment).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String content = write.getText().toString();

                        mainPresenter.ComentReplay(categories.get(i).getId(), cursor, content);
                        index = i;


                    }
                });

            }
        });


    }


    @Override
    public int getItemCount() {
        return categories.size();
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
    public void posts(List<PostsmodelBylevel.Post> posts) {

    }



    @Override
    public void info(List<Postsmodel.PostCreator> info) {

    }

    @Override
    public void service(String result) {

    }

    @Override
    public void student(Studentmodel.Student student) {

    }

    @Override
    public void like(String idPsot, String idStudent, ImageView view, TextView textView, TextView textView1, ImageView imageView, TextView textView2) {

    }

    @Override
    public void GetComments(List<GetComments.Comment> GetComments) {

    }

    @Override
    public void ResultOfComment(String result) {

    }

    @Override
    public void ResultOfLikeComment(String result, String noComment, ImageView imageView, TextView textView) {


        if (result.equals("like added successfully")) {
            imageView.setImageResource(R.drawable.ic_favorite);
            textView.setText(noComment);


        } else if (result.equals("like removed successfully")) {

            imageView.setImageResource(R.drawable.ic_favorite_border);
            textView.setText(noComment);


        }


    }

    @Override
    public void ResultOfCommentRelay(String result) {
        if (result.equals("replay added successfully")) {
            write.setText("");
            mainPresenter.getReplayes(categories.get(index).getId());
        }

    }

    @Override
    public void GetReplays(List<getReplayes.Replaye> GetReplays) {


        RecyclerViewHomeAdapterRply homeAdapter = new RecyclerViewHomeAdapterRply(GetReplays, context);
        // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context , LinearLayoutManager.VERTICAL,false);

        GridLayoutManager layoutManager = new GridLayoutManager(context, 1,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // recyclerView.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.VERTICAL , false));
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

    }

    @Override
    public void ResultOfLikeReply(String result, String noComment, ImageView imageView, TextView textView) {

    }

    @Override
    public void messagenum(String message) {

    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.comment)
        TextView comment;

        @BindView(R.id.imageView)
        CircularImageView circularImageView;


        @BindView(R.id.nameofownercomment)
        TextView nameofownercomment;


        @BindView(R.id.likecommentlinear)
        LinearLayout linearLayout;

        @BindView(R.id.likecomment)
        ImageView likecomment;

        @BindView(R.id.numoflike)
        TextView numoflike;

        @BindView(R.id.numofcomment)
        TextView numofcomment;

        @BindView(R.id.linearreply)
        LinearLayout linearreply;


        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {


//            clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewHomeAdapterComment.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }


}