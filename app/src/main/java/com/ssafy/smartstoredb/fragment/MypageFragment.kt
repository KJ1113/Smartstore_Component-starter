package com.ssafy.smartstoredb.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.smartstoredb.ApplicationClass
import com.ssafy.smartstoredb.R
import com.ssafy.smartstoredb.activity.MainActivity
import com.ssafy.smartstoredb.adapter.OrderAdapter
import com.ssafy.smartstoredb.databinding.FragmentMypageBinding
import com.ssafy.smartstoredb.dto.UserOrderDetail
import com.ssafy.smartstoredb.service.UserService

// MyPage 탭
class MypageFragment : Fragment(){
    private lateinit var orderAdapter : OrderAdapter
    private lateinit var mainActivity: MainActivity
    private lateinit var list : ArrayList<UserOrderDetail>

    private lateinit var binding:FragmentMypageBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id = getUserData()
        initData(id)
        initAdapter()
    }

    private fun initData(id:String){
        list = UserService(mainActivity).getOrderList(id)
    }

    private fun initAdapter() {
        orderAdapter = OrderAdapter(mainActivity, list)
        orderAdapter.setItemClickListener(object : OrderAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int, orderid:Int) {
                mainActivity.openFragment(2, "orderId", orderid)
            }
        })

        binding.recyclerViewOrder.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = orderAdapter
            //원래의 목록위치로 돌아오게함
            adapter!!.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
        binding.logout.setOnClickListener {
            mainActivity.openFragment(5)
        }
    }

    private fun getUserData():String{
        var user = ApplicationClass.sharedPreferencesUtil.getUser()
        binding.textUserName.text = user.name

        return user.id
    }

}