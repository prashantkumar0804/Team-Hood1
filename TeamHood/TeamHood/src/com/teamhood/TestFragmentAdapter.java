package com.teamhood;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.viewpagerindicator.IconPagerAdapter;

class TestFragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
    protected static final String[] CONTENT = new String[] { "Welcome to Teamhood sweep to learn\nmore about what Teamhood can do\nfor you.", "Welcome to Teamhood sweep to learn\nmore about what Teamhood can do\nfor you.", "Welcome to Teamhood sweep to learn\nmore about what Teamhood can do\nfor you.", "Welcome to Teamhood sweep to learn\nmore about what Teamhood can do\nfor you.", };
    protected static final int[] ICONS = new int[] {
            R.drawable.slide_icon,
            R.drawable.notes_icon,
            R.drawable.chat_icon,
            R.drawable.slide_icon
    };

    private int mCount = CONTENT.length;
    Context ctx;

    public TestFragmentAdapter(FragmentManager fm, Context ctx2) {
    	
        super(fm);
        this.ctx=ctx2;
    }

    @Override
    public Fragment getItem(int position) {
    	
        return TestFragment.newInstance(ctx,CONTENT[position % CONTENT.length],ICONS[position % ICONS.length]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return TestFragmentAdapter.CONTENT[position % CONTENT.length];
    }

    @Override
    public int getIconResId(int index) {
      return ICONS[index % ICONS.length];
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}