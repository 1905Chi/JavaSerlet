package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private Switch switch_bg;
    private ConstraintLayout bg;
    private int current_color;
    private Button btnButton, btnRnd, progress, btn_off, btn_dialog;
    private TextView txtSoN;
    private ImageView mg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        switch_bg = (Switch) findViewById(R.id.switch_bg);
        btnButton = (Button) findViewById(R.id.btn_Drawable_Shape_Custom);
        btn_off = (Button) findViewById(R.id.btn_off);
        progress = (Button) findViewById(R.id.progress);
        mg1 = (ImageView) findViewById(R.id.img);
        registerForContextMenu(btnButton);
        bg = (ConstraintLayout) findViewById(R.id.constraintLayout1);

        btn_dialog = (Button) findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog1();
            }
        });


        // ánh xạ
        txtSoN = (TextView) findViewById(R.id.textViewSo);
        btnRnd = (Button) findViewById(R.id.buttonRnd);



        // sinh ngẫu nhiên
        btnRnd.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          //tạo số ngẫu nhiên
                                          Random random = new Random();
                                          int number = random.nextInt(10);
                                          txtSoN.setText(number + ""); //number + ""  ép kiểu }
                                      }
                                  });

        ImageButton img_button1 = (ImageButton)
                findViewById(R.id.imageButton1);
        img_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mg1.setImageResource(R.drawable.anh);
                mg1.getLayoutParams().width=300;
                mg1.getLayoutParams().height=300;
            }
        });

        // THOÁT DÙNG ALERT DIALOG
        btn_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XacNhanXoa(0);
            }
        });
        // SWITCH
        Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){ //isChecked = true
                    Toast.makeText(MainActivity.this,"Wifi đang bật",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Wifi đang tắt",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // CHeck box
        CheckBox ck1 = (CheckBox) findViewById(R.id.checkBox2);
        ck1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked){
                    bg.setBackgroundResource(R.drawable.bg3);
                }else{
                    bg.setBackgroundResource(R.drawable.bg4);
                }
            }
        });

        // RADIO GROUP
        RadioGroup radioGroup = (RadioGroup)
                findViewById(R.id.radioGroup1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { //checkID trả về ID radio
                switch (checkedId){
                    case R.id.radioButton:
                        bg.setBackgroundResource(R.drawable.bg3);
                        break;
                    case R.id.radioButton2:
                        bg.setBackgroundResource(R.drawable.bg4);
                        break;
                }
            }
        });



        // PROGRESBAR 1
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = progressBar.getProgress();
                progressBar.setProgress(current + 10);
            }
        });


        // PROGRESBAR 2
        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tự đếm progress
                CountDownTimer countDownTimer = new CountDownTimer(10000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int current = progressBar2.getProgress();
                        //chạy đều đều >100 quay về 0
                        if (current>= progressBar2.getMax()){
                            current=0;
                        }
                        progressBar2.setProgress(current + 10); //thiết lập progress
                    }
                    @Override
                    public void onFinish() {
                        Toast.makeText(MainActivity.this,"Hết giờ",Toast.LENGTH_LONG).show();
                    }
                };
                countDownTimer.start();
            }
        });

        // SEEKBAR
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress: giá trị của seekbar
                Log.d("AAA","Giá trị:" + progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("AAA","Start");
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("AAA","Stop");
            }});

        // BÀI TẬP 2: THAY ĐỔI NỀN APP KHI BẤM VÀO SWITCH
        switch_bg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    bg.setBackgroundResource(R.drawable.ic_launcher_background);
                }
                else {
                    bg.setBackgroundResource(current_color);
                }
            }
        });
    }

    // MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSetting:
                //lệnh
                break;
            case R.id.menuShare:
                break;
            case R.id.menuLogout:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // POPUP MENU
    private void ShowPopupMenu(){
        PopupMenu popupMenu = new PopupMenu(this, btnButton);
        popupMenu.getMenuInflater().inflate(R.menu.menu_setting,popupMenu.getMenu());
        //bắt sự kiện
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuSetting:
                        //lệnh
                        Toast.makeText(MainActivity.this,"Bạn đang chọn Setting",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menuShare:
                        btnButton.setText("Chia sẻ");
                        break;
                    case R.id.menuLogout:
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    // CONTEXT MENU
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_setting,menu);
        menu.setHeaderTitle("Context Menu");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //bắt sự kiện Context Menu
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSetting:
                //lệnh
                Toast.makeText(MainActivity.this,"Bạn đang chọn Setting",Toast.LENGTH_LONG).show();
                break;
            case R.id.menuShare:
                btnButton.setText("Chia sẻ");
                break;
            case R.id.menuLogout:
                break;
        }
        return super.onContextItemSelected(item);
    }

    // ALERT DIALOG
    private void XacNhanXoa( final int vitri) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Thông báo");
        alert.setMessage("Bạn có muốn đăng xuất không");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //lệnh nút không
            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    //tạo hàm Dialog
    private void DiaLog1(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        dialog.setCanceledOnTouchOutside(false);
        //ánh xạ
        EditText editText1 = (EditText) dialog.findViewById(R.id.editNumber1);
        //viết code sự kiện
        //bắt sự kiện Dialog
        dialog.show(); //hủy gọi dialog.dismiss();
    }

    // BÀI TẬP 1: TỰ ĐỘNG THAY ĐỔI NÊN CỦA APP (BACKGROUND TẠO NGẪU NHIÊN) KHI LOAD LẠI TRANG, DÙNG ONSTART,
    //@SuppressLint("ResourceType")
    @Override
    protected void onStart() {
        super.onStart();

        bg = (ConstraintLayout)
                findViewById(R.id.constraintLayout1);

        Random rd = new Random();

        // Cach 1: Dung mảng Array
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.drawable.bg1);
        arrayList.add(R.drawable.bg2);
        arrayList.add(R.drawable.bg3);
        arrayList.add(R.drawable.bg4);

        int vitri = rd.nextInt(arrayList.size());
        current_color = arrayList.get(vitri);
        bg.setBackgroundResource(current_color);

        // Cach 2: Tạo màu ngau nhien
        while (true) {
            current_color = 2131034145+rd.nextInt(100000);
            try {
                    bg.setBackgroundResource(current_color);
                    break;
                }
            catch (Exception e) {
                //  Block of code to handle errors
            }
        }
    }
}