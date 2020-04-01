package com.karansyd4.newsappexercise.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.karansyd4.newsappexercise.ui.base.listeners.BaseView
import dagger.android.support.AndroidSupportInjection


abstract class BaseFragment : Fragment(), BaseView {
    abstract val layoutId: Int

    protected abstract fun initializeDagger()

    protected abstract fun initializePresenter(view: View)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeDagger()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(layoutId, container, false)
        initializePresenter(view)
        return view
    }
}