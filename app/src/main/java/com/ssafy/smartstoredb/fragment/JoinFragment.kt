package com.ssafy.smartstoredb.fragment

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ssafy.smartstoredb.R
import com.ssafy.smartstoredb.activity.LoginActivity
import com.ssafy.smartstoredb.databinding.FragmentJoinBinding
import com.ssafy.smartstoredb.service.UserService

// 회원 가입 화면
private const val TAG = "JoinFragment_싸피"
class JoinFragment : Fragment(){
    private var checkedId = false
    lateinit var binding: FragmentJoinBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //id 중복 확인 버튼
        binding.btnConfirm.setOnClickListener{






        }

        // 회원가입 버튼
        binding.btnJoin.setOnClickListener {









        }
    }
}