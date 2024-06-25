package com.example.rombatabase.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rombatabase.R;
import com.example.rombatabase.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private List<User> usersList;
    private iClickListnener listnener;

    public UserAdapter(List<User> usersList, iClickListnener listnener) {
        this.usersList = usersList;
        this.listnener = listnener;
    }

    public UserAdapter() {
    }

    public  UserAdapter(iClickListnener listnener){
        this.listnener=listnener;
    }
    public void setData(List<User>list)
    {
        this.usersList=list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder,int position)
    {
        User uses= usersList.get(position);
        if(uses==null)
        {
            return;
        }
        holder.username.setText(uses.getUsername());
        holder.address.setText(uses.getAddress());

        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listnener.updateUser(uses);            }
        });
    }
    @Override
    public int getItemCount(){
        if(usersList != null)
        {
            return usersList.size();
        }
        return 0;
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        private TextView username;
        private TextView address;

        private Button btnupdate,btnxoa;
        public UserHolder(@NonNull View itemView)
        {
            super(itemView);
            username=itemView.findViewById(R.id.user);
            address=itemView.findViewById(R.id.addrss);
            btnupdate=itemView.findViewById(R.id.btnUpdate);
            btnxoa=itemView.findViewById(R.id.btnxoa);
        }
    }

}
