package com.example.dogexplorer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dogexplorer.base.FragmentBase
import com.example.dogexplorer.databinding.DogBreedsFragmentBinding

class DogBreedsFragment : FragmentBase() {

    private val viewModel: DogBreedsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<DogBreedsFragmentBinding>(
            inflater,
            R.layout.dog_breeds_fragment,
            container,
            false
        )

        binding.btnTest.setOnClickListener {
            findNavController().navigate(DogBreedsFragmentDirections.actionBreedsToDetails())
        }
        return binding.root
    }

}