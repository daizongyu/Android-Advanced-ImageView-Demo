package com.appsdk.androidadvancedimageview.demo;

import java.util.ArrayList;

import com.appsdk.advancedimageview.AdvancedImageCarousel;
import com.appsdk.advancedimageview.AdvancedImageView;
import com.appsdk.advancedimageview.listener.AdvancedImageCarouselClickListener;
import com.appsdk.advancedimageview.listener.AdvancedImageCarouselSwitchListener;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TabHost mTabHost;
	private ListView mListView;
	private BaseAdapter mAdapter;
	private LayoutInflater mInflater;
	private ImageType mType;
	private AdvancedImageCarousel mImageCarousel;
	private RelativeLayout mBottomView;
	private TextView mBottomViewTextView;

	/**
	 * You can set the correct picture urls below
	 */
	ArrayList<String> mPicUrlList = new ArrayList<String>();

	private void setPicUrl() {
		mPicUrlList.add("http://fn.zdmimg.com/201403/19/53290db6530d4.jpg_n6.jpg");
		mPicUrlList.add("http://fn.zdmimg.com/201403/19/53290e24c2cf0.jpg_n6.jpg");
		mPicUrlList.add("http://fn.zdmimg.com/201403/19/53290b1c4debf.jpg_n6.jpg");
		mPicUrlList.add("http://fn.zdmimg.com/201403/19/532907220295e.jpg_n6.jpg");
		mPicUrlList.add("http://fn.zdmimg.com/201403/19/532907f896abd.jpg_n6.jpg");
		mPicUrlList.add("http://fn.zdmimg.com/201403/19/53290912f1644.jpg_n6.jpg");
		mPicUrlList.add("http://fn.zdmimg.com/201403/19/532904d005b78.jpg_n6.jpg");
		mPicUrlList.add("http://fn.zdmimg.com/201403/19/5329064d011f5.jpg_n6.jpg");
		mPicUrlList.add("http://fn.zdmimg.com/201403/19/5329069119ee2.jpg_n6.jpg");
		mPicUrlList.add("http://fn.zdmimg.com/201403/19/532902561b051.jpg_n6.jpg");
		mPicUrlList.add("http://fn.zdmimg.com/201403/19/53290449125bc.jpg_n6.jpg");
		mPicUrlList.add("http://fn.zdmimg.com/201403/19/5329042b5817c.jpg_n6.jpg");
		mPicUrlList.add("http://fn.zdmimg.com/201403/19/5328ffb3a7684.jpg_n6.jpg");
		mPicUrlList.add("http://fn.zdmimg.com/201403/19/5329012da69af.jpg_n6.jpg");
	}

	/**
	 * You should put image in you sd card, and set the correct path below
	 */
	ArrayList<String> mPicPathList = new ArrayList<String>();

	private void setPicPath() {
		mPicPathList.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/1.jpg");
		mPicPathList.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/2.jpg");
		mPicPathList.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/3.jpg");
		mPicPathList.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/4.jpg");
		mPicPathList.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/5.jpg");
		mPicPathList.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/6.jpg");
		mPicPathList.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/7.jpg");
		mPicPathList.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/8.jpg");
		mPicPathList.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/9.jpg");
		mPicPathList.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/10.jpg");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mInflater = LayoutInflater.from(this);
		mType = ImageType.NetImage;
		setPicUrl();
		setPicPath();
		initTabHost();
		initListView();
		initImageCarousel();
	}

	private void initImageCarousel() {
		// init AdvancedImageCarousel
		mImageCarousel = (AdvancedImageCarousel) findViewById(R.id.image_carousel);

		// Set configurations
		mImageCarousel.setScaleType(ScaleType.CENTER_CROP);
		mImageCarousel.setIntervalTime(2000);

		// Add AdvancedImageView to Carousel
		AdvancedImageView imageView1 = new AdvancedImageView(this);
		imageView1.setLocalImage(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/1.jpg");
		mImageCarousel.addCarouselView(imageView1);

		// Add image path to Carousel
		mImageCarousel.addCarouselViewByPath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test/2.jpg");

		// Add url to Carousel
		mImageCarousel.addCarouselViewByUrl("http://fn.zdmimg.com/201403/19/5329012da69af.jpg_n6.jpg", 1);

		// Add bottom view to Carousel
		mBottomView = (RelativeLayout) mInflater.inflate(R.layout.bottom_view, null);
		mImageCarousel.addBottomView(mBottomView);
		mBottomViewTextView = (TextView) mBottomView.findViewById(R.id.text);

		// Set Carousel switch listener
		mImageCarousel.setOnCarouselSwitchListener(new AdvancedImageCarouselSwitchListener() {
			@Override
			public void onImageSwitch(int position) {
				mBottomViewTextView.setText(MainActivity.this.getResources().getString(R.string.bottom_view_text) + " " + position);
			}
		});

		mImageCarousel.setOnCarouselClickListener(new AdvancedImageCarouselClickListener() {
			@Override
			public void onImageClick(int position) {
				Toast.makeText(MainActivity.this, "click " + position, Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * Load picture to AdvancedImageView, use image url or local file path
	 */
	private void loadViewHolder(int position, View convertView) {
		ViewHolder holder = (ViewHolder) convertView.getTag();
		if (mType == ImageType.NetImage) {
			holder.image.setNetImage(mPicUrlList.get(position));
			holder.content.setText("net image " + position);
		} else {
			holder.image.setLocalImage(mPicPathList.get(position));
			holder.content.setText("local image " + position);
		}
	}

	/**
	 * Call AdvancedImageView.destory(Context context) to clear bitmap cache
	 */
	@Override
	protected void onDestroy() {
		AdvancedImageView.destory();
		super.onDestroy();
	}

	private void initTabHost() {
		mTabHost = (TabHost) findViewById(R.id.tabhost);
		mTabHost.setup();
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				if (mTabHost.getCurrentTab() == 0) {
					mType = ImageType.NetImage;
				} else {
					mType = ImageType.LocalImage;
				}
				if (mAdapter != null) {
					mAdapter.notifyDataSetChanged();
				}
				for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
					View view = (View) mTabHost.getTabWidget().getChildAt(i);
					if (mTabHost.getCurrentTab() == i) {
						((TextView) view.findViewById(R.id.text)).setTextColor(getResources().getColor(R.color.red));
						view.setBackgroundResource(R.color.background_dark);
					} else {
						((TextView) view.findViewById(R.id.text)).setTextColor(getResources().getColor(R.color.black));
						view.setBackgroundResource(R.color.background_light);
					}
				}
			}
		});
		View view = mInflater.inflate(R.layout.tab_item, null);
		((TextView) view.findViewById(R.id.text)).setText("Net Image");
		((TextView) view.findViewById(R.id.text)).setGravity(Gravity.CENTER);
		mTabHost.addTab(mTabHost.newTabSpec("Net Image").setIndicator(view).setContent(R.id.listview));
		View view1 = mInflater.inflate(R.layout.tab_item, null);
		((TextView) view1.findViewById(R.id.text)).setText("Local Image");
		((TextView) view1.findViewById(R.id.text)).setGravity(Gravity.CENTER);
		mTabHost.addTab(mTabHost.newTabSpec("Local Image").setIndicator(view1).setContent(R.id.listview));
		mTabHost.setCurrentTab(1);
		mTabHost.setCurrentTab(0);
	}

	private void initListView() {
		mListView = (ListView) findViewById(R.id.listview);
		mAdapter = new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ViewHolder holder = null;
				if (convertView == null) {
					convertView = mInflater.inflate(R.layout.list_item, null);
					holder = new ViewHolder();
					holder.image = (AdvancedImageView) convertView.findViewById(R.id.image);
					holder.content = (TextView) convertView.findViewById(R.id.content);
					convertView.setTag(holder);
					loadViewHolder(position, convertView);
				} else {
					loadViewHolder(position, convertView);
				}
				return convertView;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public Object getItem(int position) {
				return null;
			}

			@Override
			public int getCount() {
				if (mType == ImageType.NetImage) {
					return mPicUrlList.size();
				} else {
					return mPicPathList.size();
				}
			}
		};
		mListView.setAdapter(mAdapter);
	}

	private enum ImageType {
		NetImage, LocalImage
	}

	private class ViewHolder {
		public AdvancedImageView image;
		public TextView content;
	}
}
