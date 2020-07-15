package com.internshala.myapplication.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.internshala.myapplication.util.ConnectionManager
import com.intershala.myapplication.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    /*Declaring the different variables used for this activity*/
    private lateinit var registerYourself: TextView
    private lateinit var login: Button
    private lateinit var etMobileNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var txtForgotPassword: TextView

    /*Life-cycle method of the activity*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Linking the view*/
        setContentView(R.layout.activity_login)


        /*Initialising the views with the ones defined in the XML*/
        etMobileNumber = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        login = findViewById(R.id.btnLogin)

        /*Handling the clicks using the setOnClickListener method*/

        if (ConnectionManager().checkConnectivity(this@LoginActivity)) {
            login.setOnClickListener {

                /*Declaring the intent which sets up the route for the navigation of the activity*/
                val intent = Intent(this@LoginActivity, MainActivity::class.java)

                /*Declaring the bundle object which will carry the data
            * You can send the data inside intents
            *
            * We specifically used Bundle just to demonstrate a new technique*/
                val bundle = Bundle()

                /*Setting a value data which is activity specific. This will be used to identify from where the data was sent*/
                bundle.putString("data", "login")

                /*Putting the values in Bundle*/
                bundle.putString("email", etEmail.text.toString())
                bundle.putString("password", etPassword.text.toString())

                /*Putting the Bundle to be shipped with the intent*/
                intent.putExtra("details", bundle)

                /*Starting the new activity by sending the intent in the startActivity method*/
                startActivity(intent)
            }

        } else {
            val dialog = AlertDialog.Builder(this@LoginActivity)
            dialog.setTitle("Error")
            dialog.setMessage("Internet Connection is not found")
            dialog.setPositiveButton("Open Settings") { text, listener ->
                val settingsIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingsIntent)
                finish()
            }
            dialog.setNegativeButton("Exit") { text, listener ->
                ActivityCompat.finishAffinity(this@LoginActivity)
            }
            dialog.create()
            dialog.show()
        }
    }
}
