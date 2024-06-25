package vn.iotstar.socketexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class ControlActivity extends AppCompatActivity {
    ImageButton btnTb1, btnTb2, btnDis;
    TextView tvTb, tvMac;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    Set<BluetoothDevice> pairedDevices1;
    String address = null;
    private ProgressDialog progressDialog;
    int flaglamp1;
    int flaglamp2;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent newIntent = getIntent();
        address = newIntent.getStringExtra(MainActivity.EXTRA_ADDRESS);
        setContentView(R.layout.activity_control);
        AnhXa();
        connect();

    }
    private void AnhXa(){
        btnTb1 = (ImageButton) findViewById(R.id.btnTB1);
        btnTb2 = (ImageButton) findViewById(R.id.btnTB2);
        tvTb = (TextView) findViewById(R.id.textView3);
        tvMac = (TextView) findViewById(R.id.textView2);
        btnDis = (ImageButton) findViewById(R.id.btnConnet);
    }
    private void connect(){
        new ConnectBT().execute();
        btnTb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thietTbi1();
            }
        });
        btnTb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thietTbi7();
            }
        });
        btnDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Disconnect();
            }
        });
    }

    private void Disconnect() {
        if (btSocket != null){
            try{
                btSocket.close();
            }catch (IOException e){
                msg("Loi");
            }
        }
        finish();
    }

    private void thietTbi1(){
        if (btSocket != null){
            try{
                if (this.flaglamp1 == 0){
                    this.flaglamp1 = 1;
                    this.btnTb1.setBackgroundResource(R.drawable.btnon);
                    btSocket.getOutputStream().write("1".toString().getBytes());
                    tvTb.setText("Thiet bi so 1 dang bat");
                }
                else{
                    if (this.flaglamp1 != 1) return;
                    {
                        this.flaglamp1 = 0;
                        this.btnTb1.setBackgroundResource(R.drawable.btnoff);
                        btSocket.getOutputStream().write("A".toString().getBytes());
                        tvTb.setText("Thiet bi so 1 dang tat");
                    }
                }
            }catch (IOException e){
                msg("Loi");
            }
        }
    }
    private void thietTbi7(){
        if (btSocket != null){
            try{
                if (this.flaglamp2 == 0){
                    this.flaglamp2 = 1;
                    this.btnTb2.setBackgroundResource(R.drawable.btnon);
                    btSocket.getOutputStream().write("7".toString().getBytes());
                    tvTb.setText("Thiet bi so 2 dang bat");
                }
                else{
                    if (this.flaglamp2 != 1) return;
                    {
                        this.flaglamp2 = 0;
                        this.btnTb2.setBackgroundResource(R.drawable.btnoff);
                        btSocket.getOutputStream().write("G".toString().getBytes());
                        tvTb.setText("Thiet bi so 2 dang tat");
                    }
                }
            }catch (IOException e){
                msg("Loi");
            }
        }
    }
    private class ConnectBT extends AsyncTask<Void, Void, Void>{
        private boolean ConnectSuccess = true;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(ControlActivity.this, "Dang ket noi...", "Xin vui long doi");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                if (btSocket == null || !isBtConnected){
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();

                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
                    if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED){
                        btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                        btSocket.connect();
                    }
                }
            }
            catch (IOException e){
                ConnectSuccess = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (!ConnectSuccess){
                msg("Ket noi that bai! Kiem tra thiet bi");
                finish();
            }
            else{
                msg("Ket noi thanh cong.");
                isBtConnected = true;
                pairedDevicesList1();
            }
            progressDialog.dismiss();
        }
    }
    private void msg(String s){
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
    private void pairedDevicesList1(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED){
            pairedDevices1 = myBluetooth.getBondedDevices();

            if (pairedDevices1.size() > 0){
                for (BluetoothDevice bt: pairedDevices1){
                    tvMac.setText(bt.getName() + " - " + bt.getAddress());
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "Khong tim that thiet bi ket noi", Toast.LENGTH_SHORT).show();
            }
        }
    }

}