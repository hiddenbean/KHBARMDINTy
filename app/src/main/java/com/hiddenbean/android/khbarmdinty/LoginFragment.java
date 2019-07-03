package com.hiddenbean.android.khbarmdinty;

import android.app.ProgressDialog;
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
import com.hiddenbean.android.khbarmdinty.interfaces.Auth.LoginUserInterface;
import com.hiddenbean.android.khbarmdinty.models.User;
import com.hiddenbean.android.khbarmdinty.providers.RetrofitServiceProvider;
import com.hiddenbean.android.khbarmdinty.resources.UserResource;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    Button accessRecoveryButton, singinButton, facebookButton;
    TextInputEditText login, password;
    TextInputLayout login_layout,password_layout;
    ProgressBar progress_bar;
    LinearLayout layout_semi_transparent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        getActivity().setTitle(R.string.login_title);
        ((FrontActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        accessRecoveryButton = view.findViewById(R.id.access_recovery);
        singinButton = view.findViewById(R.id.singin);
        facebookButton = view.findViewById(R.id.facebook_button);
        login = view.findViewById(R.id.login);
        login_layout = view.findViewById(R.id.login_layout);
        password = view.findViewById(R.id.password);
        password_layout = view.findViewById(R.id.password_layout);
        progress_bar = getActivity().findViewById(R.id.progress_bar);
        layout_semi_transparent = view.findViewById(R.id.layout_semi_transparent);

        final LoginUserInterface loginUserInterface = RetrofitServiceProvider.RETROFIT.create(LoginUserInterface.class);

        //show access recovery fragment
        accessRecoveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrontActivity
                        .fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragments_container, new AccessRecoveryFragment(), "Access recovery")
                        .addToBackStack("BACK_TO_LOGIN_FROM_ACCESS_RECOVERY")
                        .commit();
            }
        });

        //try to login
        singinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress_bar.setVisibility(View.VISIBLE);
                layout_semi_transparent.setVisibility(View.VISIBLE);

                getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                Call<UserResource> call = loginUserInterface.loginUser(login.getText().toString(), password.getText().toString());

                call.enqueue(new Callback<UserResource>() {
                    @Override
                    public void onResponse(Call<UserResource> call, Response<UserResource> response) {
                        switch (response.code())
                        {
                            case 200 :
                                UserResource userResource  = response.body();
                                User user = userResource.getData();
                                SharedPreferences sharedPref = getActivity().getSharedPreferences("GlobalPrefrences", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("user_email", user.getEmail());
                                editor.putString("user_first_name", user.getFirst_name());
                                editor.putString("user_last_name", user.getLast_name());
                                editor.putString("user_api_token", user.getApi_token());
                                editor.apply();

                                int location = sharedPref.getInt("user_location", 0);

                                if(location == 0) {
                                    startActivity(new Intent(getContext(), SetupActivity.class));
                                    getActivity().finish();
                                }
                                else {
                                    startActivity(new Intent(getContext(), MainActivity.class));
                                    getActivity().finish();
                                }

                                break;
                            case 400 :
                                progress_bar.setVisibility(View.INVISIBLE);
                                layout_semi_transparent.setVisibility(View.GONE);

                                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                Toast.makeText(getContext(), "Vérifier le mot de passe", Toast.LENGTH_LONG).show();
                                break;
                            case 422 :
                                progress_bar.setVisibility(View.INVISIBLE);
                                layout_semi_transparent.setVisibility(View.GONE);

                                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    //Login validation messages
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
                                    progress_bar.setVisibility(View.INVISIBLE);
                                    layout_semi_transparent.setVisibility(View.GONE);

                                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                                    Toast.makeText(getContext(), "Parser error : " + e.getMessage(), Toast.LENGTH_LONG).show();
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
                progress_bar.setVisibility(View.INVISIBLE);
                layout_semi_transparent.setVisibility(View.GONE);

                Toast.makeText(getContext(), "Problème de connexion", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
