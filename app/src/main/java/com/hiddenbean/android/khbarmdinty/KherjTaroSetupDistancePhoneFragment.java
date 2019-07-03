package com.hiddenbean.android.khbarmdinty;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class KherjTaroSetupDistancePhoneFragment extends Fragment {

    TextInputEditText phoneInput, codePhoneInput;
    Button valideButton;
    String phone,codePhone;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kherj_taro_setup_distance_phone, container, false);

        phoneInput = view.findViewById(R.id.phone);
        codePhoneInput = view.findViewById(R.id.code_phone);
        phone = KherjTaroActivity.GLOBAL_PREFERENCES.getString("phone_number", "");
        codePhone = KherjTaroActivity.GLOBAL_PREFERENCES.getString("phone_number_code", "");

        if(!phone.equals("") && !codePhone.equals("")) {
            phoneInput.setText(phone);
            codePhoneInput.setText(codePhone);
        }

        valideButton = view.findViewById(R.id.verify);

        valideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = KherjTaroActivity.GLOBAL_PREFERENCES.edit();
                editor.putString("phone_number", phoneInput.getText().toString());
                editor.putString("phone_number_code", codePhoneInput.getText().toString());
                editor.apply();

                KherjTaroActivity.FRAGMENT_MANAGER
                        .beginTransaction()
                        .replace(R.id.fragments_container, new KherjTaroSetupDistancePhoneVerificationFragment(), "SETUP_DISTANCE_PHONE_VERIFICATION_KHERJ_TARO")
                        .commit();
            }
        });

        return view;
    }
}
