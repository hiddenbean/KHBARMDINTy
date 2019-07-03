package com.hiddenbean.android.khbarmdinty;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.hiddenbean.android.khbarmdinty.adapters.LocationAdapter;
import com.hiddenbean.android.khbarmdinty.interfaces.Locations.GetLocationsInterface;
import com.hiddenbean.android.khbarmdinty.models.Location;
import com.hiddenbean.android.khbarmdinty.providers.RetrofitServiceProvider;
import com.hiddenbean.android.khbarmdinty.resources.LocationsResource;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetupLocationManuallyFragment extends Fragment {

    Toolbar toolbar;
    ListView listView;
    ArrayList<Location> locations = new ArrayList();
    LocationAdapter locationAdapter;
    ExtendedFloatingActionButton fab;
    ProgressBar progressBar;
    int selectedLocationPosition = -1;
    ImageView imageView;
    public static String selectedLocationName;
    Location currentLocation;
    Button current_location;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_setup_location_manually, container, false);

        toolbar = view.findViewById(R.id.setup_location_toolbar);
        ((SetupActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        listView = view.findViewById(R.id.locations_list);
        progressBar = view.findViewById(R.id.progress_bar);
        imageView = view.findViewById(R.id.network_problem);
        current_location = view.findViewById(R.id.current_location);

        imageView.setVisibility(View.INVISIBLE);

        fab = view.findViewById(R.id.fab);

        GetLocationsInterface getLocationsInterface = RetrofitServiceProvider.RETROFIT.create(GetLocationsInterface.class);

        final SharedPreferences globalPreferences = getActivity().getSharedPreferences("GlobalPrefrences", Context.MODE_PRIVATE);
        String userApiToken = globalPreferences.getString("user_api_token", "N/A");

        Call<LocationsResource> call = getLocationsInterface.getLocations("Bearer " + userApiToken);
        call.enqueue(new Callback<LocationsResource>() {
            @Override
            public void onResponse(Call<LocationsResource> call, Response<LocationsResource> response) {
                if(response.code() == 200){
                    LocationsResource locationsResource = response.body();
                    locations = locationsResource.getData();
                    locationAdapter = new LocationAdapter(view.getContext(), locations);
                    listView.setAdapter(locationAdapter);
                    progressBar.setVisibility(View.INVISIBLE);
                }else if(response.code() == 401) {
                    Toast.makeText(getContext(), "Non authentifié", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getContext(), FrontActivity.class));

                    SharedPreferences.Editor editor = globalPreferences.edit();
                    editor.remove("user_api_token");
                    editor.apply();

                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<LocationsResource> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);

                imageView.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Problème de connexion", Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                currentLocation = locations.get(position);

                if(selectedLocationPosition >= 0 && selectedLocationPosition < locations.size()) {
                    locations.get(selectedLocationPosition).setIsSelected(false);
                    currentLocation.setIsSelected(true);
                }

                selectedLocationName = currentLocation.getName();
                currentLocation.setIsSelected(true);
                locationAdapter.notifyDataSetChanged();

                selectedLocationPosition = position;

                fab.setText(" utiliser " + currentLocation.getName());
                fab.show();

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = globalPreferences.edit();
                editor.putLong("longitude", currentLocation.getLongitude());
                editor.putLong("latitude", currentLocation.getLatitude());
                editor.apply();

                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.setup_location_menu, menu);

        MenuItem search = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) search.getActionView();
        searchView.setQueryHint("Recherche");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                locationAdapter.getFilter().filter(newText);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}
