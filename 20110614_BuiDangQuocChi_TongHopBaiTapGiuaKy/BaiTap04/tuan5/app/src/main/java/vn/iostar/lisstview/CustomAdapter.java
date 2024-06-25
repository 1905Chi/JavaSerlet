package vn.iostar.lisstview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuan5.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Object> mObject;
    public static final int TEXT = 0;
    public static final int IMAGE = 1;
    public static final int USER = 2;

    public CustomAdapter(Context mContext, List<Object> mObject) {
        this.mContext = mContext;
        this.mObject = mObject;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class TextViewHolder extends RecyclerView.ViewHolder {
        private TextView tvText;
        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(com.example.tuan5.R.id.tv_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mObject.get(getAdapterPosition()).toString(), Toast.LENGTH_LONG);
                }
            });
        }
    }
}
