package pl.polsl.aei.pum2.keyboard;


import android.gesture.Gesture;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

/**
 * Klasa odpowiadająca za swipe lewo-prawo
 */
public class PageFragment extends Fragment {
    TextView textView;

    public PageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_fragment_layout, container, false);
        textView = (TextView)view.findViewById(R.id.textView);
        Bundle bundle = getArguments();
        final String message = bundle.getString("Character");
        textView.setText(message);
        final GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
            private static final int SWIPE_MIN_DISTANCE = 120;
            private static final int SWIPE_THRESHOLD_VELOCITY = 200;
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return true;
            }
            @Override
            public void onLongPress(MotionEvent event) {
                ((InputActivity)getActivity()).speakKeyboard();
            }
            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                ((InputActivity)getActivity()).speakCharacter(message);
                return true;
            }
            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                if (motionEvent.getY() - motionEvent1.getY() > SWIPE_MIN_DISTANCE && Math.abs(v1) > SWIPE_THRESHOLD_VELOCITY) {
                    ((InputActivity)getActivity()).appendToKeyboard(message);
                    ((InputActivity)getActivity()).speakKeyboard();
                    return true;
                } else if (motionEvent1.getY() - motionEvent.getY() > SWIPE_MIN_DISTANCE && Math.abs(v1) > SWIPE_THRESHOLD_VELOCITY) {
                    ((InputActivity)getActivity()).removeFromKeyboard();
                    ((InputActivity)getActivity()).speakKeyboard();
                    return true;
                }
                return false;
            }
            @Override
            public boolean onDoubleTap(MotionEvent event){
                ((InputActivity)getActivity()).appendToKeyboard(" ");
                ((InputActivity)getActivity()).speakKeyboard();
                return true;
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }

        });
        return view;
    }


}
