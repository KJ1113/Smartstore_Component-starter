package com.ssafy.smartstoredb.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ssafy.smartstoredb.R
import com.ssafy.smartstoredb.activity.MainActivity
import com.ssafy.smartstoredb.adapter.MenuAdapter
import com.ssafy.smartstoredb.databinding.FragmentOrderBinding
import com.ssafy.smartstoredb.dto.Product
import com.ssafy.smartstoredb.service.ProductService

// 하단 주문 탭
private const val TAG = "OrderFragment_싸피"
class OrderFragment : Fragment(){
    private lateinit var menuAdapter: MenuAdapter
    private lateinit var mainActivity: MainActivity
    private lateinit var prodList:List<Product>
    private lateinit var binding:FragmentOrderBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initAdapter()

        binding.recyclerViewMenu.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter = menuAdapter
            //원래의 목록위치로 돌아오게함
            adapter!!.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }

        binding.floatingBtn.setOnClickListener{
            //장바구니 이동
            mainActivity.openFragment(1)
        }

        binding.btnMap.setOnClickListener{
            mainActivity.openFragment(4)
        }
    }

    private fun initData(){
        prodList = ProductService(requireContext()).getProductList()
//        Log.d(TAG, "initData: prodList: ${prodList}")
    }

    private fun initAdapter(){
        menuAdapter = MenuAdapter(requireContext(), prodList)
        menuAdapter.setItemClickListener(object : MenuAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int, productId:Int) {
                mainActivity.openFragment(3, "productId", productId)
            }
        })

    }
}