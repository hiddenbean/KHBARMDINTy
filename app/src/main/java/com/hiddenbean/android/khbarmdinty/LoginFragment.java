package com.hiddenbean.android.khbarmdinty;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hiddenbean.android.khbarmdinty.interfaces.Auth.LoginUserInterface;
import com.hiddenbean.android.khbarmdinty.models.User;
import com.hiddenbean.android.khbarmdinty.providers.RetrofitServiceProvider;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    Button accessRecoveryButton, singinButton, facebookButton;
    TextInputEditText login, password;
    TextInputLayout login_layout,password_layout;

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
                Call<User> call = loginUserInterface.loginUser(login.getText().toString(), password.getText().toString());

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        switch (response.code())
                        {
                            case 200 :
                                User user  = response.body();
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                startActivity(intent);
                                break;
                            case 422 :
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
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                                break;
                            case 400 :
                                Toast.makeText(getContext(), "Vérifier le mot de passe", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Veuillez réessayer ultérieurement", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
