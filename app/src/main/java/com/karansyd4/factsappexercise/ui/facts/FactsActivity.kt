package com.karansyd4.factsappexercise.ui.facts

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.karansyd4.factsappexercise.R
import com.karansyd4.factsappexercise.data.remote.Result
import com.karansyd4.factsappexercise.di.ViewModelFactory
import com.karansyd4.factsappexercise.di.injectViewModel
import com.karansyd4.factsappexercise.ui.base.BaseActivity
import com.karansyd4.factsappexercise.ui.factslist.FactsListViewModel
import com.karansyd4.factsappexercise.ui.factslist.adapter.NewListAdapter
import com.karansyd4.factsappexercise.ui.factslist.adapter.VerticalItemDecoration
import com.karansyd4.factsappexercise.util.ConnectivityUtil
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_facts.*
import javax.inject.Inject

class FactsActivity : BaseActivity(), HasSupportFragmentInjector {

    companion object {
        @JvmStatic
        fun createIntent(context: Context): Intent = Intent(context, FactsActivity::class.java)
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override val layoutId: Int
        get() = R.layout.activity_facts

    @Inject
    lateinit var viewModel: ViewModelFactory

    private lateinit var newListViewModel: FactsListViewModel

    private var adapter: NewListAdapter? = null

    override fun initializeViewModel() {
        newListViewModel = injectViewModel(viewModel)
        newListViewModel.connectivityAvailable = ConnectivityUtil.isConnected(this)
    }

    override fun onResume() {
        super.onResume()
        initViews()
    }

    private fun initViews() {
        rvNewsList.setHasFixedSize(true)
        rvNewsList.addItemDecoration(
            VerticalItemDecoration(resources.getDimension(R.dimen._16sp).toInt(), true)
        )
        when (adapter) {
            null -> {
                adapter = NewListAdapter()
                rvNewsList.adapter = adapter
                adapter?.let {
                    subscribeUi(it)
                }
                newListViewModel.getFactsList()
            }
            else -> {
                rvNewsList.adapter = adapter
                progressBar.visibility = View.GONE
            }
        }
    }


    /**
     * Observer all list to update UI on data change. If MutableLiveData already has data
     * set, it will be delivered to the observer.
     * When data changes views will receive the last available data from the server and
     * local database.
     */
    private fun subscribeUi(adapter: NewListAdapter) {
        newListViewModel.mutableListLiveDataResult.observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    rvNewsList.visibility = View.VISIBLE
                    result.data?.let { adapter.submitList(it) }
                }
                Result.Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    rvNewsList.visibility = View.GONE
                }
                Result.Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    result.message?.let {
                        Snackbar.make(rvNewsList, it, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

}