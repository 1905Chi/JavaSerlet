package vn.iotstar.socketexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    Button btnPaired;
    ListView lsDS;
    public static int REQUEST_BLUETOOTH = 1;
    //bluetooth
    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    public static String EXTRA_ADDRESS = "device_address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        checkBluetoohOn();
        setBtnPaired();
    }
    private void AnhXa(){
        btnPaired = (Button) findViewById(R.id.button);
        lsDS = (ListView) findViewById(R.id.lvDS);
    }
    private void checkBluetoohOn(){
        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        if (myBluetooth == null){
            Toast.makeText(getApplicationContext(), "Thiết bị chưa bật bluetooth", Toast.LENGTH_SHORT).show();
            finish();
        }
        else if(!myBluetooth.isEnabled()){
            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(), "Thiết bị chưa bật bluetooth", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getApplicationContext(), "Thiết bị đã bật bluetooth", Toast.LENGTH_SHORT).show();
            startActivityForResult(turnBTon, REQUEST_BLUETOOTH);
        }
    }
    private void setBtnPaired(){
        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pairedDevicesList();
            }
        });
    }
    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String info = ((TextView) view).getText().toString();
            String address = info.substring(info.length() - 17);

            Intent intent = new Intent(MainActivity.this, ControlActivity.class);

            intent.putExtra(EXTRA_ADDRESS, address);
            startActivity(intent);
        }
    };

    private void pairedDevicesList() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED){
            pairedDevices = myBluetooth.getBondedDevices();
            ArrayList list = new ArrayList();
            if (pairedDevices.size() > 0){
                for (BluetoothDevice bt: pairedDevices){
                    if(ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(getApplicationContext(), "Danh sách thiết bị Bluetooth đã bật", Toast.LENGTH_SHORT).show();
                        list.add(bt.getName() + "\n" + bt.getAddress());
                    }
                }
            }
            else{
                Toast.makeText(getApplicationContext(), "Không tìm thất thiết bị nào", Toast.LENGTH_SHORT).show();
            }
            final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
            lsDS.setAdapter(arrayAdapter);
            lsDS.setOnItemClickListener(myListClickListener);
        }
    }
}