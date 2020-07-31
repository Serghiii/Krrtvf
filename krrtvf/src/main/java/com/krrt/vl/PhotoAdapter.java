package com.krrt.vl;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;

public class PhotoAdapter extends PagedListAdapter<Photo, PhotoAdapter.PhotoHolder> {

    Context context;
    private OnItemClickListener mListener;

    protected PhotoAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.photo_row_item, parent, false);
        final PhotoHolder holder = new PhotoHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.onItemClicked((Long)holder.itemView.getTag());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        Photo photo = getItem(position);
        if (photo != null) {
            holder.itemView.setTag(photo.getId());
            holder.title.setText(photo.getTitle());
            holder.date.setText(new SimpleDateFormat("dd.MM.yyyy").format(photo.getDate()));
            if (photo.getFilename() != null && !photo.getFilename().isEmpty()) {
                String url = String.format(context.getResources().getString(R.string.photo_url), photo.getId(), photo.getFilename());
                Glide.with(context).load(url).into(holder.img);
            }
        }
    }

    public static class PhotoHolder extends RecyclerView.ViewHolder {
        TextView title, date;
        ImageView img;

        public PhotoHolder(View view){
            super(view);
            title = view.findViewById(R.id.phr_title);
            date = view.findViewById(R.id.phr_date);
            img = view.findViewById(R.id.phr_img);
        }
    }

    public static DiffUtil.ItemCallback<Photo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Photo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Photo oldPhoto, @NonNull Photo newPhoto) {
            return oldPhoto.getId() == newPhoto.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Photo oldPhoto, @NonNull Photo newPhoto) {
            return oldPhoto.equals(newPhoto);
        }
    };

    public interface OnItemClickListener {
        void onItemClicked(Long id);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
