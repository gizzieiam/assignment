package com.example.apipractice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apipractice.databinding.ActivityBtnBinding
import com.example.apipractice.databinding.FragmentMainBinding
import com.example.apipractice.util.ViewState
import com.example.apipractice.viewModel.shibeViewModel

class MainFragment : Fragment() {

    private lateinit var mainBinding: FragmentMainBinding
    private lateinit var binding: ActivityBtnBinding
    private val viewModel by viewModels<shibeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        mainBinding = FragmentMainBinding.inflate(inflater, container, false)
        binding = ActivityBtnBinding.inflate(inflater, container, false)

        initViews()
        initButtons()
        initObserver()


        return mainBinding.root
    }



    private fun initViews() {
        with(mainBinding) {
            tvCount.text = ""
            incrementBtn.setOnClickListener {
                println("increment")
                viewModel.updateCount()

            }
        }
    }
    private fun initButtons() {
        with(binding) {
            imgBtn.setOnClickListener {
                val action =
                    MainFragmentDirections.actionMainFragmentToShibeFragment("1")
                findNavController().navigate(action)
            }

            decrementBtn.setOnClickListener {
                viewModel.updateCount()
            }
        }
    }
    private fun initObserver() = with(viewModel) {
        getCount().observe(viewLifecycleOwner) {
            @Override
            fun onChanged(count: String) {
                binding.tvCount.text = count;
            }
        }
    }
}