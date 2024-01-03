package com.collegemangement.fci.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.collegemangement.fci.ChatClass;
import com.collegemangement.fci.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterChat extends RecyclerView.Adapter<RecyclerViewHomeAdapterChat.RecyclerViewHolder> {

    private List<ChatClass> categories;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewHomeAdapterChat(List<ChatClass> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterChat.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat,
                viewGroup, false);

        return new RecyclerViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterChat.RecyclerViewHolder viewHolder, int i) {






    }


    @Override
    public int getItemCount() {
        return categories.size();
    }



    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        @BindView(R.id.sender)
        TextView sender;





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
        RecyclerViewHomeAdapterChat.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
