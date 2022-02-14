package com.datapacker.surveyor.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.datapacker.surveyor.MainActivity
import com.datapacker.surveyor.data.Repository
import com.datapacker.surveyor.databinding.ActivityLoginBinding
import com.datapacker.surveyor.model.*


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

        loginViewModel.surveyor.observe(this, Observer {response->

            if (response.isSuccessful&& response !=null){
                Loading.stopLoading()
                startActivity(Intent(this, MainActivity::class.java));
                finish()

            }else{
                Loading.stopLoading()
                MyAlert.error("সার্ভার সমস্যাঃ কোড "+response.code(),"আপনার লগিন সফল হয় নি। দয়া করে একটু পরে আবার চেষ্টা করুন",this)
            }
        })
        if(savedToken!=null){

            var token = Token.getInstance()
            token?.access=savedToken
            Loading.startLoading(this,"সার্ভেয়র এর ডাটা লোড হচ্ছে ...")
            loginViewModel.loadSurveyor()


        }else{
            bd.emailTextview.setText("al@gmail.com")
            bd.passwordTextVIew.setText("987123al")
        }







        loginViewModel.parsedToken.observe(this, Observer {
            Loading.stopLoading()

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
                Loading.startLoading(this,"লগ-ইন ডাটা যাচাই করা হচ্ছে...")
               var loginBody = LoginBody(bd.emailTextview.text.toString(),bd.passwordTextVIew.text.toString())
               loginViewModel.parseToken(loginBody)

            }
        }






    }
}