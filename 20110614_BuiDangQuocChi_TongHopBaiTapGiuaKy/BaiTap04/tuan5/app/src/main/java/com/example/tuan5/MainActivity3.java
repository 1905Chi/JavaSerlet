package com.example.tuan5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tuan5.R;

import java.util.ArrayList;
import java.util.List;

import vn.iostar.lisstview.SongAdapter;
import vn.iostar.lisstview.SongModel;

public class MainActivity3 extends AppCompatActivity {
    private RecyclerView rvSongs;
    private SongAdapter nSongAdapter;
    private List<SongModel> nSongs;
    RecyclerView itemView;
    SongModel mSongs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.tuan5.R.layout.activity_main3);
        itemView = (RecyclerView) findViewById(R.id.rv_songs) ;
        //thiết lập sự kiện
      /*  itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SongModel song = mSongs.get(getAdapterPosition());
                Toast.makeText(mContext, song.getmTitle(),
                        Toast.LENGTH_SHORT).show();
            }
        });*/

        rvSongs= (RecyclerView) findViewById(R.id.rv_songs);
        //create song data
        nSongs= new ArrayList<>();
        nSongs.add(new SongModel("12","nếu em còn tồn tại","Khi anh bắt đầu một tình ","Trịnh Đình Quang"));
        nSongs.add(new SongModel("13","Em của ngày hôm qua","Tìm lại em của ngày hôm qua ","Sơn Tùng MTP"));
        nSongs.add(new SongModel("11","nếu em còn tồn tại","Khi anh bắt đầu một tình ","Trịnh Đình Quang"));
        nSongs.add(new SongModel("13","Em của ngày hôm qua","Tìm lại em của ngày hôm qua ","Sơn Tùng MTP"));
        nSongs.add(new SongModel("15","nếu em còn tồn tại","Khi anh bắt đầu một tình ","Trịnh Đình Quang"));
        nSongAdapter = new SongAdapter(this,nSongs);
        rvSongs.setAdapter(nSongAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvSongs.setLayoutManager(linearLayoutManager);

    }

}