package com.picpay.desafio.android.features

import com.picpay.desafio.android.R
import com.picpay.desafio.android.base.BaseActivity
import com.picpay.desafio.android.base.BaseViewModel
import com.picpay.desafio.android.databinding.ActivityUsersNavFlowBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersNavFlowActivity : BaseActivity<ActivityUsersNavFlowBinding>() {

    override val layoutId = R.layout.activity_users_nav_flow
    override val viewModel by viewModel<BaseViewModel>()
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var progressBar: ProgressBar
//    private lateinit var adapter: UserListAdapter

//    private val url = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
//
//    private val gson: Gson by lazy { GsonBuilder().create() }
//
//    private val okHttp: OkHttpClient by lazy {
//        OkHttpClient.Builder()
//            .build()
//    }
//
//    private val retrofit: Retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(url)
//            .client(okHttp)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//    }
//
//    private val service: PicPayService by lazy {
//        retrofit.create(PicPayService::class.java)
//    }

//    override fun onResume() {
//        super.onResume()

    //        recyclerView = findViewById(R.id.recyclerView)
//        progressBar = findViewById(R.id.user_list_progress_bar)
//
//        adapter = UserListAdapter()
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        progressBar.visibility = View.VISIBLE
//        service.getUsers()
//            .enqueue(object : Callback<List<User>> {
//                override fun onFailure(call: Call<List<User>>, t: Throwable) {
//                    val message = getString(R.string.error)
//
//                    progressBar.visibility = View.GONE
//                    recyclerView.visibility = View.GONE
//
//                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
//                        .show()
//                }
//
//                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
//                    progressBar.visibility = View.GONE
//
//                    adapter.users = response.body()!!
//                }
//            })
//    }


}
