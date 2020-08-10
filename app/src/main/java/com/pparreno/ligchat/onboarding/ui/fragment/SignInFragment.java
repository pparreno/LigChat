package com.pparreno.ligchat.onboarding.ui.fragment;

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
import com.pparreno.ligchat.onboarding.viewmodel.SignInViewModel;
import com.pparreno.ligchat.onboarding.viewmodel.SignUpViewModel;
import com.pparreno.ligchat.utils.FieldValidator;

public class SignInFragment extends Fragment {

    private SignInViewModel mViewModel;
    private KProgressHUD progressHUD;

    private Button loginButton;
    private EditText emailField;
    private EditText passwordField;

    private TextView passwordErrorText;
    private TextView emailErrorText;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sign_in_fragment, container, false);
        this.loginButton = v.findViewById(R.id.login_btn);

        this.emailField = v.findViewById(R.id.email_field);
        this.passwordField = v.findViewById(R.id.password_field);

        this.emailErrorText = v.findViewById(R.id.email_error_text);
        this.passwordErrorText = v.findViewById(R.id.password_error_text);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SignInViewModel.class);
        progressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getString(R.string.please_wait))
                .setDetailsLabel(getString(R.string.signing_in))
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        this.mViewModel.setLifecycleOwner(getViewLifecycleOwner());
        this.mViewModel.isAuthenticated.observe(getViewLifecycleOwner(), isAuthenticated -> {
            if (isAuthenticated) {
                navigateToChatRoom();
            } else {
                Toast.makeText(SignInFragment.this.getContext(), "Authentication Error", Toast.LENGTH_SHORT).show();
            }
        });
        this.mViewModel.flagAuthProcessOnGoing.observe(getViewLifecycleOwner(), flagAuthProcessOnGoing -> {
            if (flagAuthProcessOnGoing) {
                progressHUD.show();
            } else {
                progressHUD.dismiss();
            }
        });
        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginButtonClick();
            }
        });
    }

    private void navigateToChatRoom() {
        Intent intent = new Intent(getContext(), ChatRoomActivity.class);
        startActivity(intent);
    }

    public void onLoginButtonClick() {
        if (validateFields()) {
            this.mViewModel.signInUser(emailField.getText().toString(),
                    passwordField.getText().toString());
        }
    }

    private boolean validateFields() {
        emailErrorText.setVisibility(View.GONE);
        passwordErrorText.setVisibility(View.GONE);

        boolean isValidEmail = FieldValidator.validateEmailString(emailField.getText().toString());
        boolean isValidPassword = FieldValidator.validatePassword(passwordField.getText().toString());

        if (isValidEmail && isValidPassword) {
            return true;
        } else {

            if (!isValidEmail) {
                emailErrorText.setVisibility(View.VISIBLE);
            }

            if (!isValidPassword) {
                passwordErrorText.setVisibility(View.VISIBLE);
            }

            return false;
        }
    }

}