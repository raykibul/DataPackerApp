package com.datapacker.surveyor.ui.login

import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.datapacker.surveyor.MainActivity
import com.datapacker.surveyor.data.Repository
import com.datapacker.surveyor.data.model.*
import com.datapacker.surveyor.databinding.ActivityLoginBinding
import com.google.gson.Gson


class LoginActivity : AppCompatActivity() {


    private var _binding: ActivityLoginBinding? = null
    var repository = Repository()
    private val bd get() = _binding!!
    private   val TAG = "LoginActivity"
    private  var dialog: Dialog?= null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        val view = bd.root
        setContentView(view)

        val  loginViewModel: LoginViewModel by viewModels()
        var savedToken = Save.getString(Constant.TOKENSAVE,this)

        if(!Controller.isOnline(this)){
            var tokenString :String? = Save.getString(Constant.TOKENSAVE,this)
            if (tokenString==null){
                Toast.makeText(this,"No Valid Token found! and no internet!",Toast.LENGTH_SHORT).show()
            }else{
                var gson = Gson()
                var surveyorString = Save.getString(Constant.TOKENSAVE,this)
                if (surveyorString==null){
                    Toast.makeText(this,"No Valid Surveyour found! No connection.",Toast.LENGTH_SHORT).show()
                }else{
                    var surveyor: Surveyor? = gson.fromJson(surveyorString,Surveyor::class.java)
                    if (surveyor==null)
                        Toast.makeText(this,"No valid Surveyour profile found.Please Try login again with Internet!",Toast.LENGTH_SHORT).show()
                    else{
                        Token.getInstance()?.access=tokenString
                        startActivity(Intent(this, MainActivity::class.java));
                        finish()
                    }
                }

            }
        }

        loginViewModel.surveyor.observe(this, Observer {response->
            if (dialog!!.isShowing)dialog!!.dismiss()
            if (response.isSuccessful&& response !=null){
                saveSurveyourData(response.body()!!);
                Token.getInstance()?.access=savedToken
                startActivity(Intent(this, MainActivity::class.java));
                finish()
            }else{

                MyAlert.error("সার্ভার সমস্যাঃ কোড "+response.code(),"আপনার লগিন সফল হয় নি। দয়া করে একটু পরে আবার চেষ্টা করুন",this)
            }
        })


        if(savedToken!=null){

            Token.getInstance()?.access=savedToken
            dialog = Loading.getLoadingDialog(this,"সার্ভেয়র এর ডাটা লোড হচ্ছে...")
            dialog!!.show()
            loginViewModel.loadSurveyor()


        }else{
            bd.emailTextview.setText("user@gmail.com")
            bd.passwordTextVIew.setText("12345678")
        }

        loginViewModel.parsedToken.observe(this, Observer {

            if (dialog!!.isShowing)dialog!!.dismiss()
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
                    dialog= Loading.getLoadingDialog(this,"সার্ভেয়র এর প্রোফাইল লোড হচ্ছে...")
                    dialog!!.show()
                    loginViewModel.loadSurveyor()
                }
            }else{
                Toast.makeText(this,"response code: "+it.code()+"",Toast.LENGTH_SHORT).show()
            }
        })


        bd.loginButton.setOnClickListener {

            if(bd.passwordTextVIew.text.toString()==""||bd.emailTextview.text.toString()==""){
                Toast.makeText(this,"দয়া করে সব ইনপুট পুরন করুন ",Toast.LENGTH_SHORT).show()
            }else{
                dialog = Loading.getLoadingDialog(this,"লগ ইন ডাটা যাচাই হচ্ছে...")
                dialog!!.show()
                var loginBody = LoginBody(bd.emailTextview.text.toString(),bd.passwordTextVIew.text.toString())
                loginViewModel.parseToken(loginBody)
            }
        }


    }
    fun saveSurveyourData(surveyor: Surveyor){
        var gson = Gson()
        var surveyourStringData = gson.toJson(surveyor)
        Save.saveString(Constant.SURVEYOR,surveyourStringData,this)
    }
}