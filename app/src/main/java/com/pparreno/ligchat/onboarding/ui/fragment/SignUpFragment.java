package com.pparreno.ligchat.onboarding.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.pparreno.ligchat.R;
import com.pparreno.ligchat.chatroom.ui.activity.ChatRoomActivity;
import com.pparreno.ligchat.onboarding.viewmodel.SignUpViewModel;
import com.pparreno.ligchat.utils.FieldValidator;

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

    private SignUpFragmentListener signUpFragmentListener;

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
        TextView navigateToLoginText = v.findViewById(R.id.login_text);
        navigateToLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNavigateToSignInTextClick();
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signUpFragmentListener = (SignUpFragmentListener) this.getActivity();
        progressHUD = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getString(R.string.please_wait))
                .setDetailsLabel(getString(R.string.registering))
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
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

    public void onNavigateToSignInTextClick() {
        if (signUpFragmentListener != null) {
            signUpFragmentListener.onSignInTextClicked();
        }
    }

    public interface SignUpFragmentListener {
        void onSignInTextClicked();
    }

}