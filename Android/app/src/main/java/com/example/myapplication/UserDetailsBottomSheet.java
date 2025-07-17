package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class UserDetailsBottomSheet extends BottomSheetDialogFragment {
    private String userDetails;

    public UserDetailsBottomSheet(String userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_details_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvUserDetails = view.findViewById(R.id.tv_user_details);
        tvUserDetails.setText(userDetails);
    }
}
