package com.example.apipractice.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.apipractice.R
import com.example.apipractice.adapter.ShibeAdapter
import com.example.apipractice.databinding.FragmentShibeBinding
import com.example.apipractice.util.ViewState
import com.example.apipractice.viewModel.shibeViewModel

class ShibeFragment : Fragment(R.layout.fragment_shibe) {
    private var _binding: FragmentShibeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<shibeViewModel>()
    private val args by navArgs<ShibeFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentShibeBinding.inflate(inflater, container, false).also{
        _binding = it
//        args.count

    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() = with(viewModel){
        viewState.observe(viewLifecycleOwner){state ->
            binding.loader.isVisible = state is ViewState.Loading
            if(state is ViewState.Success) handleSuccess(state.url)
            if(state is ViewState.Error) handleError(state.e)
        }
    }

    private fun handleError(e: String) {
        Toast.makeText(context, e, Toast.LENGTH_SHORT).show()
    }

    private fun handleSuccess(url: List<String>) {
        binding.rvUrls.adapter = ShibeAdapter(url)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}