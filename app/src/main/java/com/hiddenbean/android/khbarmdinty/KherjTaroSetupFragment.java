package com.hiddenbean.android.khbarmdinty;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class KherjTaroSetupFragment extends Fragment {

    Button activeButton, moreDtailsButton, declineButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kherj_taro_setup, container, false);

        activeButton = view.findViewById(R.id.active);
        moreDtailsButton = view.findViewById(R.id.plus);
        declineButton = view.findViewById(R.id.decline);

        activeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    new MaterialAlertDialogBuilder(getContext(), R.style.alertdialog)
                            .setTitle("Service de Localisation")
                            .setMessage("Le service Kherj Taro necessite la détéction automatique de votre position.\nAutoriser le service sur Paramètres > Permissions > Localisation.")
                            .setPositiveButton("parameters", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
                                    intent.setData(uri);
                                    getContext().startActivity(intent);
                                }
                            })
                            .show();
                }
                else {
                    SharedPreferences.Editor editor = KherjTaroActivity.GLOBAL_PREFERENCES.edit();
                    editor.putBoolean("auto_localization", true);
                    editor.putInt("kherj_taro", 1);
                    editor.apply();
                    KherjTaroActivity.FRAGMENT_MANAGER
                            .beginTransaction()
                            .replace(R.id.fragments_container, new KherjTaroSetupDistanceFragment(), "SETUP_DISTANCE_KHERJ_TARO")
                            .commit();
                }
            }
        });

        moreDtailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KherjTaroActivity.FRAGMENT_MANAGER
                        .beginTransaction()
                        .addToBackStack("BACK_TO_SETUP_KHERJ_TARO_FROM_MORE_DETAILS_KHERJ_TARO")
                        .replace(R.id.fragments_container, new KherjTaroMoreDetailsFragment(), "MORE_DETAILS_KHERJ_TARO")
                        .commit();
            }
        });

        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = KherjTaroActivity.GLOBAL_PREFERENCES.edit();
                editor.putInt("kherj_taro", 0);
                editor.apply();
                getActivity().finish();

            }
        });

        return view;
    }
}
