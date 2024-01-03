package com.collegemangement.fci.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.collegemangement.fci.ClassesModel.GetComments;
import com.collegemangement.fci.ClassesModel.Postsmodel;
import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getReplayes;
import com.collegemangement.fci.Classmodeloflist.modeloflist;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.MainPosts.HomeView;
import com.collegemangement.fci.MainPosts.MainPresenter;
import com.collegemangement.fci.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.RecyclerViewHolder> implements HomeView {

    private List<modeloflist> categories;
    private Context context;
    private static ClickListener clickListener;
    private String cursor;
    private Helper helper;
    private String idofpost;
    MainPresenter mainPresenter;
    String id ;
    String filelink ;

    public RecyclerViewHomeAdapter(List<modeloflist> categories, String id , Context context) {
        this.categories = categories;
        this.context = context;
        this.id = id ;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_postdesgin,
                viewGroup, false);


        mainPresenter = new MainPresenter(this);

        helper = new Helper(context);
        cursor = helper.getAlldata();
       // mainPresenter.getstudentinfo();



        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapter.RecyclerViewHolder viewHolder, int i) {


        String name = categories.get(i).getName();

        String grade = categories.get(i).getGrade();
        String date = categories.get(i).getDate();
        String text = categories.get(i).getText();

        List<String> fileapp = categories.get(i).getFiles();
        List <String> checklike = categories.get(i).getLikes();

        Log.e("List" , checklike.toString());


        for (int j = 0; j < checklike.size() ; j++) {

            if(checklike.get(j).equals(id))
            {


                viewHolder.likepost.setImageResource(R.drawable.ic_favorite);


            }


        }


//        if (!fileapp.isEmpty())
//        {
//
//            for (int j = 0; j < fileapp.size() ; j++) {
//
//
//                viewHolder.file.setVisibility(View.VISIBLE);
//
//                filelink = "http://fciapi.herokuapp.com/api/file/" + fileapp.get(j);
//
//
//                viewHolder.file.setText(filelink.toString());
//            }
//
//        }else {
//            viewHolder.file.setText("");
//            viewHolder.file.setVisibility(View.GONE);
//        }
//
//        viewHolder.file.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Uri uri = Uri.parse(filelink);
//                Intent intent = new Intent(Intent.ACTION_VIEW , uri);
//                context.startActivity(intent);
//
//            }
//        });




        String photo = categories.get(i).getPhoto();

        viewHolder.name.setText(name);
        viewHolder.grade.setText(grade);
        viewHolder.date.setText(date);
        viewHolder.text.setText(text);

      //  viewHolder.photo.setImageResource(photo);


//
//        String photo = categories.get(i).getPostCreator().getProfileImagePath();
//
//
            String urlen = Uri.encode(photo , "utf-8");
//
//
//
        String url = "http://fciapi.herokuapp.com/api/file/"+ urlen;
//
//
//       viewHolder.link.setText(url);
//
//
//
//
        Picasso.get().load(url).placeholder(R.drawable.demp).into(viewHolder.photo);


        viewHolder.likepost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idofpost = categories.get(i).getId();

            //    mainPresenter.makelike(id, idofpost,viewHolder.likepost);



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

    }

    @Override
    public void messagenum(String message) {

    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.like)
        ImageButton likepost;

        @BindView(R.id.cost)
        TextView name;
        @BindView(R.id.grade)
        TextView grade;

        @BindView(R.id.depart)
        TextView date;

        @BindView(R.id.text)
        TextView text;

        @BindView(R.id.photo)
        CircularImageView photo ;





        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
         clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewHomeAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
