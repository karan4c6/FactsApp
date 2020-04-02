package com.karansyd4.factsappexercise.ui.facts

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

class FactsActivity : BaseActivity(), HasSupportFragmentInjector,
    SwipeRefreshLayout.OnRefreshListener {

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
        rvFactsList.setHasFixedSize(true)
        rvFactsList.addItemDecoration(
            VerticalItemDecoration(resources.getDimension(R.dimen._16sp).toInt(), true)
        )
        initSwipeToRefresh()
        when (adapter) {
            null -> {
                adapter = NewListAdapter()
                rvFactsList.adapter = adapter
                adapter?.let {
                    subscribeUi(it)
                }
                newListViewModel.getFactsList()
            }
            else -> {
                rvFactsList.adapter = adapter
            }
        }
    }

    private fun initSwipeToRefresh() {
        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.setColorSchemeResources(
            R.color.colorPrimary,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark
        )
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
                    rvFactsList.visibility = View.VISIBLE
                    result.data?.let { adapter.submitList(it) }
                    swipeRefreshLayout.isRefreshing = false
                }
                Result.Status.LOADING -> {
                    rvFactsList.visibility = View.GONE
                    swipeRefreshLayout.isRefreshing = true
                }
                Result.Status.ERROR -> {
                    swipeRefreshLayout.isRefreshing = false
                    result.message?.let {
                        Snackbar.make(rvFactsList, it, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    override fun onRefresh() {
        newListViewModel.getFactsList()
    }

}
