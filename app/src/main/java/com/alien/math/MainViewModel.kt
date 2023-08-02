package com.alien.math

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alien.math.model.PostModel
import com.alien.math.model.ResponseModel
import com.alien.math.network.MathsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val _responseData: MutableLiveData<ResponseModel> = MutableLiveData()
    val responseData:LiveData<ResponseModel> = _responseData


    fun getEvalute(postModel: PostModel,context: Context){
        MathsApi().postExpression(postModel).enqueue(object :Callback<ResponseModel>{
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                _responseData.value = response.body()
                Log.d("alien","Success ${response.body()}")
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d("alien","Failed ${t.message}")
                Toast.makeText(context, "Failed ${t.message}", Toast.LENGTH_SHORT).show()

            }

        })

    }

}