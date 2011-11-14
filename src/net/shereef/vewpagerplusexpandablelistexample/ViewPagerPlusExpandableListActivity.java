package net.shereef.vewpagerplusexpandablelistexample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class ViewPagerPlusExpandableListActivity extends Activity {

	private class MyViewPagerAdapter extends PagerAdapter {

		/**
		 * Remove a page for the given position. The adapter is responsible for
		 * removing the view from its container, although it only must ensure
		 * this is done by the time it returns from {@link #finishUpdate()}.
		 * 
		 * @param container
		 *            The containing View from which the page will be removed.
		 * @param position
		 *            The page position to be removed.
		 * @param object
		 *            The same object that was returned by
		 *            {@link #instantiateItem(View, int)}.
		 */
		@Override
		public void destroyItem(View collection, int position, Object view) {
			((ViewPager) collection).removeView((View) view);
		}

		/**
		 * Called when the a change in the shown pages has been completed. At
		 * this point you must ensure that all of the pages have actually been
		 * added or removed from the container as appropriate.
		 * 
		 * @param container
		 *            The containing View which is displaying this adapter's
		 *            page views.
		 */
		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return 10; // Number of pages usually set with .length() or .size()
		}

		/**
		 * Create the page for the given position. The adapter is responsible
		 * for adding the view to the container given here, although it only
		 * must ensure this is done by the time it returns from
		 * {@link #finishUpdate()}.
		 * 
		 * @param container
		 *            The containing View in which the page will be shown.
		 * @param position
		 *            The page position to be instantiated.
		 * @return Returns an Object representing the new page. This does not
		 *         need to be a View, but can be some other container of the
		 *         page.
		 */
		@Override
		public Object instantiateItem(View collection, int position) {
			final LayoutInflater inflater = (LayoutInflater) ViewPagerPlusExpandableListActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			TextView tv = new TextView(ViewPagerPlusExpandableListActivity.this);
			switch (position) {
			case 2: // Full ExpandableList code here
				View v = inflater.inflate(R.layout.expander, null, false);
				ExpandableListView elv1 = (ExpandableListView) v
						.findViewById(R.id.elv1);
				if (true) { // if the size of the data source is greater than 0
							// do the following
					ViewPagerPlusExpandableListActivity.this.mAdapter = new BaseExpandableListAdapter() {

						@Override
						public Object getChild(int groupPosition,
								int childPosition) {
							return null;
						}

						@Override
						public long getChildId(int groupPosition,
								int childPosition) {
							return 0;
						}

						@Override
						public int getChildrenCount(int groupPosition) {
							return 3; // Size of children usually taken from
										// .length or .size()
						}

						@Override
						public View getChildView(int groupPosition,
								int childPosition, boolean isLastChild,
								View convertView, ViewGroup parent) {
							View v = inflater.inflate(R.layout.twolinelistitem,
									null, false);
							TextView tv = (TextView) v
									.findViewById(R.id.simple_expandable_list_item_2_text1);
							tv.setText("Child " + (childPosition + 1)
									+ " of group " + (groupPosition + 1));
							return v;
						}

						@Override
						public Object getGroup(int groupPosition) {
							return null;
						}

						@Override
						public int getGroupCount() {
							return 12; // Groups count usually taken from
										// .length or .size()
						}

						@Override
						public long getGroupId(int groupPosition) {
							return 0;
						}

						@Override
						public View getGroupView(int groupPosition,
								boolean isExpanded, View convertView,
								ViewGroup parent) {
							View v = inflater.inflate(R.layout.twolinelistitem,
									parent, false);
							TextView tv = (TextView) v
									.findViewById(R.id.simple_expandable_list_item_2_text1);
							tv.setText("Group " + (groupPosition + 1));
							return v;
						}

						@Override
						public boolean hasStableIds() {
							return false;
						}

						@Override
						public boolean isChildSelectable(int groupPosition,
								int childPosition) {
							return true;
						}
					};
					elv1.setAdapter(ViewPagerPlusExpandableListActivity.this.mAdapter);

					((ViewPager) collection).addView(v, 0);
					return v;
				}
			default:
				tv.setText("Hello page " + (position + 1) + " of "
						+ this.getCount());
				tv.setTextColor(Color.WHITE);
				tv.setTextSize(30);
				((ViewPager) collection).addView(tv, 0);
				return tv;
			}
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}

	}

	public ExpandableListAdapter mAdapter;

	private MyViewPagerAdapter myAdapter;
	private ViewPager myPager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);

		this.myAdapter = new MyViewPagerAdapter();
		this.myPager = (ViewPager) this.findViewById(R.id.viewpager);
		this.myPager.setAdapter(this.myAdapter);
	}
}