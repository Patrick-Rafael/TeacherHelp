package com.example.teacherhelp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teacherhelp.R;
import com.example.teacherhelp.configFireBase.ConfiguracaoFirebase;
import com.example.teacherhelp.model.Registro;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

    private EditText textEmail, textSenha;
    private Button buttonLogin;
    private Registro registro;
    private FirebaseAuth autenticacao;
    private FirebaseAuth autenticacaoAuto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ids();

        //autenticacao.signOut();

        verificarUsuarioLogado();

        //Esconde a actionBar
        getSupportActionBar().hide();

        //Faz as verificações e loga o usuario

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = textEmail.getText().toString();
                String senha = textSenha.getText().toString();


                if (!email.isEmpty()) {
                    if (!senha.isEmpty()) {

                        registro = new Registro();
                        registro.setEmail(email);
                        registro.setSenha(senha);
                        validarlogin();


                    } else {
                        Toast.makeText(LoginActivity.this, "Preencha a senha!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Preencha o E-mail!", Toast.LENGTH_LONG).show();
                }

            }
        });


    }


    //Valida o login do usuario

    public void validarlogin() {

        autenticacao = ConfiguracaoFirebase.getFireBaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                registro.getEmail(),
                registro.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    abrirPrincipal();

                } else {
                    String excecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        excecao = "Usuario não está cadastrado";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "E-mail e senha não corresponde a usuario cadastrado ";

                    } catch (Exception e) {
                        excecao = "Erro ao cadastrar usuario" + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_LONG).show();

                }


            }
        });


    }


    //Leva para a MainActivity

    public void abrirPrincipal() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }


    //Levar para a activity do cadastro

    public void cadastro(View view) {

        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
        finish();

    }

    public void ids() {
        textEmail = findViewById(R.id.editEmail);
        textSenha = findViewById(R.id.editSenha);
        buttonLogin = findViewById(R.id.buttonLogin);

    }

    //Verifica o usuario

    public void verificarUsuarioLogado() {


        autenticacaoAuto = ConfiguracaoFirebase.getFireBaseAutenticacao();

        if (autenticacaoAuto.getCurrentUser() != null) {

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);


        }


    }


}