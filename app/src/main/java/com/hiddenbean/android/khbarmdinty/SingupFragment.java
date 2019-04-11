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
import com.hiddenbean.android.khbarmdinty.interfaces.Auth.RegisterUserInterface;
import com.hiddenbean.android.khbarmdinty.models.User;
import com.hiddenbean.android.khbarmdinty.providers.RetrofitServiceProvider;

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

        //Retrofit boot
        final RegisterUserInterface registerUserInterface = RetrofitServiceProvider.RETROFIT.create(RegisterUserInterface.class);

        //Register Button
        singupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<User> call = registerUserInterface.registerUser(first_name.getText().toString(), last_name.getText().toString(), login.getText().toString(), password.getText().toString());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        switch (response.code())
                        {
                            case 201 :
                                User user = response.body();
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                startActivity(intent);
                                break;
                            case 422 :
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
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getContext(), "Error : " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "veuillez réessayer ultérieurement", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
