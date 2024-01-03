package com.collegemangement.fci.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.collegemangement.fci.ClassesModel.getdoctors;
import com.collegemangement.fci.ClassesModel.studentofMyLevel;
import com.collegemangement.fci.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterdocotr extends RecyclerView.Adapter<RecyclerViewHomeAdapterdocotr.RecyclerViewHolder> {

    List<getdoctors.Doctor> categories ;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewHomeAdapterdocotr(List<getdoctors.Doctor> categories , Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterdocotr.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.studentdesgin,
                viewGroup , false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterdocotr.RecyclerViewHolder viewHolder, int i) {


        String name = categories.get(i).getName();
        String grade = categories.get(i).getEmail();
        String date = categories.get(i).getCity();
        String photo = categories.get(i).getProfileImagePath();

        viewHolder.name.setText(name);
        viewHolder.grade.setText(grade);
        viewHolder.depart.setText(date);
        Picasso.get().load(photo).placeholder(R.drawable.demp).into(viewHolder.photo);
//



    }


    @Override
    public int getItemCount() {
        return categories.size() ;

    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.cost)
        TextView name;
        @BindView(R.id.grade)
        TextView grade;

        @BindView(R.id.depart)
        TextView depart;

        @BindView(R.id.photo)
        CircularImageView photo ;

        @BindView(R.id.linkphpoto)
                TextView link;

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
        RecyclerViewHomeAdapterdocotr.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
