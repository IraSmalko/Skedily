package com.skedily.screens.authentication

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.skedily.R
import com.skedily.base.BaseBoundVmActivity
import com.skedily.databinding.ActivityLoggedInBinding
import com.skedily.screens.main.MainActivity
import kotlinx.android.synthetic.main.activity_logged_in.*
import kotlinx.android.synthetic.main.item_schedule.*


/**
 * Created by smalk on 11/26/2017.
 */
class LoggedInActivity : BaseBoundVmActivity<ActivityLoggedInBinding, LoggedInViewModel>(
        R.layout.activity_logged_in, LoggedInViewModel::class), LoggedInInteractor {


    var fbAuth = FirebaseAuth.getInstance()
    private var mGoogleApiClient: GoogleApiClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.init(this, signButton)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, { })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
    }

    override fun singIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun signOut() {
        showMessage()
        fbAuth.signOut()
    }

    private fun showMessage() {
        Snackbar.make(view, "Logging Out...", Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {

            }

        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        if (task.isSuccessful()) {
                            startActivity(Intent(this@LoggedInActivity, MainActivity::class.java))
                        } else {
                            Toast.makeText(this@LoggedInActivity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }
                })
    }

    companion object {
        const val RC_SIGN_IN = 9001
    }
}