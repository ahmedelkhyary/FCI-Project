package com.collegemangement.fci.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.collegemangement.fci.ClassesModel.GetComments;
import com.collegemangement.fci.ClassesModel.Postsmodel;
import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getReplayes;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.MainPosts.HomeView;
import com.collegemangement.fci.MainPosts.MainPresenter;
import com.collegemangement.fci.ProfileInfromation.profile;
import com.collegemangement.fci.Studentinfo.ProfileStudent;
import com.collegemangement.fci.R;
import com.collegemangement.fci.Updata.Updatapost;
import com.collegemangement.fci.videoplyer;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterMainBylevel extends RecyclerView.Adapter<RecyclerViewHomeAdapterMainBylevel.RecyclerViewHolder> implements HomeView {

    private List<PostsmodelBylevel.Post> categories;
    private Context context;
    private static ClickListener clickListener;
    private MainPresenter mainPresenter;
    Helper helper;
    String cursor;
    EditText write;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    int index;


    public RecyclerViewHomeAdapterMainBylevel(List<PostsmodelBylevel.Post> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterMainBylevel.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_postdesgin,
                viewGroup, false);


        mainPresenter = new MainPresenter(this);
        helper = new Helper(context);
        cursor = helper.getAlldata();


        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterMainBylevel.RecyclerViewHolder viewHolder, int i) {

        // Insert data into fields



        String name = categories.get(i).getPostCreator().getName();
        String ProfileImagePath = categories.get(i).getPostCreator().getProfileImagePath();
        String grade = categories.get(i).getPostCreator().getEmail();
        String date = categories.get(i).getTimestemp();
        String text = categories.get(i).getContent();
        String nooflike = categories.get(i).getLikesNum();
        String noofcomment = categories.get(i).getCommentsNum();
        String path = categories.get(i).getFilePath();
        String type = categories.get(i).getPostType();
        Boolean like = categories.get(i).getIsLiked();



        if (like) {
            viewHolder.likepost.setImageResource(R.drawable.ic_favorite);

        } else {
            viewHolder.likepost.setImageResource(R.drawable.ic_favorite_border);

        }


        if (nooflike.equals("0") && noofcomment.equals("0")) {
            viewHolder.nooflike.setVisibility(View.GONE);
            viewHolder.noofcomment.setVisibility(View.GONE);
            viewHolder.likeiconshow.setVisibility(View.GONE);
            viewHolder.commentshow.setVisibility(View.GONE);

        } else {

            viewHolder.nooflike.setVisibility(View.VISIBLE);
            viewHolder.noofcomment.setVisibility(View.VISIBLE);
            viewHolder.likeiconshow.setVisibility(View.VISIBLE);
            viewHolder.commentshow.setVisibility(View.VISIBLE);

        }

        if ( type ==null  )
        {
            viewHolder.image.setVisibility(View.GONE);
            viewHolder.link.setVisibility(View.GONE);
        }else if (type.equals("image/png") || type.equals("image/jpg")
                || type.equals("image/jpeg") || type.equals("image/tif") || type.equals("image/gif")  )

        {
            viewHolder.linkUrl.setVisibility(View.GONE);
            viewHolder.video.setVisibility(View.GONE);
            viewHolder.link.setVisibility(View.GONE);
            viewHolder.image.setVisibility(View.VISIBLE);
            try {
                Picasso.get().load(path).into(viewHolder.image);

            }catch (Exception e){}
        }else if(type.equals("video/mp4") || type.equals("video/m4p")
                || type.equals("video/flv") || type.equals("video/mkv") || type.equals("video/3gp")  )
        {
            viewHolder.video.setVisibility(View.VISIBLE);
            viewHolder.link.setVisibility(View.GONE);
            viewHolder.image.setVisibility(View.GONE);
            viewHolder.linkUrl.setVisibility(View.GONE);

            viewHolder.video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }else
        {
            viewHolder.linkUrl.setVisibility(View.VISIBLE);
            viewHolder.linkUrl.setText(categories.get(i).getFilePath());


            if (type.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
            {
                viewHolder.link.setVisibility(View.VISIBLE);

                viewHolder.link.setImageResource(R.drawable.word);

            }else if (type.equals("application/pdf"))
            {
                viewHolder.link.setVisibility(View.VISIBLE);

                viewHolder.link.setImageResource(R.drawable.pdf);

            }else if (type.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation"))
            {
                viewHolder.link.setVisibility(View.VISIBLE);

                viewHolder.link.setImageResource(R.drawable.powerpoint);

            }else if (type.equals("text/plain"))
            {
                viewHolder.link.setVisibility(View.VISIBLE);

                viewHolder.link.setImageResource(R.drawable.paper);


            }else if (type.equals("application/zip")) {
                viewHolder.link.setVisibility(View.VISIBLE);

                viewHolder.link.setImageResource(R.drawable.zip);

            }
            viewHolder.video.setVisibility(View.GONE);
            viewHolder.image.setVisibility(View.GONE);
            viewHolder.link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(path));
                    context.startActivity(i);
                }
            });

            viewHolder.linkUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(path));
                    context.startActivity(i);
                }
            });

        }


        viewHolder.video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(view.getContext(), videoplyer.class);
                intent.putExtra("video", categories.get(i).getFilePath());

                view.getContext().startActivity(intent);


            }
        });


        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cursor.equals(categories.get(i).getPostCreator().getId()))
                {
                    context.startActivity(new Intent(context , profile.class));
                }
                else
                {
                    Intent intent = new Intent(context, ProfileStudent.class);
                    intent.putExtra("id", categories.get(i).getPostCreator().getId());
                    context.startActivity(intent);
                }
            }
        });

        viewHolder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cursor.equals(categories.get(i).getPostCreator().getId()))
                {
                    context.startActivity(new Intent(context , profile.class));
                }
                else {
                    Intent intent = new Intent(context, ProfileStudent.class);
                    intent.putExtra("id", categories.get(i).getPostCreator().getId());


                    context.startActivity(intent);
                }
            }
        });

            viewHolder.name.setText(name);
        viewHolder.grade.setText(grade);
        viewHolder.date.setText(date);
        viewHolder.text.setText(text);
        viewHolder.nooflike.setText(nooflike);
        viewHolder.noofcomment.setText(noofcomment);
        Picasso.get().load(ProfileImagePath).placeholder(R.drawable.demp).into(viewHolder.photo);


        viewHolder.linearlike.setOnClickListener(view -> {

            mainPresenter.LikePost(categories.get(i).getId(), cursor, viewHolder.likepost
                    , viewHolder.nooflike, viewHolder.noofcomment, viewHolder.likeiconshow, viewHolder.commentshow);


        });


        viewHolder.threedots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetDialog);
                View view1 = LayoutInflater.from(context).inflate(R.layout.editpost, null);

                LinearLayout edit = view1.findViewById(R.id.edit);
                LinearLayout delete = view1.findViewById(R.id.delete);
                LinearLayout share = view1.findViewById(R.id.share);
                View v = view1.findViewById(R.id.view);

                if (categories.get(i).getPostCreator().getId().equals(cursor))
                {
                    edit.setVisibility(View.VISIBLE);
                    delete.setVisibility(View.VISIBLE);
                    share.setVisibility(View.VISIBLE);

                }else
                {
                    edit.setVisibility(View.GONE);
                    delete.setVisibility(View.GONE);
                    v.setVisibility(View.GONE);
                    share.setVisibility(View.VISIBLE);
                }
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context , Updatapost.class);
                        intent.putExtra("content" , categories.get(i).getContent());
                        intent.putExtra("id" , categories.get(i).getId());
                        context.startActivity(intent);
                        bottomSheetDialog.dismiss();
                    }
                });



                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();

            }
        });

        viewHolder.linearcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog bottomSheetDialog = new Dialog(context, android.R.style.Theme_DeviceDefault_DialogWhenLarge_NoActionBar);
                View view1 = LayoutInflater.from(context).inflate(R.layout.comments, null);

                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();


                write = view1.findViewById(R.id.writecomment);
                recyclerView = view1.findViewById(R.id.recyclerView);
                progressBar = view1.findViewById(R.id.progressBar);


                mainPresenter.GetComments(categories.get(i).getId(), cursor);

               // index = i;


                view1.findViewById(R.id.addcomment).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String content = write.getText().toString();
                        mainPresenter.CreateComment(write.getText().toString() , categories.get(i).getId(), cursor);

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
    public void like(String message, String number, ImageView view, TextView textView
            , TextView noofComment, ImageView likeicon, TextView commentName) {

        if (message.equals("like added successfully")) {
            view.setImageResource(R.drawable.ic_favorite);
            textView.setText(number);


            if (textView.getText().equals("0") && noofComment.getText().equals("0")) {
                textView.setVisibility(View.GONE);
                noofComment.setVisibility(View.GONE);
                likeicon.setVisibility(View.GONE);
                commentName.setVisibility(View.GONE);


            } else {

                textView.setVisibility(View.VISIBLE);
                noofComment.setVisibility(View.VISIBLE);
                likeicon.setVisibility(View.VISIBLE);
                commentName.setVisibility(View.VISIBLE);


            }


        } else if (message.equals("like removed successfully")) {

            view.setImageResource(R.drawable.ic_favorite_border);
            textView.setText(number);


            if (textView.getText().equals("0") && noofComment.getText().equals("0")) {
                textView.setVisibility(View.GONE);
                noofComment.setVisibility(View.GONE);
                likeicon.setVisibility(View.GONE);
                commentName.setVisibility(View.GONE);


            } else {

                textView.setVisibility(View.VISIBLE);
                noofComment.setVisibility(View.VISIBLE);
                likeicon.setVisibility(View.VISIBLE);
                commentName.setVisibility(View.VISIBLE);


            }


        }

    }

    @Override
    public void GetComments(List<GetComments.Comment> GetComments) {

        RecyclerViewHomeAdapterComment homeAdapter = new RecyclerViewHomeAdapterComment(GetComments, context);
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
    public void ResultOfComment(String result) {

        if (result.equals("comment added successfully")) {
            mainPresenter.GetComments(categories.get(index).getId(), cursor);
            write.setText("");


        }

    }

    @Override
    public void ResultOfLikeComment(String result, String noComment ,  ImageView imageView, TextView textView) {

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
        ImageView likepost;

        @BindView(R.id.cost)
        TextView name;
        @BindView(R.id.grade)
        TextView grade;

        @BindView(R.id.depart)
        TextView date;

        @BindView(R.id.text)
        TextView text;

        @BindView(R.id.photo)
        CircularImageView photo;

        @BindView(R.id.nooflike)
        TextView nooflike;

        @BindView(R.id.noofcomment)
        TextView noofcomment;

        @BindView(R.id.likeiconshow)
        ImageView likeiconshow;

        @BindView(R.id.commentshow)
        TextView commentshow;

        @BindView(R.id.linearlike)
        LinearLayout linearlike;

        @BindView(R.id.linearcomment)
        LinearLayout linearcomment;

        @BindView(R.id.image)
        ImageView image;

        @BindView(R.id.link)
        ImageView link;

        @BindView(R.id.video)
        ConstraintLayout video;

        @BindView(R.id.linkUrl)
        TextView linkUrl;

        @BindView(R.id.threedots)
        ImageView threedots;




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
        RecyclerViewHomeAdapterMainBylevel.clickListener = clickListener;

    }


    public interface ClickListener {
        void onClick(View view, int position);

    }


}
