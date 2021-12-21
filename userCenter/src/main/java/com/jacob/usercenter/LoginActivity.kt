package com.jacob.usercenter

import android.app.ProgressDialog
import android.view.View
import android.view.WindowManager
import com.google.common.eventbus.EventBus
import com.jacob.base.view.BaseActivity
import com.jacob.common.autoservice.eventbus.LoginEvent
import com.jacob.usercenter.databinding.ActivityLoginBinding

class LoginActivity: BaseActivity<ActivityLoginBinding>() {
    private lateinit var pDialog: ProgressDialog

    override fun initView() {
        // Progress dialog

        // Progress dialog
        pDialog = ProgressDialog(this)
        pDialog.setCancelable(false)

        // Hide Keyboard
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        // Login button Click Event
        mBinding.btnLogin.setOnClickListener(View.OnClickListener {
           var email: String = mBinding.lEditEmail.text.toString()
            EventBus().post(LoginEvent(email))
            finish()
        })
    }

    override fun initData() {
    }

    override fun showLoading() {
    }
}