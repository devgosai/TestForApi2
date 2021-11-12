package com.app.testforapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.testforapi.R;
import com.app.testforapi.api.PojoList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {

    List<PojoList.Datum> list;
    Context context;

    public RecyclerviewAdapter(List<PojoList.Datum> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        RecyclerviewAdapter.MyViewHolder viewHolder = new RecyclerviewAdapter.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapter.MyViewHolder holder, int position) {

        //animation
        holder.custom.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));

        holder.name.setText(list.get(position).getFirstName());
        holder.email.setText(list.get(position).getEmail());

        Picasso.get().load(list.get(position).getAvatar())
                .placeholder(R.drawable.ic_launcher_foreground)
                .fit()
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, email;
        ImageView image;
        RelativeLayout custom;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            email = itemView.findViewById(R.id.tvEmail);
            name = itemView.findViewById(R.id.tvName);
            image = itemView.findViewById(R.id.ivPicture);
            custom = itemView.findViewById(R.id.relative_layout);

        }
    }
}
