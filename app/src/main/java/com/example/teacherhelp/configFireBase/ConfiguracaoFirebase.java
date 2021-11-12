package com.example.teacherhelp.configFireBase;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {


    private static FirebaseAuth autenticacao;

    //retorna a intancia do FireBaseAuth
    public static FirebaseAuth getFireBaseAutenticacao() {
        if (autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;

    }




}
