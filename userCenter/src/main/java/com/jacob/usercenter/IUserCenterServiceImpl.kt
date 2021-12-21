package com.jacob.usercenter

import android.content.Intent
import com.google.auto.service.AutoService
import com.jacob.base.BaseApplication
import com.jacob.common.autoservice.IUserCenterService
@AutoService(IUserCenterService::class)
class IUserCenterServiceImpl: IUserCenterService {
    override fun isLogined(): Boolean {
        return false
    }

    override fun login() {
        val intent = Intent(BaseApplication.instance, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        BaseApplication.instance?.startActivity(intent)
    }
}