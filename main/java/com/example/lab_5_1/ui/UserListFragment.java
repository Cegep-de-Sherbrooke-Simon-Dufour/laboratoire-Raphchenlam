package com.example.lab_5_1.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lab_5_1.R;
import com.example.lab_5_1.data.User;

import java.util.ArrayList;


public class UserListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        UserListViewModel viewModel = new ViewModelProvider(requireActivity()).get(UserListViewModel.class);

        //
        RecyclerView recycler = view.findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        CustomAdapter adapter = new CustomAdapter();
        recycler.setAdapter(adapter);
        //

        viewModel.getUsers().observe(getViewLifecycleOwner(), users -> {
            adapter.submitList(new ArrayList<>(users));
        });


        adapter.setCallbackedUser(new RecyclerCallback<User>() {
            @Override
            public void returnValue(User user) {
                Bundle bundle = new Bundle();
                bundle.putInt("ownerId", user.user_id);
                Navigation.findNavController(view).navigate(R.id.action_userListFragment_to_userDetails_ItemsAddAndRemove, bundle);
            }
        });

        Button addBtn = view.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_userListFragment_to_addUserFragment);
            }
        });
    }
}