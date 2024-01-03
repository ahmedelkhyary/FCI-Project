package com.collegemangement.fci.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.collegemangement.fci.ClassesModel.getquiz;
import com.collegemangement.fci.Classmodeloflist.getquizoffline;
import com.collegemangement.fci.Classmodeloflist.result;
import com.collegemangement.fci.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterQuizOnlie extends RecyclerView.Adapter<RecyclerViewHomeAdapterQuizOnlie.RecyclerViewHolder> {

    private List<getquizoffline> categories;
    private Context context;
    private static ClickListener clickListener;
     result resultofcount ;
     int z = 0 ;

    public RecyclerViewHomeAdapterQuizOnlie(List<getquizoffline> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterQuizOnlie.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.desofquiz,
                viewGroup, false);

        resultofcount = new result(0);
        return new RecyclerViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterQuizOnlie.RecyclerViewHolder viewHolder, int i) {


        String question = categories.get(i).getQuestion();

        String answer1 = categories.get(i).getAnswer1();
        String answer2 = categories.get(i).getAnswer2();
        String answer3 = categories.get(i).getAnswer3();
        String answer4 = categories.get(i).getAnswer4();

        int result = Integer.parseInt(categories.get(i).getResult());









        viewHolder.question.setText(String.valueOf(++i)+"- " +question);

        viewHolder.answer1.setText(answer1);
        viewHolder.answer2.setText(answer2);
        viewHolder.answer3.setText(answer3);
        viewHolder.answer4.setText(answer4);

        int checkedRadioButtonId = viewHolder.radioGroup.getCheckedRadioButtonId();

        viewHolder.result.setText(String.valueOf(result));



        viewHolder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                index-- ;
                Log.e("Index" , String.valueOf(index));

                if (result == index)
                {

                    com.collegemangement.fci.Classmodeloflist.result.setCount(++z);


                }


            }
        });







    }


    @Override
    public int getItemCount() {
        return categories.size();
    }



    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        @BindView(R.id.question)
        TextView question;

        @BindView(R.id.answer1)
        RadioButton answer1;

        @BindView(R.id.answer2)
        RadioButton answer2;

        @BindView(R.id.answer3)
        RadioButton answer3;

        @BindView(R.id.answer4)
        RadioButton answer4;

        @BindView(R.id.result)
        TextView result;



        @BindView(R.id.radio)
        RadioGroup radioGroup ;







        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        // clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewHomeAdapterQuizOnlie.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
