package com.hiddenbean.android.khbarmdinty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class KherjTaroSetupDistanceFragment extends Fragment {

    Button registerButton;
    CheckBox smsCheckBox;
    TextInputEditText minutesInput;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kherj_taro_setup_distance, container, false);

        registerButton = view.findViewById(R.id.register);
        smsCheckBox = view.findViewById(R.id.sms);
        minutesInput = view.findViewById(R.id.minutes);

        final int minutesInputValue = Integer.valueOf(minutesInput.getText().toString());

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(smsCheckBox.isChecked()) {
                    SharedPreferences.Editor editor = KherjTaroActivity.GLOBAL_PREFERENCES.edit();
                    editor.putBoolean("kherj_taro_phone", true);
                    editor.putInt("tkherj_taro_time", minutesInputValue);
                    editor.apply();

                    KherjTaroActivity.FRAGMENT_MANAGER
                            .beginTransaction()
                            .addToBackStack("BACK_TO_SETUP_DISTANCE_KHERJ_TARO_FROM_SETUP_DISTANCE_PHONE_KHERJ_TARO")
                            .replace(R.id.fragments_container, new KherjTaroSetupDistancePhoneFragment(), "SETUP_DISTANCE_PHONE_KHERJ_TARO")
                            .commit();
                }
                else {
                    SharedPreferences.Editor editor = KherjTaroActivity.GLOBAL_PREFERENCES.edit();
                    editor.putBoolean("kherj_taro_phone", false);
                    editor.putInt("tkherj_taro_time", minutesInputValue);
                    editor.apply();
                    getActivity().finish();
                }
            }
        });
        return view;
    }
}
