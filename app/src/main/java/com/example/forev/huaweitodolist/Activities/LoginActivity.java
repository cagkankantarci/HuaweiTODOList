package com.example.forev.huaweitodolist.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forev.huaweitodolist.Models.LoginModel;
import com.example.forev.huaweitodolist.R;
import com.example.forev.huaweitodolist.RestApi.ManagerAll;
import com.example.forev.huaweitodolist.Utils.GetSharedPreferences;
import com.example.forev.huaweitodolist.Utils.Warning;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail,loginPassword;
    private TextView loginText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        defineLayout();
        click();
        delete();
    }

    public void defineLayout()
    {
        loginEmail = (EditText)findViewById(R.id.loginEmail);
        loginPassword = (EditText)findViewById(R.id.loginPassword);
        loginText = (TextView)findViewById(R.id.loginText);
        loginButton = (Button)findViewById(R.id.loginButton);
    }

    public void click()
    {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();
                if(!email.equals("") && !password.equals(""))
                {
                    login(email,password);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"You have entered incomplete information, please try again.",Toast.LENGTH_LONG).show();
                }
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void delete()
    {
        loginEmail.setText("");
        loginPassword.setText("");
    }

    public void login(String email,String password)
    {
        Call<LoginModel> req = ManagerAll.getInstance().loginUser(email,password);
        req.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.body().isTf())
                {
                    Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    GetSharedPreferences getSharedPreferences = new GetSharedPreferences(LoginActivity.this);
                    getSharedPreferences.setSession(response.body().getId(),response.body().getUsername(),response.body().getEmail());
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),Warning.internetProblemText,Toast.LENGTH_LONG).show();
            }
        });
    }
}
