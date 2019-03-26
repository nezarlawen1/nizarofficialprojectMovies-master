package com.example.hp1.nizarofficialprojectmovies;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    TextView tvSignin;
    Button btSignIn, btOrSignUp;
    EditText email, password;
    final String TAG = "FIREBASE";

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btSignIn = findViewById(R.id.btSignIn);
        btSignIn.setTextColor(Color.BLACK);
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(),MainActivity.class);
                startActivity(i);
            }
        });

        mAuth = FirebaseAuth.getInstance();


        email =(EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        tvSignin = (TextView) findViewById(R.id.tvSignin);

        btOrSignUp = findViewById(R.id.btOrSignUp);
        btOrSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplication(),SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    /**
     * check if the email and password are in the firebase or not
     * @param email
     * @param password
     */
    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    /**
     * checks if the text fields are empty
     * @param v
     */
    @Override
    public void onClick(View v) {
        //createUser
        if (v == btSignIn) {
            String Email = email.getText().toString();
            String Password = password.getText().toString();
            if (email.equals("")|| password.equals("")) {
                Toast.makeText(LoginActivity.this, "Email  Or Password Is Empty",
                        Toast.LENGTH_SHORT).show();
            } else {
                signIn(Email, Password);
            }

        }
    }

}

