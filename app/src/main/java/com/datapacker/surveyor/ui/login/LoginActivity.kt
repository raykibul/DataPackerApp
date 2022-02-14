package com.datapacker.surveyor.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.datapacker.surveyor.MainActivity
import com.datapacker.surveyor.data.Repository
import com.datapacker.surveyor.databinding.ActivityLoginBinding
import com.datapacker.surveyor.model.Constant
import com.datapacker.surveyor.model.LoginBody
import com.datapacker.surveyor.model.Save
import com.datapacker.surveyor.model.Token



class LoginActivity : AppCompatActivity() {


    private var _binding: ActivityLoginBinding? = null
    var repository = Repository()
    private val bd get() = _binding!!
    private   val TAG = "LoginActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        val view = bd.root
        setContentView(view)

        val  loginViewModel: LoginViewModel by viewModels()

        var savedToken = Save.getString(Constant.TOKENSAVE,this)

        if(savedToken!=null){
            loginViewModel.loadSurveyor()

        }else{
            bd.emailTextview.setText("al@gmail.com")
            bd.passwordTextVIew.setText("987123al")
        }



        loginViewModel.surveyor.observe(this, Observer {response->

            Log.e(TAG, "onCreate: response"+response + response.code())

            if (response.isSuccessful&& response !=null){

                startActivity(Intent(this, MainActivity::class.java));
                finish()
           }
        })



        loginViewModel.parsedToken.observe(this, Observer {

            if(it==null){
                Toast.makeText(this,"null response",Toast.LENGTH_SHORT).show()
            }
        if (it.isSuccessful){
                Toast.makeText(this,"SUCCESS",Toast.LENGTH_SHORT).show()
                var token : Token? = it.body()
                Token.instance = token


                if (token?.access==null||it.code()==401){
                    Toast.makeText(this,token?.detail,Toast.LENGTH_SHORT).show()

                }else{
                    Save.saveString(Constant.TOKENSAVE,token?.access!!,this)
                    loginViewModel.loadSurveyor()
                    Toast.makeText(this,token?.access,Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"response code: "+it.code()+"",Toast.LENGTH_SHORT).show()
            }
        })





        bd.loginButton.setOnClickListener {
            if(bd.passwordTextVIew.text.toString()==""||bd.emailTextview.text.toString()==""){
                Toast.makeText(this,"দয়া করে সব ইনপুট পুরন করুন ",Toast.LENGTH_SHORT).show()
            }else{
               var loginBody = LoginBody(bd.emailTextview.text.toString(),bd.passwordTextVIew.text.toString())
               loginViewModel.parseToken(loginBody)

            }
        }






    }
}