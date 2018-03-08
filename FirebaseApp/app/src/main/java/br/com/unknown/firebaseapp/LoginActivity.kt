package br.com.unknown.firebaseapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import br.com.unknown.firebaseapp.extensions.getText
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseUser



class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance();

        btnCriarConta.setOnClickListener {
            if (!ValidarCampos()) {
                Toast.makeText(this, "Favor digitar usuário e senha.",
                        Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            mAuth.createUserWithEmailAndPassword(
                    txtEmail.getText(), txtPassword.getText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            Toast.makeText(this,
                                    "Conta criada com sucesso.",
                                    Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Ocorreu um erro ao criar a conta,\nfavor tentar novamente",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }
        }

        btnLogin.setOnClickListener {
            if (!ValidarCampos()) {
                Toast.makeText(this, "Favor digitar usuário e senha.",
                        Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            mAuth.signInWithEmailAndPassword(
                    txtEmail.getText(), txtPassword.getText())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val currentUser = mAuth.currentUser
                            Toast.makeText(this,
                                    "Usuário " + currentUser?.displayName + " conectado com sucesso.",
                                    Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Senha incorreta.",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }
        }

        btnLogout.setOnClickListener { mAuth.signOut() }

        btnEmail.setOnClickListener {
            val currentUser = mAuth.currentUser
            currentUser?.sendEmailVerification()
                    ?.addOnCompleteListener(this, OnCompleteListener {
                        task ->
                        var message = "Houve uma falha ao enviar o e-mail de verificação."
                        if (task.isSuccessful) {
                            message = "E-mail enviado para: " + currentUser.email
                        }

                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    })
        }
    }

    fun ValidarCampos():Boolean{
        return txtEmail.getText() != "" && txtPassword.getText() != ""
    }
}
