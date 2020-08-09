package com.pparreno.ligchat.onboarding.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import timber.log.Timber;

public class SignUpRepository {

    private FirebaseAuth mAuth;

    public SignUpRepository() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    public MutableLiveData<FirebaseUser> registerUser(@NonNull String email, @NonNull String password, @NonNull final String username)
    {
        final MutableLiveData<FirebaseUser> mutableLiveDataFirebaseUser = new MutableLiveData<>();
        this.mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user = mAuth.getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(username).build();

                    assert user != null;
                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Timber.d("User profile updated.");
                                        mutableLiveDataFirebaseUser.postValue(mAuth.getCurrentUser());
                                    }
                                }
                            });
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
