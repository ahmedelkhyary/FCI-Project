package com.collegemangement.fci.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.collegemangement.fci.ChatClass;
import com.collegemangement.fci.ClassesModel.getMessages;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class RecyclerViewHomeAdapterGetMessage extends RecyclerView.Adapter<RecyclerViewHomeAdapterGetMessage.RecyclerViewHolder> {

    private List<ChatClass> categories;
    private Context context;
    private static ClickListener clickListener;
    Helper helper ;
    String cursor ;

    public RecyclerViewHomeAdapterGetMessage(List<ChatClass> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterGetMessage.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat,
                viewGroup, false);

        helper = new Helper(context);
        cursor = helper.getAlldata();

        return new RecyclerViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterGetMessage.RecyclerViewHolder viewHolder, int i) {


        String content = categories.get(i).getContent();
        String time = categories.get(i).getTime();
        String sender = categories.get(i).getSender();



        if (sender.equals(cursor))

        {
            viewHolder.linear.setGravity( Gravity.RIGHT);

            viewHolder.sender.setText(content);
            viewHolder.time.setText(time);

        }else
        {
            viewHolder.linear.setGravity( Gravity.LEFT);

            viewHolder.sender.setText(content);
            viewHolder.time.setText(time);


        }









    }


    @Override
    public int getItemCount() {
        return categories.size();
    }



    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        @BindView(R.id.sender)
        TextView sender;

        @BindView(R.id.time)
        TextView time;


        @BindView(R.id.linear)
        LinearLayout linear;





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
        RecyclerViewHomeAdapterGetMessage.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
