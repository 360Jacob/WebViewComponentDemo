package com.jacob.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {
    lateinit var mBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        mBinding = method.invoke(null, layoutInflater) as T
        setContentView(mBinding.root)
        initView();
        initData();
    }

     abstract fun initView()

     abstract fun initData()

     abstract fun showLoading()
    override fun onDestroy() {
        super.onDestroy()

    }

}