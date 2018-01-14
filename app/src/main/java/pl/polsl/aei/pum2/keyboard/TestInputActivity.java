package pl.polsl.aei.pum2.keyboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class TestInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_input);
        Button btn1 = findViewById(R.id.testInputReturnButton);
        Button btn2 = findViewById(R.id.sendTextButton);
        final EditText editText = findViewById(R.id.sendEditText);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                    hideKeyboard(view);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (editText.getText().length() == 0) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Nie ma żadnego tekstu do wysłania!", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), InputActivity.class);
                        intent.setAction(Intent.ACTION_SEND);
                        intent.putExtra("EditTextTest", editText.getText().toString());
                        intent.setType("text/plain");
                        startActivity(intent);
                    }
                } catch (Exception ex) {
                    Toast toast = Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
    protected void hideKeyboard(View view)
    {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
