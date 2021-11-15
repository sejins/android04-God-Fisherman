package com.android04.godfisherman.ui.mypage


import androidx.fragment.app.viewModels
import com.android04.godfisherman.R
import com.android04.godfisherman.databinding.FragmentMyPageBinding
import com.android04.godfisherman.ui.base.BaseFragment

class MyPageFragment : BaseFragment<FragmentMyPageBinding, MyPageViewModel>(R.layout.fragment_my_page) {

    override val viewModel: MyPageViewModel by viewModels()
}
