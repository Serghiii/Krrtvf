package com.krrt.vl;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Bundle;
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
public class Press_centerVideoFragment extends Fragment {


    public Press_centerVideoFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_press_center_video, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_video);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        VideoViewModel videoViewModel = ViewModelProviders.of(getActivity()).get(VideoViewModel.class);
        final VideoAdapter videoAdapter = new VideoAdapter();
        videoViewModel.videoPagedList.observe(getActivity(), new Observer<PagedList<Video>>() {
            @Override
            public void onChanged(@Nullable PagedList<Video> video) {
                videoAdapter.submitList(video);
            }
        });
        recyclerView.setAdapter(videoAdapter);
        videoAdapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Long id) {
                Intent intent = new Intent(getContext(), VideoActivity.class);
                intent.putExtra("id", id);
                getContext().startActivity(intent);
            }
        });
        return rootView;
    }

}
