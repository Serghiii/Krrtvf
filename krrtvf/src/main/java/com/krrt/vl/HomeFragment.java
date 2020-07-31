package com.krrt.vl;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ViewPager viewPager = rootView.findViewById(R.id.viewPager);
        SwipeAdaptor swipeAdaptor = new SwipeAdaptor(rootView.getContext());
        viewPager.setAdapter(swipeAdaptor);
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);

        setListener((ImageView) rootView.findViewById(R.id.topic1), 0);
        setListener((ImageView) rootView.findViewById(R.id.topic2), 1);
        setListener((ImageView) rootView.findViewById(R.id.topic3), 2);
        setListener((ImageView) rootView.findViewById(R.id.topic4), 3);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            viewPager.getLayoutParams().height =(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
        }else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            viewPager.getLayoutParams().height =(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 375, getResources().getDisplayMetrics());
        }

        RecyclerView recyclerView = rootView.findViewById(R.id.rv_news);
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setBackgroundColor(Color.parseColor("#E9EDF7"));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setHasFixedSize(true);
        NewsHomeViewModel newsHomeViewModel = ViewModelProviders.of(getActivity()).get(NewsHomeViewModel.class);
        final NewsAdapter newsAdapter = new NewsAdapter();
        newsHomeViewModel.newsPagedList.observe(getActivity(), new Observer<PagedList<News>>() {
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

//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new SwipeTimer(), 4000, 8000);

        return rootView;
    }

    private void setListener(ImageView image, final int tabIndex){
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = getActivity();
                View ns = activity.findViewById(R.id.navigation_services);
                ns.setTag(tabIndex);
                ((BottomNavigationView)activity.findViewById(R.id.navigation)).setSelectedItemId(ns.getId());
            }
        });
    }
/*
    public class SwipeTimer extends TimerTask {

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0)viewPager.setCurrentItem(1);
                    else if (viewPager.getCurrentItem() == 1)viewPager.setCurrentItem(2);
                    else if (viewPager.getCurrentItem() == 2)viewPager.setCurrentItem(3);
                    else if (viewPager.getCurrentItem() == 3)viewPager.setCurrentItem(4);
                    else if (viewPager.getCurrentItem() == 4)viewPager.setCurrentItem(0);
                }
            });
        }
    }
*/
    public class SwipeAdaptor extends PagerAdapter {

        private Context mContext;
        private int[] mImageIds = new int[]{R.drawable.slider_logo, R.drawable.slider_mast, R.drawable.slider_checkpoint, R.drawable.slider_office, R.drawable.slider_region};

        public SwipeAdaptor(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            View itemView = layoutInflater.inflate(R.layout.swipe_layout, container, false);
            ImageView imageView = itemView.findViewById(R.id.image_view);
            imageView.setImageResource(mImageIds[position]);
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }

    }
/*
    public class ImageAdaptor extends PagerAdapter{

        private Context mContext;
        private int[] mImageIds = new int[]{R.drawable.slider_logo, R.drawable.slider_mast, R.drawable.slider_checkpoint, R.drawable.slider_office, R.drawable.slider_region};

        public ImageAdaptor(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(
                    new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setAdjustViewBounds(true);
            imageView.setImageResource(mImageIds[position]);
            container.addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }

    }
*/
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ViewPager viewPager = getActivity().findViewById(R.id.viewPager);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            viewPager.getLayoutParams().height =(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 380, getResources().getDisplayMetrics());
        }else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            viewPager.getLayoutParams().height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());;
        }
    }

}
