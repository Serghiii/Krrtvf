package com.krrt.vl;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;


public class NewsAdapter extends PagedListAdapter<News, NewsAdapter.NewsHolder> {

    private OnItemClickListener mListener;

    protected NewsAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row_item, parent, false);
        final NewsHolder holder = new NewsHolder(view);
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
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class NewsHolder extends RecyclerView.ViewHolder {

        TextView title, description, date;

        public NewsHolder(View view){
            super(view);
            title = view.findViewById(R.id.nr_title);
            description = view.findViewById(R.id.nr_description);
            date = view.findViewById(R.id.nr_date);
        }

        public void bind(News news) {
            if (news == null){
                title.setText(R.string.wait);
                description.setText(R.string.wait);
                date.setText(R.string.wait);
            } else {
                itemView.setTag(news.getId());
                title.setText(news.getTitle());
                description.setText(news.getDescription());
                date.setText(new SimpleDateFormat("dd.MM.yyyy").format(news.getDate()));
            }
        }

    }

    public static DiffUtil.ItemCallback<News> DIFF_CALLBACK = new DiffUtil.ItemCallback<News>() {
        @Override
        public boolean areItemsTheSame(@NonNull News oldNews, @NonNull News newNews) {
            return oldNews.getId() == newNews.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull News oldNews, @NonNull News newNews) {
            return oldNews.equals(newNews);
        }
    };

    public interface OnItemClickListener {
        void onItemClicked(Long id);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

}

/*
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private Context mContext;
    private List<News> mData;

    public NewsAdapter(Context mContext, List<News> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.news_row_item, viewGroup, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder newsHolder, int i) {
        newsHolder.linearLayout.setTag(mData.get(i).getId());
        newsHolder.title.setText(mData.get(i).getTitle());
        newsHolder.description.setText(mData.get(i).getDescription());
        newsHolder.date.setText(new SimpleDateFormat("MM.dd.yyyy").format(mData.get(i).getDate()));
        newsHolder.linearLayout.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsActivity.class);
                intent.putExtra("id", v.getTag().toString());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class NewsHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView title, description, date;
        public NewsHolder(View view){
            super(view);
            linearLayout =  view.findViewById(R.id.nr_layout);
            title = view.findViewById(R.id.nr_title);
            description = view.findViewById(R.id.nr_description);
            date = view.findViewById(R.id.nr_date);
        }
    }

    public void addData(List<News> data) {
        mData.addAll(data);
    }
}
*/