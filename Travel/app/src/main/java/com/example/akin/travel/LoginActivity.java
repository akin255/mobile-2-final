package com.example.akin.travel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_login;
    private EditText input_email;
    private EditText input_password;
    private TextView tv_reset;
    private ImageView img;
    private TextView tv,reset;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    String emailAddress = "user@example.com";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String DummyEmail = "Dummy@gmail.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth=FirebaseAuth.getInstance();
        btn_login=(Button)findViewById(R.id.btn_login);
        reset=(TextView)findViewById(R.id.link_resetpassword);
        mAuth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuthListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        if (firebaseAuth.getCurrentUser() == null) {
                            PassResetViaEmail();
                            Toast.makeText(getApplicationContext(),"Şifre yenilemek için mail gönderildi...",Toast.LENGTH_SHORT).show();
                        }
                    }};
                Toast.makeText(getApplicationContext(),"Şifre yenilemek için mail gönderildi...",Toast.LENGTH_SHORT).show();



            }
        });
        input_email=(EditText)findViewById(R.id.input_email);
        input_password=(EditText)findViewById(R.id.input_password);
        progressDialog=new ProgressDialog(this);

        tv=(TextView)findViewById(R.id.link_sign_up);
        tv_reset=(TextView)findViewById(R.id.link_resetpassword);
        btn_login.setOnClickListener(this);
        tv.setOnClickListener(this);





    }

    private void PassResetViaEmail() {
        if(mAuth != null) {
            Log.w(" if Email authenticated", "Recovery Email has been  sent to " + DummyEmail);
            mAuth.sendPasswordResetEmail(DummyEmail);
            Toast.makeText(getApplicationContext(),"Şifre yenilemek için mail gönderildi...",Toast.LENGTH_SHORT).show();
        } else {
            Log.w(" error ", " bad entry ");
        }
    }

    private void userLogin(){
        String email=input_email.getText().toString().trim();
        String password=input_password.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Lütfen e-mail giriniz", Toast.LENGTH_SHORT).show();
            //stop the function
            return;
        }

        if(TextUtils.isEmpty(password)){
            // pasword is empty
            Toast.makeText(this, "Lütfen şifre giriniz!", Toast.LENGTH_SHORT).show();
            // stop the fuction
            return;
        }



        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //start the profile activity
                            progressDialog.setMessage("Hoşgeldin ...");
                            progressDialog.show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),Profil.class));
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Hatalı e-mail veya şifre girdiniz!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View v) {
        if(v==btn_login){
            userLogin();
        }
        if(v==tv){
            finish();
            startActivity(new Intent(this,MainActivity.class));

        }


    }
}
