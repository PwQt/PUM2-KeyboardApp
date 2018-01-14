package pl.polsl.aei.pum2.keyboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonVibrate = findViewById(R.id.button_vibrate);
        final Button buttonWrite = findViewById(R.id.button_write);
        buttonWrite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    Intent intent = new Intent(getApplicationContext(), InputActivity.class);
                    startActivity(intent);
                }
                catch (Exception ex)
                {
                    toast = Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        buttonVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast = Toast.makeText(getApplicationContext(), "HAHAHAHAHAHAAHAH", Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }
}
