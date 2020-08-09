package com.pparreno.ligchat.onboarding.ui.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.pparreno.ligchat.R;
import com.pparreno.ligchat.chatroom.ui.activity.ChatRoomActivity;
import com.pparreno.ligchat.onboarding.viewmodel.SignUpViewModel;
import com.pparreno.ligchat.utils.FieldValidator;

import java.lang.reflect.Field;

public class SignUpFragment extends Fragment {

    private SignUpViewModel mViewModel;
    private KProgressHUD progressHUD;

    private Button registerButton;
    private EditText emailField;
    private EditText passwordField;
    private EditText usernameField;

    private TextView usernameErrorText;
    private TextView passwordErrorText;
    private TextView emailErrorText;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sign_up_fragment, container, false);
        this.registerButton = v.findViewById(R.id.signup_btn);

        this.emailField = v.findViewById(R.id.email_field);
        this.passwordField = v.findViewById(R.id.password_field);
        this.usernameField = v.findViewById(R.id.username_field);

        this.usernameErrorText = v.findViewById(R.id.username_error_text);
        this.emailErrorText = v.findViewById(R.id.email_error_text);
        this.passwordErrorText = v.findViewById(R.id.password_error_text);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Registering")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        mViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        this.mViewModel.setLifecycleOwner(getViewLifecycleOwner());
        this.mViewModel.isRegistered.observe(getViewLifecycleOwner(), isRegistered -> {
            if (isRegistered) {
                navigateToChatRoom();
            } else {
                Toast.makeText(SignUpFragment.this.getContext(), "Registration Error", Toast.LENGTH_SHORT).show();
            }
        });
        this.mViewModel.flagAuthProcessOnGoing.observe(getViewLifecycleOwner(), flagAuthProcessOnGoing -> {
            if (flagAuthProcessOnGoing) {
                progressHUD.show();
            } else {
                progressHUD.dismiss();
            }
        });
        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterButtonClick();
            }
        });
    }

    private void navigateToChatRoom() {
        Intent intent = new Intent(getContext(), ChatRoomActivity.class);
        startActivity(intent);
    }

    public void onRegisterButtonClick() {
        if (validateFields()) {
            this.mViewModel.registerUser(usernameField.getText().toString(),
                    emailField.getText().toString(),
                    passwordField.getText().toString());
        }
    }

    private boolean validateFields() {
        emailErrorText.setVisibility(View.GONE);
        usernameErrorText.setVisibility(View.GONE);
        passwordErrorText.setVisibility(View.GONE);

        boolean isValidEmail = FieldValidator.validateEmailString(emailField.getText().toString());
        boolean isValidUsername = FieldValidator.validateUserName(usernameField.getText().toString());
        boolean isValidPassword = FieldValidator.validatePassword(passwordField.getText().toString());

        if (isValidEmail && isValidPassword && isValidUsername) {
            return true;
        } else {

            if (!isValidEmail) {
                emailErrorText.setVisibility(View.VISIBLE);
            }
            if (!isValidUsername) {
                usernameErrorText.setVisibility(View.VISIBLE);
            }
            if (!isValidPassword) {
                passwordErrorText.setVisibility(View.VISIBLE);
            }

            return false;
        }
    }

}