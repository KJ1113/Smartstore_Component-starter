package com.ssafy.smartstoredb.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.smartstoredb.ApplicationClass
import com.ssafy.smartstoredb.R
import com.ssafy.smartstoredb.activity.MainActivity
import com.ssafy.smartstoredb.adapter.CommentAdapter
import com.ssafy.smartstoredb.databinding.FragmentMenuDetailBinding
import com.ssafy.smartstoredb.dto.Product
import com.ssafy.smartstoredb.service.ProductService
import com.ssafy.smartstoredb.util.CommonUtils

//메뉴 상세 화면 . Order탭 - 특정 메뉴 선택시 열림
private const val TAG = "MenuDetailFragment_싸피"
class MenuDetailFragment : Fragment(){
    private lateinit var mainActivity: MainActivity
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var product: Product
    private var productId = -1

    private lateinit var binding:FragmentMenuDetailBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity.hideBottomNav(true)

        arguments?.let {
            productId = it.getInt("productId", -1)
            Log.d(TAG, "onCreate: $productId")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuDetailBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
        initAdapter()
        initListener()
    }

    private fun initData(){
        product = ProductService(requireContext()).getProductWithComments(productId)
//        Log.d(TAG, "initData: prodList: ${product}")
    }

    private fun initView(){
        binding.txtMenuName.text = product.name
        binding.txtMenuPrice.text = CommonUtils.makeComma(product.price)

        var img = requireContext().resources.getIdentifier(product.img, "drawable", requireContext().packageName)
        binding.menuImage.setImageResource(img)
    }

    private fun initAdapter() {
        commentAdapter = CommentAdapter(mainActivity, product.comment)
        binding.recyclerViewMenuDetail.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = commentAdapter
            //원래의 목록위치로 돌아오게함
            adapter!!.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    private fun initListener(){
        binding.btnAddList.setOnClickListener {
            Toast.makeText(context,"상품이 장바구니에 담겼습니다.",Toast.LENGTH_SHORT).show()
        }
        binding.btnCreateComment.setOnClickListener {
            showDialogRatingStar()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.hideBottomNav(false)
    }

    private fun showDialogRatingStar() {



    }


    companion object {
        @JvmStatic
        fun newInstance(key:String, value:Int) =
            MenuDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(key, value)
                }
            }
    }
}