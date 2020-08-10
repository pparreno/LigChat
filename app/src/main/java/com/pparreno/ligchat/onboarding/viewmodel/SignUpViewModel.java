package com.pparreno.ligchat.onboarding.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.pparreno.ligchat.onboarding.repository.SignUpRepository;

public class SignUpViewModel extends ViewModel {
    private LifecycleOwner lifecycleOwner;

    private SignUpRepository signUpRepository;

    public MutableLiveData<Boolean> isRegistered;
    public MutableLiveData<Boolean> flagAuthProcessOnGoing;

    public SignUpViewModel() {
        this.signUpRepository = new SignUpRepository();
        this.isRegistered = new MutableLiveData<>();
        this.flagAuthProcessOnGoing = new MutableLiveData<>();
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    public void registerUser(@NonNull String username, @NonNull String email, @NonNull String password)
    {
        this.flagAuthProcessOnGoing.postValue(true);
        this.signUpRepository.registerUser(email, password, username).observe(
                lifecycleOwner, new Observer<FirebaseUser>() {
                    @Override
                    public void onChanged(FirebaseUser firebaseUser) {
                        if(firebaseUser != null){
                            isRegistered.postValue(true);
                        } else {
                            isRegistered.postValue(false);
                        }
                        flagAuthProcessOnGoing.postValue(false);
                    }
                }
        );
    }

}