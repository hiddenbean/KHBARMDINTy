package com.hiddenbean.android.khbarmdinty;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hiddenbean.android.khbarmdinty.interfaces.Auth.RegisterUserInterface;
import com.hiddenbean.android.khbarmdinty.models.User;
import com.hiddenbean.android.khbarmdinty.providers.RetrofitServiceProvider;
import com.hiddenbean.android.khbarmdinty.resources.UserResource;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingupFragment extends Fragment {

    Button singupButton, facebookButton;
    TextInputEditText first_name, last_name, login, password;
    TextInputLayout first_name_layout, last_name_layout, login_layout, password_layout;
    ProgressBar progress_bar;
    LinearLayout layout_semi_transparent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_singup, container, false);

        ((FrontActivity)getActivity()).setTitle(R.string.singup_title);
        ((FrontActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //buttons
        singupButton = view.findViewById(R.id.singup);
        facebookButton = view.findViewById(R.id.facebook_button);

        //Text inputs
        first_name = view.findViewById(R.id.first_name);
        last_name = view.findViewById(R.id.last_name);
        login = view.findViewById(R.id.login);
        password = view.findViewById(R.id.password);

        //input layouts
        first_name_layout = view.findViewById(R.id.first_name__layout);
        last_name_layout = view.findViewById(R.id.last_name_layout);
        login_layout = view.findViewById(R.id.login_layout);
        password_layout = view.findViewById(R.id.password_layout);
        progress_bar = getActivity().findViewById(R.id.progress_bar);
        layout_semi_transparent = view.findViewById(R.id.layout_semi_transparent);


        //Retrofit boot
        final RegisterUserInterface registerUserInterface = RetrofitServiceProvider.RETROFIT.create(RegisterUserInterface.class);

        //Register Button
        singupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress_bar.setVisibility(View.VISIBLE);
                layout_semi_transparent.setVisibility(View.VISIBLE);

                getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                Call<UserResource> call = registerUserInterface.registerUser(first_name.getText().toString(), last_name.getText().toString(), login.getText().toString(), password.getText().toString());
                call.enqueue(new Callback<UserResource>() {
                    @Override
                    public void onResponse(Call<UserResource> call, Response<UserResource> response) {

                        switch (response.code())
                        {
                            case 201 :
                                UserResource userResource  = response.body();
                                User user = userResource.getData();
                                SharedPreferences sharedPref = getActivity().getSharedPreferences("GlobalPrefrences", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("user_email", user.getEmail());
                                editor.putString("user_first_name", user.getFirst_name());
                                editor.putString("user_last_name", user.getLast_name());
                                editor.putString("user_api_token", user.getApi_token());
                                editor.apply();

                                Intent intent = new Intent(getContext(), MainActivity.class);
                                startActivity(intent);
                                break;
                            case 422 :
                                progress_bar.setVisibility(View.INVISIBLE);
                                layout_semi_transparent.setVisibility(View.GONE);

                                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    //First name validation messages
                                    if(!jObjError.getJSONObject("errors").isNull("first_name")) {
                                        first_name_layout.setError(jObjError.getJSONObject("errors").getJSONArray("first_name").get(0).toString());
                                    }
                                    else {
                                        first_name_layout.setError(null);
                                    }
                                    //Last name validation messages
                                    if(!jObjError.getJSONObject("errors").isNull("last_name")) {
                                        last_name_layout.setError(jObjError.getJSONObject("errors").getJSONArray("last_name").get(0).toString());
                                    }
                                    else {
                                        last_name_layout.setError(null);
                                    }
                                    //Email name validation messages
                                    if(!jObjError.getJSONObject("errors").isNull("email")) {
                                        login_layout.setError(jObjError.getJSONObject("errors").getJSONArray("email").get(0).toString());
                                    }
                                    else {
                                        login_layout.setError(null);
                                    }
                                    //Password validation messages
                                    if(!jObjError.getJSONObject("errors").isNull("password")) {
                                        password_layout.setError(jObjError.getJSONObject("errors").getJSONArray("password").get(0).toString());
                                    }
                                    else {
                                        password_layout.setError(null);
                                    }
                                } catch (Exception e) {
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResource> call, Throwable t) {
                        progress_bar.setVisibility(View.INVISIBLE);
                        layout_semi_transparent.setVisibility(View.GONE);

                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        Toast.makeText(getContext(), "Problème de connexion", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Problème de connexion", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
