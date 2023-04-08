package com.example.githubusersearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide.init
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(){

    companion object{
        private const val TAG = "MainViewModel"
    }

    private val _listUsers = MutableLiveData<List<UserDetail>>()
    val listUsers: LiveData<List<UserDetail>> = _listUsers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _nameSearch = MutableLiveData<String>()
    val nameSearch: LiveData<String> = _nameSearch

    private val _isFound = MutableLiveData<Boolean>()
    val isFound: LiveData<Boolean> = _isFound

    private val _user = MutableLiveData<UserDetailResponse>()
    val user: LiveData<UserDetailResponse> = _user



    init{
        getListUsers()
    }

    // Public fun for set the nameSearch
    fun setSearch(search: String){
        _nameSearch.value = search
        getListUsers(nameSearch.value.toString())
    }

    private fun getUser(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<UserDetailResponse> {
            override fun onResponse(
                call: Call<UserDetailResponse>,
                response: Response<UserDetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _user.value = response.body()
                } else {
                    Log.e(MainViewModel.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Main Activity", "onFailure: ${t.message}")
            }
        })
    }


    private fun getListUsers(nama: String = "arif"){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUsers(nama)
        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    if(response.body()?.totalCount != 0){
                        _isFound.value = true
                        _listUsers.value = response.body()?.items as List<UserDetail>?
                    } else {
                        _isFound.value = false
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("Main Activity", "onFailure: ${t.message}")
            }

        })
    }


}