
package pl.polsl.aei.pum2.keyboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class InputActivity extends FragmentActivity {
    ViewPager viewPager;
    TextToSpeech tts;
    TextView textView, textToCompare;
    String[] comparingLettersArray = {};
    int comparingLettersArrayIterator = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_activity);
        viewPager = findViewById(R.id.view_pager);
        textToCompare = findViewById(R.id.textToCompare);
        if (getIntent().getExtras() != null){
            String data = getIntent().getStringExtra("EditTextTest");
            if (data.length() > 0) {
                comparingLettersArrayIterator = 1;
                textToCompare.setText(data.toUpperCase());
                comparingLettersArray = data.split("");

            }
        }
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);
        viewPager.setCurrentItem(1, false);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int currentPosition, pos;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = viewPager.getCurrentItem();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE){
                    final int lastPosition = viewPager.getAdapter().getCount();
                    if (currentPosition == lastPosition - 1)
                        pos = 1;
                    else if (currentPosition == 0)
                        pos = lastPosition - 2;
                    else
                        pos = currentPosition;
                    viewPager.setCurrentItem(pos, false);
                    speakCharacter(LettersArray.charArray[pos]);
                }
            }
        });
        textView = findViewById(R.id.viewKeyboardText);
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS){
                    int result = tts.setLanguage(new Locale("pl_PL"));
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                        Log.e("TTS", "Language not supported");
                } else Log.e("TTS", "Init failed");
            }
        });
        final Button button = findViewById(R.id.back_input_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void appendToKeyboard(String text){
        String oldText = textView.getText().toString();
        if (comparingLettersArray.length > 0 && comparingLettersArrayIterator < comparingLettersArray.length) {
            if (text.equals(comparingLettersArray[comparingLettersArrayIterator].toUpperCase())) {
                Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                v.vibrate(500);
                comparingLettersArrayIterator = (comparingLettersArrayIterator == comparingLettersArray.length) ? comparingLettersArrayIterator : comparingLettersArrayIterator + 1;
            }
        }
        textView.setText(oldText + text);
    }
    public void speakKeyboard(){
        tts.speak(textView.getText().toString(), TextToSpeech.QUEUE_FLUSH,null, null);
    }
    public void speakCharacter(String text){
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }
    public void removeFromKeyboard(){
        String keyboardString = textView.getText().toString();
        try {
            if (keyboardString != null && keyboardString.length() > 0) {
                String lastLetter = keyboardString.substring(keyboardString.length() - 1, keyboardString.length());
                textView.setText(keyboardString.substring(0, keyboardString.length() - 1));
                if (comparingLettersArrayIterator > 1 && lastLetter.equals(comparingLettersArray[comparingLettersArrayIterator - 1].toUpperCase())) {
                    comparingLettersArrayIterator--;
                }
            } else {
                Toast.makeText(getApplicationContext(), "Brak znaków w klawiaturze!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex ){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
