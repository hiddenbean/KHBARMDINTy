package com.hiddenbean.android.khbarmdinty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class KherjTaroSetupDistancePhoneVerificationFragment extends Fragment {

    TextInputEditText codeInput;
    TextView text;
    Button valideButton, editPhoneButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kherj_taro_setup_distance_phone_verification, container, false);

        codeInput = view.findViewById(R.id.phone_layout);
        text = view.findViewById(R.id.text);
        editPhoneButton = view.findViewById(R.id.edit_phone);

        String phoneNumber = KherjTaroActivity.GLOBAL_PREFERENCES.getString("phone_number_code", "") + " " +KherjTaroActivity.GLOBAL_PREFERENCES.getString("phone_number", "");
        text.setText("Un SMS contenant un code de validation à six chiffres a été envoyé au " + phoneNumber);

        editPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KherjTaroActivity.FRAGMENT_MANAGER
                        .beginTransaction()
                        .replace(R.id.fragments_container, new KherjTaroSetupDistancePhoneFragment(), "SETUP_DISTANCE_PHONE_KHERJ_TARO")
                        .commit();
            }
        });

        return view;
    }
}
