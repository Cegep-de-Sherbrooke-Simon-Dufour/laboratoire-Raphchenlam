package com.example.lab_5_1.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_5_1.R;
import com.example.lab_5_1.data.User;

public class CustomAdapter extends ListAdapter<User, CustomAdapter.UserViewHolder> {

    private RecyclerCallback<User> callbackedUser = (u)->{};

    public void setCallbackedUser(RecyclerCallback<User> whatever){
        this.callbackedUser = whatever;
    }
    public CustomAdapter(){
        super(new DiffUtil.ItemCallback<User>() {
            @Override
            public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem.get_name().equals(newItem.get_name()) && oldItem.get_email().equals(newItem.get_email());
            }
        });
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_item, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder viewHolder, final int position){
        viewHolder.setText(getItem(position));
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView_name;
        private final TextView textView_email;

        private User user;
        public UserViewHolder(View view){
            super(view);
            textView_name = view.findViewById(R.id.name);
            textView_email = view.findViewById(R.id.email);
            view.setOnClickListener(v -> {
                callbackedUser.returnValue(user);
            });
        }

        public void setText(User user){
            textView_name.setText(user.get_name());
            textView_email.setText(user.get_email());
            this.user = user;
        }
    }
}
