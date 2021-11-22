package com.example.rentalapartmentapp.View.CustomUI;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.rentalapartmentapp.R;


public class CustomTextView extends AppCompatTextView {

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyFont();
    }


    private void applyFont(){
        Typeface boldTypeface = getResources().getFont(R.font.nunito);
        setTypeface(boldTypeface);
    }

}

