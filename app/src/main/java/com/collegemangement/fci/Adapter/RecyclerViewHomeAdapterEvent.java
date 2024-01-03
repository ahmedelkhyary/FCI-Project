package com.collegemangement.fci.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.collegemangement.fci.ClassesModel.getEvents;
import com.collegemangement.fci.R;
import com.collegemangement.fci.fullimage;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterEvent extends RecyclerView.Adapter<RecyclerViewHomeAdapterEvent.RecyclerViewHolder> {

    private List<getEvents.Event> categories;
    private Context context;
    private static ClickListener clickListener;
    String image ;
    public RecyclerViewHomeAdapterEvent(List<getEvents.Event> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterEvent.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.eventdes,
                viewGroup, false);

        return new RecyclerViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterEvent.RecyclerViewHolder viewHolder, int i) {

        String title = categories.get(i).getTitle();
        String time = categories.get(i).getUploadDate();
        String descraption = categories.get(i).getDescription();
         image = categories.get(i).getImage();

        viewHolder.title.setText(title);
        viewHolder.time.setText(time);
        viewHolder.descraption.setText(descraption);

        try {
            Picasso.get().load(image).into(viewHolder.imageView);

        } catch (Exception e) {

        }

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, fullimage.class);
                intent.putExtra("image" , categories.get(i).getImage());
                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return categories.size();
    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.time)
        TextView time;

        @BindView(R.id.des)
        TextView descraption;

        @BindView(R.id.image)
        ImageView imageView;


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
        RecyclerViewHomeAdapterEvent.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
