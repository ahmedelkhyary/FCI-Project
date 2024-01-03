package com.collegemangement.fci.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.collegemangement.fci.Classmodeloflist.getquizoffline;
import com.collegemangement.fci.Classmodeloflist.result;
import com.collegemangement.fci.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterUpdate extends RecyclerView.Adapter<RecyclerViewHomeAdapterUpdate.RecyclerViewHolder> {

    private List<getquizoffline> categories;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewHomeAdapterUpdate(List<getquizoffline> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterUpdate.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.desofquizupdate,
                viewGroup, false);

        return new RecyclerViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterUpdate.RecyclerViewHolder viewHolder, int i) {


        String question = categories.get(i).getQuestion();

        String answer1 = categories.get(i).getAnswer1();
        String answer2 = categories.get(i).getAnswer2();
        String answer3 = categories.get(i).getAnswer3();
        String answer4 = categories.get(i).getAnswer4();
        String result = categories.get(i).getResult();










        viewHolder.question.setText(String.valueOf(++i)+"- " +question);

        viewHolder.answer1.setText(answer1);
        viewHolder.answer2.setText(answer2);
        viewHolder.answer3.setText(answer3);
        viewHolder.answer4.setText(answer4);
        viewHolder.result.setText("result = "+result);











    }


    @Override
    public int getItemCount() {
        return categories.size();
    }



    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        @BindView(R.id.question)
        TextView question;

        @BindView(R.id.answer1)
        TextView answer1;

        @BindView(R.id.answer2)
        TextView answer2;

        @BindView(R.id.answer3)
        TextView answer3;

        @BindView(R.id.answer4)
        TextView answer4;

        @BindView(R.id.result)
        TextView result;







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
        RecyclerViewHomeAdapterUpdate.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
