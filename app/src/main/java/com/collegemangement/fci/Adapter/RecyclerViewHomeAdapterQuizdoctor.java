package com.collegemangement.fci.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.collegemangement.fci.ClassesModel.doctorUploadedQuizes;
import com.collegemangement.fci.ClassesModel.getquiz;
import com.collegemangement.fci.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterQuizdoctor extends RecyclerView.Adapter<RecyclerViewHomeAdapterQuizdoctor.RecyclerViewHolder> {

    private List<doctorUploadedQuizes.Quize> categories;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewHomeAdapterQuizdoctor(List<doctorUploadedQuizes.Quize> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterQuizdoctor.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.getquizdoctor,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterQuizdoctor.RecyclerViewHolder viewHolder, int i) {



        String title = categories.get(i).getTitle();
        String level  = categories.get(i).getLevel();
        String question  = categories.get(i).getQuestionNum();

        String idquiiz = categories.get(i).getQuizUploader().getName();
        String photo = categories.get(i).getQuizUploader().getProfileImagePath();



        viewHolder.QuizCreator.setText(idquiiz);
        viewHolder.title.setText(title);
        viewHolder.grade.setText(level);
        viewHolder.question.setText(question);


        try {
            Picasso.get().load(photo).placeholder(R.drawable.demp).into(viewHolder.photo);

        }catch (Exception e){

        }






    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.quizCreator)
        TextView QuizCreator;


        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.grade)
        TextView grade;

        @BindView(R.id.question)
        TextView question;

        @BindView(R.id.photo)
        CircularImageView photo;



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
        RecyclerViewHomeAdapterQuizdoctor.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
