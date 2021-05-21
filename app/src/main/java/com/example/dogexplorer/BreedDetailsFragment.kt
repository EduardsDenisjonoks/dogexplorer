package com.example.dogexplorer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.dogexplorer.adapters.BreedImageAdapter
import com.example.dogexplorer.base.FragmentBase
import com.example.dogexplorer.databinding.BreedDetailsFragmentBinding

class BreedDetailsFragment : FragmentBase() {

    private val args by navArgs<BreedDetailsFragmentArgs>()
    private val breedImageAdapter by lazy { BreedImageAdapter(args.breed.images)}

    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

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

        initListView(binding.listView)

        return binding.root
    }

    private fun initListView(listView: RecyclerView) {
        listView.adapter = breedImageAdapter
        staggeredGridLayoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)
        listView.layoutManager = staggeredGridLayoutManager
    }

}