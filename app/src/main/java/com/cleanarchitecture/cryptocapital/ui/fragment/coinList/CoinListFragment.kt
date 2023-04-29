package com.cleanarchitecture.cryptocapital.ui.fragment.coinList

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.cleanarchitecture.domain.model.CoinListResponse
import com.cleanarchitecture.cryptocapital.R
import com.cleanarchitecture.cryptocapital.data.Resource
import com.cleanarchitecture.cryptocapital.databinding.FragmentCoinListBinding
import com.cleanarchitecture.cryptocapital.ui.fragment.coinList.adapter.CoinListAdapter
import com.cleanarchitecture.cryptocapital.ui.fragment.coinList.viewmodel.CoinListViewModel
import com.digi.base_module.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.digi.base_module.extensions.toastMessage

class CoinListFragment : BaseFragment<FragmentCoinListBinding, CoinListViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_coin_list
    override val viewModel: CoinListViewModel by viewModel()
    private lateinit var adapter: CoinListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCoinList()
    }


    override fun onReady(savedInstanceState: Bundle?) {
        setObserver()
    }

    private fun setObserver() {
        viewModel.coinListLiveData.observe(viewLifecycleOwner) { data ->
            when (data.status) {
                Resource.Status.SUCCESS -> {
                    binding.loading = false
                    setupUi(data.data!!)
                }

                Resource.Status.ERROR -> {
                    binding.loading = false
                    requireContext().toastMessage(data.message.toString())
                }

                Resource.Status.LOADING -> {
                    binding.loading = true
                }

                Resource.Status.EMPTY -> {
                    binding.loading = false
                    requireContext().toastMessage(data.message.toString())
                }
            }
        }

    }

    private fun setupUi(coinListData: ArrayList<CoinListResponse.CoinListResponseItem>) {
        adapter = CoinListAdapter(coinListData, viewModel)
        binding.rvCoinList.adapter = adapter

    }

    override fun onNetworkAvailable() {
    }

    override fun onNetworkLost() {

    }

}