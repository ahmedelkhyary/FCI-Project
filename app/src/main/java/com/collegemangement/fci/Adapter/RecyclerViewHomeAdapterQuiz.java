package com.collegemangement.fci.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.collegemangement.fci.ClassesModel.getquiz;
import com.collegemangement.fci.Classmodeloflist.modeloflist;
import com.collegemangement.fci.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterQuiz extends RecyclerView.Adapter<RecyclerViewHomeAdapterQuiz.RecyclerViewHolder> {

    private List<getquiz.Quize> categories;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewHomeAdapterQuiz(List<getquiz.Quize> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterQuiz.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.getquizs,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterQuiz.RecyclerViewHolder viewHolder, int i) {



        String title = categories.get(i).getTitle();
        String level  = categories.get(i).getLevel();
        String idquiiz = categories.get(i).getQuizUploader().getName();
       // String photo = categories.get(i).getQuizUploader().getName();



        viewHolder.QuizCreator.setText(idquiiz);
        viewHolder.title.setText(title);
        viewHolder.grade.setText(level);






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
        RecyclerViewHomeAdapterQuiz.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
