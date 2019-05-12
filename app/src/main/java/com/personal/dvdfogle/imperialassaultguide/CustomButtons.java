package com.personal.dvdfogle.imperialassaultguide;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

interface TogglerActivity {
    void toggleActive(int id);
}

class ToggleClickListener implements AdapterView.OnItemClickListener {
    private ImageSelect[] mImageSelects;
    private ImageViewGridAdapter mAdapter;
    private TogglerActivity mOwner;

    public ToggleClickListener(TogglerActivity owner, ImageSelect[] imageSelects, ImageViewGridAdapter adapter) {
        this.mImageSelects = imageSelects;
        this.mAdapter = adapter;
        this.mOwner = owner;
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int index, long id) {
        ImageSelect imageSelect = mImageSelects[index];
        imageSelect.toggleActive();
        mOwner.toggleActive(imageSelect.mDbId);
        mAdapter.notifyDataSetChanged();
    }
}

class ImageSelect {
    public int mDbId;
    public String mTitle;
    public int mImageId;
    public String mExtra;
    private boolean isActive;

    public ImageSelect(Context context, int id, String title) {
        this.mDbId = id;
        this.mTitle = title;
        String imageTitle = title.replaceAll("['()&-]+", "").replace(" ", "_").toLowerCase();
        this.mImageId = context.getResources().getIdentifier(
                imageTitle,
                "drawable",
                context.getPackageName());
        this.isActive = false;
    }

    public ImageSelect(Context context, int id, String title, String detail) {
        this.mDbId = id;
        this.mTitle = title;
        this.mExtra = detail;
        this.isActive = false;
        this.mImageId = 0;
    }

    public int backgroundColor(View parent) {
        if (isActive)
            return parent.getResources().getColor(R.color.colorPrimary);
        return Color.TRANSPARENT;
    }

    public void toggleActive() {
        this.isActive = !this.isActive;
    }
}