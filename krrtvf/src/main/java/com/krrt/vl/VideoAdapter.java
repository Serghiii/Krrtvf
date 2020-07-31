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

public class VideoAdapter extends PagedListAdapter<Video, VideoAdapter.VideoHolder> {

    Context context;
    private OnItemClickListener mListener;

    protected VideoAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.video_row_item, parent, false);
        final VideoHolder holder = new VideoHolder(view);
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
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        Video video = getItem(position);
        if (video != null) {
            holder.itemView.setTag(video.getId());
            holder.title.setText(video.getTitle());
            holder.date.setText(new SimpleDateFormat("dd.MM.yyyy").format(video.getDate()));
            if (video.getFilename() != null && !video.getFilename().isEmpty()) {
                String url = String.format(context.getResources().getString(R.string.video_url), video.getId(), video.getFilename());
                Glide.with(context).load(url).into(holder.img);
            }
        }
    }

    public static class VideoHolder extends RecyclerView.ViewHolder {
        TextView title, date;
        ImageView img;

        public VideoHolder(View view){
            super(view);
            title = view.findViewById(R.id.vr_title);
            date = view.findViewById(R.id.vr_date);
            img = view.findViewById(R.id.vr_img);
        }
    }

    public static DiffUtil.ItemCallback<Video> DIFF_CALLBACK = new DiffUtil.ItemCallback<Video>() {
        @Override
        public boolean areItemsTheSame(@NonNull Video oldVideo, @NonNull Video newVideo) {
            return oldVideo.getId() == newVideo.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Video oldVideo, @NonNull Video newVideo) {
            return oldVideo.equals(newVideo);
        }
    };

    public interface OnItemClickListener {
        void onItemClicked(Long id);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

}
