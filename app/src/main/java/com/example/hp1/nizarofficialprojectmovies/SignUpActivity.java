package com.example.hp1.nizarofficialprojectmovies;

import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.Manifest.permission.READ_CONTACTS;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "FIREBASE";
    Button btSignUp;
    EditText etFirstName, etLastName, etEmailSignUp, etCreatePassword, etConfirmPassword;
    TextView tvSignUp;


    private AutoCompleteTextView mEmailView;
    private static final int REQUEST_READ_CONTACTS = 0;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmailSignUp = findViewById(R.id.etEmailSignUp);
        etCreatePassword = findViewById(R.id.etCreatePassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        tvSignUp = findViewById(R.id.tvSignUp);

        mAuth = FirebaseAuth.getInstance();

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        btSignUp = (Button) findViewById(R.id.btSignUp);
        btSignUp.setOnClickListener(this);
    }

    private void populateAutoComplete() {
    }

    public void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUser:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(i);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUser:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v == btSignUp) {
            String user = etEmailSignUp.getText().toString();
            String password = etCreatePassword.getText().toString();
            if (user.equals("")&&password.equals("")) {

            } else {
                createUser(user, password);

            }
        }
    }
}

