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
import android.widget.EditText;

import com.example.lab_5_1.R;



public class UserDetails_ItemsAddAndRemove extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details__items_add_and_remove, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserListViewModel viewModel = new ViewModelProvider(requireActivity()).get(UserListViewModel.class);
        int user_id = getArguments().getInt("ownerId");

        RecyclerView recyclerItem = view.findViewById(R.id.recyclerView2);
        ItemAdapter itemAdapter = new ItemAdapter(item -> { viewModel.removeItem(item);});
        recyclerItem.setAdapter(itemAdapter);
        recyclerItem.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.getItems(user_id).observe(getViewLifecycleOwner(), items -> {itemAdapter.submitList(items);});

        Button addItemBtn = view.findViewById(R.id.addItemBtn);
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                EditText inputItemName = view.findViewById(R.id.itemNameInput);
                String itemName = inputItemName.getText().toString();
                viewModel.addItem(itemName, user_id);
            }
        });
    }
}