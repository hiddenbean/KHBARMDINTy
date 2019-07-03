package com.hiddenbean.android.khbarmdinty;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class SetupLocationFragment extends Fragment {

    Button current_location, location_manually;
    LocationManager locationManager;
    LocationListener locationListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setup_location, container, false);

        current_location = view.findViewById(R.id.current_location);
        location_manually = view.findViewById(R.id.location_manually);

        location_manually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetupActivity.FRAGMENT_MANAGER
                        .beginTransaction()
                        .replace(R.id.fragments_container, new SetupLocationManuallyFragment(), "SETUP_LOCATION_MANUALLY")
                        .addToBackStack("BACK_TO_SETUP_LOCATION")
                        .commit();
            }
        });

        current_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    }, 101);
                }
                else {
                    SharedPreferences.Editor editor = SetupActivity.GLOBAL_PREFERENCES.edit();
                    editor.putBoolean("auto_localization", true);
                    editor.apply();

                    startActivity(new Intent(getContext(), MainActivity.class));
                    getActivity().finish();
                }
            }
        });

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(grantResults[0] != -1 && grantResults[1] != -1 ) {
            SharedPreferences.Editor editor = SetupActivity.GLOBAL_PREFERENCES.edit();
            editor.putBoolean("auto_localization", true);
            editor.apply();

            startActivity(new Intent(getContext(), MainActivity.class));
        }else if(grantResults[0] == -1 || grantResults[1] == -1) {
            new MaterialAlertDialogBuilder(getContext(), R.style.alertdialog)
                    .setTitle("Service de Localisation")
                    .setMessage("Permet nous d'avoire votre position pour te dire Htary au tour de vous.\nAutoriser le service sur Paramètres > Permissions > Localisation.")
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
    }
}
