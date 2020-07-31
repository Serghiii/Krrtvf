package com.krrt.vl;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Press_centerNewsFragment extends Fragment {

    public Press_centerNewsFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_press_center_news, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_news);
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), DividerItemDecoration.VERTICAL));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        NewsViewModel newsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);
        final NewsAdapter newsAdapter = new NewsAdapter();
        newsViewModel.newsPagedList.observe(getActivity(), new Observer<PagedList<News>>() {
            @Override
            public void onChanged(@Nullable PagedList<News> news) {
                newsAdapter.submitList(news);
            }
        });
        recyclerView.setAdapter(newsAdapter);
        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Long id) {
                Intent intent = new Intent(getContext(), NewsActivity.class);
                intent.putExtra("id", id);
                getContext().startActivity(intent);
            }
        });


/*
        NewsDataSource dataSource = new NewsDataSource();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(NewsDataSource.PageSize)
                .build();

        Executor executor = Executors.newFixedThreadPool(5);

        PagedList<News> pagedList = new PagedList.Builder<>(dataSource, config)
                .setFetchExecutor(executor)
//                .setFetchExecutor(Executors.newSingleThreadExecutor())
//                .setNotifyExecutor(Executors.newSingleThreadExecutor())
                .build();

        NewsAdapter newsAdapter = new NewsAdapter();
        newsAdapter.submitList(pagedList);
        recyclerView.setAdapter(newsAdapter);
*/
        /*----------------*/
        /*
        newsAdapter = new NewsAdapter(rootView.getContext(), new ArrayList<News>());
        layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(newsAdapter);

        NetworkService.getInstance()
                .getJSONApi()
                .getNewsPage(Page)
                .enqueue(new Callback<List<News>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                        newsAdapter.addData(response.body());
                        newsAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (loading && dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        Page++;
                        NetworkService.getInstance()
                                .getJSONApi()
                                .getNewsPage(Page)
                                .enqueue(new Callback<List<News>>() {
                                    @Override
                                    public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                                        if (response.body().isEmpty()) { loading = false; }
                                        else {
                                            newsAdapter.addData(response.body());
                                            newsAdapter.notifyDataSetChanged();
                                        }
                                    }
                                    @Override
                                    public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable t) {
                                        t.printStackTrace();
                                    }
                                });
                    }
                }
            }
        });
        */

        return rootView;
    }

}