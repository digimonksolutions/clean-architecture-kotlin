package com.cleanarchitecture.cryptocapital.ui.fragment.coinList.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cleanarchitecture.domain.interactor.GetCoinListUseCase
import com.cleanarchitecture.domain.model.CoinListResponse
import com.cleanarchitecture.domain.response.Response
import com.cleanarchitecture.cryptocapital.data.Resource
import com.cleanarchitecture.cryptocapital.ui.fragment.coinList.CoinListFragmentDirections
import com.cleanarchitecture.cryptocapital.utils.AppConstants
import com.digi.base_module.base.BaseViewModel
import com.digi.base_module.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val SAVED_DATA = "SAVED_DATA"
class CoinListViewModel(
    private val getCoinListUseCase: GetCoinListUseCase,
    private val handle:SavedStateHandle
):BaseViewModel() {
     val test = "test"
    val coinListLiveData = MutableLiveData<Resource<CoinListResponse>>()

    fun getCoinList() {
        viewModelScope.launch {
            if (handle.get<CoinListResponse>(SAVED_DATA) == null) {
                coinListLiveData.value = Resource.loading()
                val response = getCoinListUseCase.execute(
                    CoinListResponse::class.java,
                    AppConstants.COIN_LIST_URL
                )
                withContext(Dispatchers.Main) {
                    when (response.status) {
                        Response.Status.SUCCESS -> {
                            if (response.data.isNullOrEmpty()) {
                                coinListLiveData.value = Resource.empty("No Data Found.")
                            } else {
                                coinListLiveData.value = Resource.success(response.data!!)
                                handle[SAVED_DATA] = response.data
                            }
                        }

                        Response.Status.ERROR -> {
                            coinListLiveData.value = Resource.error(response.message.toString())
                        }
                    }
                }
            }
        }
    }

    fun navigateToCoinDetailPage(coinID:String,color:Int){
        navigate(CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(coinID,color))
    }



}