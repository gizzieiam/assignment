package com.example.apipractice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apipractice.databinding.FragmentMainBinding
import com.example.apipractice.viewModel.shibeViewModel

class MainFragment : Fragment() {

    private lateinit var mainBinding: FragmentMainBinding
    private val viewModel by viewModels<shibeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        mainBinding = FragmentMainBinding.inflate(inflater, container, false)

        initViews()
        initButtons()
        initObserver()


        return mainBinding.root
    }



    private fun initViews() {
        with(mainBinding) {
            tvCount.text = "0"
            incrementBtn.setOnClickListener {
                viewModel.updateCount(true)
            }
            decrementBtn.setOnClickListener {
                viewModel.updateCount(false)
            }
        }
    }
    private fun initButtons() {
        with(mainBinding) {
            imgBtn.setOnClickListener {
                val action =
                    MainFragmentDirections.actionMainFragmentToShibeFragment(tvCount.text.toString())
                findNavController().navigate(action)
            }
            tvCount.text.toString().toInt()
        }
    }
    private fun initObserver() = with(viewModel) {
        getCount().observe(viewLifecycleOwner) {
            mainBinding.tvCount.text = it.toString();
        }
    }
}