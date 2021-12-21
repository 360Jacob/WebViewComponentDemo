package com.jacob.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    lateinit var mBinding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val superClass = javaClass.genericSuperclass
        (superClass as ParameterizedType).actualTypeArguments[0] as Class<T>
        val cls =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
        mBinding = cls.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        ).invoke(null, LayoutInflater.from(requireActivity()), container, false) as T
        initView(mBinding.root);
        initData()
        return mBinding.root
    }

    abstract fun initView(rootView: View)
    abstract fun initData()
}