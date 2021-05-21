package com.example.dogexplorer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.dogexplorer.base.FragmentBase
import com.example.dogexplorer.databinding.BreedDetailsFragmentBinding

class BreedDetailsFragment : FragmentBase() {

    private val viewModel: BreedDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<BreedDetailsFragmentBinding>(
            inflater,
            R.layout.breed_details_fragment,
            container,
            false
        )

        return binding.root
    }


}