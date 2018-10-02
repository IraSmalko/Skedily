package com.skedily.screens.authentication


/**
 * Created by smalk on 11/26/2017.
 */
//class LoggedInActivity : BaseBoundVmActivity<ActivityLoggedInBinding, LoggedInViewModel>(
//        R.layout.activity_logged_in, LoggedInViewModel::class), LoggedInInteractor {

//    private var fbAuth = FirebaseAuth.getInstance()
//    private var googleApiClient: GoogleApiClient? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        vm.init(this, signButton)
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build()
//        googleApiClient = GoogleApiClient.Builder(this)
//                .enableAutoManage(this, { })
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build()
//    }
//
//    override fun singIn() {
//        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
//        startActivityForResult(signInIntent, RC_SIGN_IN)
//        awaitActivityResult(RC_SIGN_IN)
//                .map { GoogleSignIn.getSignedInAccountFromIntent(it.data).getResult(ApiException::class.java) }
//                .map { GoogleAuthProvider.getCredential(it.idToken, null) }
//                .flatMap { AuthListener(fbAuth, it).awaitResult() }
//                .subscribe { t ->
//                    when (t) {
//                        true -> vm.successfulLogIn()
//                        false -> vm.failedLogIn()
//                    }
//                }
//    }
//
//    override fun signOut() {
//     //   showMessage()
//        fbAuth.signOut()
//    }
//
////    private fun showMessage() {
////        Snackbar.make(view, getString(R.string.lgging_out), Snackbar.LENGTH_INDEFINITE).setAction(getString(R.string.action), null).show()
////    }
//
//    override fun successfulLogIn() {
//        startActivity(Intent(this@LoggedInActivity, MainActivity::class.java))
//    }
//
//    override fun failedLogIn() = Toast.makeText(this,
//            getString(R.string.authentication_failed), Toast.LENGTH_SHORT).show()
//
//    companion object {
//        const val RC_SIGN_IN = 9001
//    }
//}