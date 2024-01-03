package com.collegemangement.fci.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.collegemangement.fci.ClassesModel.GetComments;
import com.collegemangement.fci.ClassesModel.Postsmodel;
import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getMaterial;
import com.collegemangement.fci.ClassesModel.getReplayes;
import com.collegemangement.fci.Classmodeloflist.modeloflist;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.MainPosts.HomeView;
import com.collegemangement.fci.MainPosts.MainPresenter;
import com.collegemangement.fci.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterGetmaterial extends RecyclerView.Adapter<RecyclerViewHomeAdapterGetmaterial.RecyclerViewHolder>  {

    private List<getMaterial.Material> categories;
    private Context context;
    private static ClickListener clickListener;


    public RecyclerViewHomeAdapterGetmaterial(List<getMaterial.Material> categories , Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterGetmaterial.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.desofgetmaterial,
                viewGroup, false);


        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterGetmaterial.RecyclerViewHolder viewHolder, int i) {


     //   String name = categories.get(i).getName();

        String level = categories.get(i).getLevel();
        String description = categories.get(i).getDescription();
        String path  = categories.get(i).getFilePath();
        String creator = categories.get(i).getMaterialuploader().getName();
        String type = categories.get(i).getMaterialType();

        if (type.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
        {
            viewHolder.image.setImageResource(R.drawable.word);

        }else if (type.equals("application/pdf"))
        {
            viewHolder.image.setImageResource(R.drawable.pdf);

        }else if (type.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation"))
        {
            viewHolder.image.setImageResource(R.drawable.powerpoint);

        }else if (type.equals("text/plain"))
        {
            viewHolder.image.setImageResource(R.drawable.paper);


        }else if (type.equals("application/zip")) {
            viewHolder.image.setImageResource(R.drawable.zip);

        }



        viewHolder.level.setText("Level "+level);
        viewHolder.des.setText(description);
        viewHolder.creator.setText("Upload by "+creator);


    }


    @Override
    public int getItemCount() {
        return categories.size();
    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.image)
        ImageView image;

        @BindView(R.id.des)
        TextView des;

        @BindView(R.id.level)
        TextView level;


        @BindView(R.id.creator)
        TextView creator;




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
        RecyclerViewHomeAdapterGetmaterial.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
