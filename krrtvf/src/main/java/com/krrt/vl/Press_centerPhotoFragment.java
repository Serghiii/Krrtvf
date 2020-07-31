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
public class Press_centerPhotoFragment extends Fragment {

    public Press_centerPhotoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_press_center_photo, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_photo);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        PhotoViewModel photoViewModel = ViewModelProviders.of(getActivity()).get(PhotoViewModel.class);
        final PhotoAdapter photoAdapter = new PhotoAdapter();
        photoViewModel.photoPagedList.observe(getActivity(), new Observer<PagedList<Photo>>() {
            @Override
            public void onChanged(@Nullable PagedList<Photo> photo) {
                photoAdapter.submitList(photo);
            }
        });
        recyclerView.setAdapter(photoAdapter);
        photoAdapter.setOnItemClickListener(new PhotoAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Long id) {
                Intent intent = new Intent(getContext(), PhotoActivity.class);
                intent.putExtra("id", id);
                getContext().startActivity(intent);
            }
        });
        return rootView;
    }

}
