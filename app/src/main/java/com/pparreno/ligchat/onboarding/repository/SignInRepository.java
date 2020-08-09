package com.pparreno.ligchat.onboarding.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import timber.log.Timber;

public class SignInRepository {

    private FirebaseAuth mAuth;

    public SignInRepository() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    public MutableLiveData<FirebaseUser> signInUser(@NonNull String email, @NonNull String password) {
        final MutableLiveData<FirebaseUser> mutableLiveDataFirebaseUser = new MutableLiveData<>();
        this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        mutableLiveDataFirebaseUser.postValue(user);
                    } else {
                        mutableLiveDataFirebaseUser.postValue(null);
                    }

                } else {
                    mutableLiveDataFirebaseUser.postValue(null);
                }
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mutableLiveDataFirebaseUser.postValue(null);
            }
        });
        return mutableLiveDataFirebaseUser;
    }
}
