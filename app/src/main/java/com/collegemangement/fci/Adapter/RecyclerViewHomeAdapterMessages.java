package com.collegemangement.fci.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.collegemangement.fci.ClassesModel.messagesContacts;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.R;
import com.collegemangement.fci.message.HomeView;
import com.collegemangement.fci.message.messagePresenter;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewHomeAdapterMessages extends RecyclerView.Adapter<RecyclerViewHomeAdapterMessages.RecyclerViewHolder> implements HomeView {

    List<messagesContacts.Contact> categories ;
    private Context context;
    private static ClickListener clickListener;
    Helper helper ;
    String cursor ;
    messagePresenter messagePresenter;
    String secUser ;

    public RecyclerViewHomeAdapterMessages(List<messagesContacts.Contact> categories , Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapterMessages.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.messagedes,
                viewGroup , false);

        helper = new Helper(context);
        cursor = helper.getAlldata();
        messagePresenter = new messagePresenter(this);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapterMessages.RecyclerViewHolder viewHolder, int i) {


        String name = categories.get(i).getName();
        String message = categories.get(i).getLastMessage().getContent();
        String time = categories.get(i).getLastMessage().getTimestemp();
        String photo = categories.get(i).getProfileImagePath();
        secUser = categories.get(i).getId();

        messagePresenter.unreadMessages(cursor , secUser, viewHolder.num);


        viewHolder.name.setText(name);
        viewHolder.message.setText(message);
        viewHolder.time.setText(time);
        try {
            Picasso.get().load(photo).placeholder(R.drawable.demp).into(viewHolder.photo);

        }catch (Exception e){}

    }


    @Override
    public int getItemCount() {
        return categories.size() ;

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void messagesContacts(List<messagesContacts.Contact> messagesContacts) {

    }

    @Override
    public void SearchBykey(List<messagesContacts.Contact> SearchBykey) {

    }

    @Override
    public void unreadMessagesBetween2Users(String unreadMessagesBetween2Users, TextView textView) {
        Log.e("u" , unreadMessagesBetween2Users);

        textView.setText(unreadMessagesBetween2Users);

    }




    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.message)
        TextView message;

        @BindView(R.id.time)
        TextView time;

        @BindView(R.id.num)
        TextView num;

        @BindView(R.id.photo)
        CircularImageView photo ;



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
        RecyclerViewHomeAdapterMessages.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}
