package com.collegemangement.fci.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.collegemangement.fci.ClassesModel.GetComments;
import com.collegemangement.fci.ClassesModel.Postsmodel;
import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getReplayes;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.MainPosts.HomeView;
import com.collegemangement.fci.MainPosts.MainPresenter;
import com.collegemangement.fci.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterRply extends RecyclerView.Adapter<RecyclerViewHomeAdapterRply.RecyclerViewHolder> implements HomeView {
    private List<getReplayes.Replaye> categories;
    private Context context;
    private static ClickListener clickListener;
    private String cursor;
    private Helper helper;
    MainPresenter mainPresenter;
    boolean flag = true;

    public RecyclerViewHomeAdapterRply(List<getReplayes.Replaye> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterRply.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.desofreply,
                viewGroup, false);


        helper = new Helper(context);
        cursor = helper.getAlldata();

        mainPresenter = new MainPresenter(this);
        return new RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterRply.RecyclerViewHolder viewHolder, int i) {


        String content = categories.get(i).getContent();
        String profileImage = categories.get(i).getReplayUploader().getProfileImagePath();
        String name = categories.get(i).getReplayUploader().getName();


        String getLikeNum = String.valueOf(categories.get(i).getLikesNum());

        Boolean like = categories.get(i).isLiked();



        if (like) {
            viewHolder.likecomment.setImageResource(R.drawable.ic_favorite);

        } else {
            viewHolder.likecomment.setImageResource(R.drawable.ic_favorite_border);

        }


        viewHolder.comment.setText(content);
        try{
            Picasso.get().load(profileImage).placeholder(R.drawable.demp).into(viewHolder.circularImageView);

        }catch (Exception e){}
        viewHolder.nameofownercomment.setText(name);
        viewHolder.numoflike.setText("("+getLikeNum+")");


        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mainPresenter.replayLike(categories.get(i).getId(), cursor, viewHolder.likecomment , viewHolder.numoflike);

            }
        });


    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

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

    }

    @Override
    public void ResultOfCommentRelay(String result) {

    }

    @Override
    public void GetReplays(List<getReplayes.Replaye> GetReplays) {

    }

    @Override
    public void ResultOfLikeReply(String result, String noComment, ImageView imageView, TextView textView) {

        if (result.equals("like added successfully")) {
            imageView.setImageResource(R.drawable.ic_favorite);
            textView.setText(noComment);





        } else if (result.equals("like removed successfully")) {

            imageView.setImageResource(R.drawable.ic_favorite_border);
            textView.setText(noComment);





        }

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


        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            //clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewHomeAdapterRply.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }


}
