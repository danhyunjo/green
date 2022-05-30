package kr.ac.cnu.computer.savagr;

import android.content.Context;
import android.view.MotionEvent;
import android.webkit.WebView;

import androidx.annotation.NonNull;

public class CustomView extends WebView {

    public CustomView(@NonNull Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(event);
    }
}