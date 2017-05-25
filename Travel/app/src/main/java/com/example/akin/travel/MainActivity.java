package com.example.akin.travel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textVievSignin;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private EditText editTextName;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();


        progressDialog=new ProgressDialog(this);

        buttonRegister=(Button)findViewById(R.id.btn_register);

        editTextEmail=(EditText)findViewById(R.id.input_email);
        editTextPassword=(EditText)findViewById(R.id.input_password);
        editTextName=(EditText)findViewById(R.id.input_name);
        textVievSignin=(TextView)findViewById(R.id.link_sign_in);
        buttonRegister.setOnClickListener(this);
        textVievSignin.setOnClickListener(this);



    }




    private void registeruser(){
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Lütfen e-mail adresini giriniz", Toast.LENGTH_SHORT).show();
            //stop the function
            return;
        }

        if(TextUtils.isEmpty(password)){
            // pasword is empty
            Toast.makeText(this, "lütfen şifre giriniz", Toast.LENGTH_SHORT).show();
            // stop the fuction
            return;
        }

        progressDialog.setMessage("Lütfen bekleyiniz ...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            finish();
                            Toast.makeText(MainActivity.this, "Kayıt işlemi başarıyla oluşturuldu", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));



                        }else {
                            Toast.makeText(MainActivity.this, "E-mail veya şifre tipi hatalı. Lütfen tekrar deneyin...", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view==buttonRegister){
            registeruser();
            // saveUserInformation();


        }
        if(view==textVievSignin){
            // will open login activity here
            startActivity(new Intent(this,LoginActivity.class));

        }
    }
}
