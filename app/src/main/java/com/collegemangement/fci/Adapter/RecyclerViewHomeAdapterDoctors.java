package com.collegemangement.fci.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getdoctors;
import com.collegemangement.fci.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterDoctors extends RecyclerView.Adapter<RecyclerViewHomeAdapterDoctors.RecyclerViewHolder> {

    private List<getdoctors.Doctor> categories;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewHomeAdapterDoctors(List<getdoctors.Doctor> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterDoctors.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.studentdesgin,
                viewGroup , false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterDoctors.RecyclerViewHolder viewHolder, int i) {


        String name = categories.get(i).getName();

        String grade = categories.get(i).getEmail();
      //  String date = categories.get(i).getDepartment();

       // int photo = categories.get(i).getPhoto();

        viewHolder.name.setText(name);
        viewHolder.grade.setText(grade);
      //  viewHolder.depart.setText(date);

      //  viewHolder.photo.setImageResource(photo);






        String photo = categories.get(i).getProfileImagePath();


        String urlen = Uri.encode(photo , "utf-8");



        String url = "http://fciapi.herokuapp.com/api/file/"+ urlen;


        viewHolder.link.setText(url);




        Picasso.get().load(url).placeholder(R.drawable.demp).into(viewHolder.photo);




    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

//
//    public Filter getFilter() {
//        return categoriesFilter;
//    }
//
//    private Filter categoriesFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence charSequence) {
//            List<Studentmodel.Info> filterlist = new ArrayList<>();
//            if (charSequence == null || charSequence.length() == 0)
//            {
//                filterlist.addAll(categoriesFull);
//            }else
//            {
//                String filterpattern = charSequence.toString().toLowerCase().trim();
//                for  (Studentmodel.Info item : categoriesFull)
//                {
//                    if (item.getDepartment().toLowerCase().contains(filterpattern))
//                    {
//                        filterlist.add(item);
//                    }
//                }
//            }
//            FilterResults results = new FilterResults();
//            results.values = filterlist ;
//            return results ;
////        }
//
//        @Override
//        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//            categories.clear();
//            categories.addAll((List) filterResults.values);
//            notifyDataSetChanged();
//
//        }
//    };

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
        RecyclerViewHomeAdapterDoctors.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
