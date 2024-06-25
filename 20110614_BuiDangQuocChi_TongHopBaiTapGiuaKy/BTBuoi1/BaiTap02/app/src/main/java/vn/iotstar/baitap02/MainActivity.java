package vn.iotstar.baitap02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button, buttonClear;
    private TextView textView;
    private ArrayList<Integer> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        buttonClear = findViewById(R.id.buttonClear);

        arrayList = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy chuỗi dữ liệu từ EditText
                String input = editText.getText().toString();

                // Tách các số từ chuỗi dữ liệu và thêm vào mảng
                String[] numbers = input.split(",");
                for (String number : numbers) {
                    try {
                        int n = Integer.parseInt(number.trim());
                        arrayList.add(n);
                    } catch (NumberFormatException e) {
                        // Không phải số, bỏ qua
                    }
                }

                // Tìm và in ra các số chính phương trong mảng
                StringBuilder output = new StringBuilder();
                for (int number : arrayList) {
                    if (isPerfectSquare(number)) {
                        output.append(number).append(", ");
                        Toast.makeText(MainActivity.this, number + " là số chính phương", Toast.LENGTH_SHORT).show();
                    }
                }

                // Hiển thị kết quả trên TextView
                if (output.length() > 0) {
                    output.setLength(output.length() - 2);
                    textView.setText("Các số chính phương là: " + output.toString());
                } else {
                    textView.setText("Không có số chính phương trong mảng");
                }
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa dữ liệu trên View EditText và View TextView
                editText.setText("");
                textView.setText("");

                // Xóa dữ liệu trong mảng ArrayList
                arrayList.clear();
            }
        });
    }
    private boolean isPerfectSquare(int n) {
        int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    }

}