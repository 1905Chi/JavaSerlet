package vn.iotstar.songaunhien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextMin;
    private EditText mEditTextMax;
    private TextView mTextViewResult;
    private  Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextMin = findViewById(R.id.edit_text_min);
        mEditTextMax = findViewById(R.id.edit_text_max);
        mTextViewResult = findViewById(R.id.text_view_result);
        buttonClear = findViewById(R.id.buttonClear);

        Button buttonGenerate = findViewById(R.id.button_generate);
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int min = Integer.parseInt(mEditTextMin.getText().toString());
                int max = Integer.parseInt(mEditTextMax.getText().toString());

                if (min > max) {
                    Toast.makeText(MainActivity.this, "Invalid range", Toast.LENGTH_SHORT).show();
                    return;
                }

                Random rand = new Random();
                int randomNumber = rand.nextInt(max - min + 1) + min;

                mTextViewResult.setText(String.valueOf(randomNumber));
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa dữ liệu trên View EditText và View TextView
                mEditTextMin.setText("");
                mEditTextMax.setText("");
                mTextViewResult.setText("");
            }
        });
    }
}