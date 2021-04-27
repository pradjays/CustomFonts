package com.example.customfonts;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {
    TextView secondText;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        secondText = view.findViewById(R.id.textview_second);
        Typeface brushUpToo = ResourcesCompat.getFont(getContext(), R.font.brush_up_too);
        secondText.setTypeface(brushUpToo);

//        String url = "https://www.dropbox.com/s/gd6ozdqwrxo9mo6/Pretty%20Magnolia.ttf?raw=1";

        String url = "https://www.dropbox.com/s/y980vywiprd8eci/riesling.ttf?raw=1";

        ContextHelper.downloadFont(getContext(), url);

        view.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_thirdFragment);
            }
        });

        view.findViewById(R.id.button_previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
}