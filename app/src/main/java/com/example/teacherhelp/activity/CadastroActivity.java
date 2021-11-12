package com.example.teacherhelp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private EditText textNome, textEmail,textSenha;
    private Button buttonRegistar;
    private Registro registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        ids();

        buttonRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textEmail.getText().toString();
                String senha = textSenha.getText().toString();

                //Validando campos

                if (!email.isEmpty()) {
                    if (!senha.isEmpty()) {

                        registro = new Registro();
                        registro.setEmail(email);
                        registro.setSenha(senha);
                        cadastrarUsuario();


                    } else {
                        Toast.makeText(CadastroActivity.this, "Preencha a senha!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(CadastroActivity.this, "Preencha o E-mail!", Toast.LENGTH_LONG).show();
                }



            }
        });




    }

    // Cadastrar Usuario e envia de volta para a tela de login
    public void cadastrarUsuario() {

        autenticacao = ConfiguracaoFirebase.getFireBaseAutenticacao();

        autenticacao.createUserWithEmailAndPassword(
                registro.getEmail(), registro.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    abrirLogin();

                } else {
                    String excecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        excecao = "Digite uma senha mais forte!";

                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Por favor, digite um e-mail válido";
                    } catch (FirebaseAuthUserCollisionException e) {
                        excecao = "Essa conta já foi cadastrada";

                    } catch (Exception e) {
                        excecao = "Erro ao cadastrar usuario" + e.getMessage();
                        e.printStackTrace();
                    }


                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_LONG).show();
                }

            }
        });


    }


    public void abrirLogin() {

        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }


    public void ids(){

        textNome = findViewById(R.id.editNomeCadastro);
        textEmail = findViewById(R.id.editEmailCadastro);
        textSenha = findViewById(R.id.editSenhaCadastro);
        buttonRegistar = findViewById(R.id.buttonRegistrar);


    }
}