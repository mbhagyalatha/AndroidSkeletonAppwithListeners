package com.imaginnovatestructureapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imaginnovatestructureapp.R;

/**
 * Created by Santosh on 6/8/2016.
 */
public class SlideshowFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);
        ((TextView) rootView.findViewById(R.id.text)).setText("Tapped on Slideshow");
        return rootView;
    }
}
