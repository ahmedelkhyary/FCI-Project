package com.collegemangement.fci.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.collegemangement.fci.ClassesModel.doctorUploadedQuizes;
import com.collegemangement.fci.ClassesModel.getQuizResult;
import com.collegemangement.fci.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterResultExam extends RecyclerView.Adapter<RecyclerViewHomeAdapterResultExam.RecyclerViewHolder> {

    private List<getQuizResult.Result> categories;
    private Context context;
    private static ClickListener clickListener;
    int count = 1 ;

    public RecyclerViewHomeAdapterResultExam(List<getQuizResult.Result> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterResultExam.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.getresultsexam,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterResultExam.RecyclerViewHolder viewHolder, int i) {



        String name = categories.get(i).getStudent().getName();
        String result  = String.valueOf(categories.get(i).getResult());



        viewHolder.name.setText(name);
        viewHolder.mark.setText(result);
        viewHolder.number.setText(String.valueOf(count++));






    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.mark)
        TextView mark;

        @BindView(R.id.number)
        TextView number;




        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//         clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewHomeAdapterResultExam.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
