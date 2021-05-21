package com.example.dogexplorer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogexplorer.adapters.BreedsAdapter
import com.example.dogexplorer.base.FragmentBase
import com.example.dogexplorer.databinding.DogBreedsFragmentBinding
import com.example.dogexplorer.repository.data.Breed
import com.example.dogexplorer.utils.showErrorDialog

class DogBreedsFragment : FragmentBase() {

    private val viewModel: DogBreedsViewModel by viewModels()

    private val breedsAdapter by lazy { BreedsAdapter { breed -> toBreedDetails(breed) } }

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

        initDataBinding(binding)
        initListView(binding.listView)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initDataBinding(binding: DogBreedsFragmentBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.breedVm = viewModel
    }

    private fun initListView(listView: RecyclerView) {
        listView.adapter = breedsAdapter
        listView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObservers() {
        viewModel.getBreedsLiveData().observe(viewLifecycleOwner) { breeds ->
            breedsAdapter.setBreeds(breeds)
        }
        viewModel.getErrorLiveData().observe(viewLifecycleOwner) { errorMessage ->
            requireContext().showErrorDialog(errorMessage)
        }
    }

    private fun toBreedDetails(breed: Breed) {
        findNavController().navigate(DogBreedsFragmentDirections.actionBreedsToDetails(breed))
    }
}