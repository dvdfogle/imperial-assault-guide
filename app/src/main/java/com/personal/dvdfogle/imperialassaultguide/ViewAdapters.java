package com.personal.dvdfogle.imperialassaultguide;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

class ImageViewGridAdapter extends BaseAdapter {
    private final Context mContext;
    private final ImageSelect[] mImageViews;

    public ImageViewGridAdapter(Context context, ImageSelect[] imageViews) {
        this.mContext = context;
        this.mImageViews = imageViews;
    }

    @Override
    public int getCount() {
        return mImageViews.length;
    }
    @Override
    public long getItemId(int index) {
        return mImageViews[index].mDbId;
    }
    @Override
    public Object getItem(int index) {
        return null;
    }
    @Override
    public View getView(int index, View convertView, ViewGroup parent) {
        ImageSelect target = mImageViews[index];
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.class_image_select, null);
        }
        convertView.setBackgroundColor(target.backgroundColor(parent));

        ((TextView) convertView.findViewById(R.id.title)).setText(target.mTitle);

        if (target.mImageId != 0) {
            ImageView temp = convertView.findViewById(R.id.imageView);
            temp.setImageResource(target.mImageId);
            convertView.findViewById(R.id.detail).setVisibility(View.GONE);
        }
        if (target.mExtra != null) {
            ((TextView) convertView.findViewById(R.id.detail)).setText(target.mExtra);
        }
        return convertView;
    }
}


@SuppressLint("ValidFragment")
class ImageSelectFragment extends Fragment {
    Context context;
    GridView layout;
    ArrayList<ImageSelect> tiles;
    ToggleClickListener toggler;

    public ImageSelectFragment() {
        tiles = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle) {
        View rootView = inflater.inflate(R.layout.fragment_image_select_grid, container, false);
        layout = rootView.findViewById(R.id.layout);
        context = getContext();

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Generate ImageSelect objects for each expansion in the array
        Bundle args = getArguments();
        int page = args.getInt("step");
        if (tiles.size() < 1) {
            switch (page) {
                case 3:
                    // Green side missions
                    setupTextWithTitle(args.getString("array"), "reward");
                    break;
                default:
                    setupImageWithTitle(args.getString("array"));
                    break;
            }
        }

        // Generate grid items for each ImageSelect object
        ImageSelect[] temp = tiles.toArray(new ImageSelect[0]);
        ImageViewGridAdapter adapter = new ImageViewGridAdapter(context, temp);
        layout.setAdapter(adapter);

        // When a grid item is selected, implement toggling activation
        toggler = new ToggleClickListener((TogglerActivity) getActivity(), temp, adapter);
        layout.setOnItemClickListener(toggler);
    }

    private void setupImageWithTitle(String array) {
        try {
            JSONArray expansionList = new JSONArray(array);
            for (int i=0; i<expansionList.length(); i++) {
                JSONObject expansion = expansionList.getJSONObject(i);
                ImageSelect test = new ImageSelect(
                        context,
                        expansion.getInt("id"),
                        expansion.getString("name"));
                tiles.add(test);
            }
        }
        catch (JSONException e) {
            throw new Error("Error reading expansion list");
        }
    }

    private void setupTextWithTitle(String array, String extraKey) {
        try {
            JSONArray expansionList = new JSONArray(array);
            for (int i = 0; i < expansionList.length(); i++) {
                JSONObject expansion = expansionList.getJSONObject(i);
                ImageSelect test = new ImageSelect(
                        context,
                        expansion.getInt("id"),
                        expansion.getString("name"),
                        expansion.getString(extraKey));
                tiles.add(test);
            }
        } catch (JSONException e) {
            throw new Error("Error reading expansion list");
        }
    }
}