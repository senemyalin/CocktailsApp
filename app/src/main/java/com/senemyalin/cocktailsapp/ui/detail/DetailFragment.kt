package com.senemyalin.cocktailsapp.ui.detail

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.senemyalin.cocktailsapp.databinding.FragmentDetailBinding
import com.senemyalin.cocktailsapp.ui.SharedViewModel

class DetailFragment() : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setView()
    }

    private fun setView() {
        sharedViewModel.drinkDetails.observe(viewLifecycleOwner) {

            if (it.videoUrl.isNullOrEmpty().not()) {
                binding.drinkVideo.visibility = View.VISIBLE
                binding.drinkImage.visibility = View.GONE
                val uri: Uri = Uri.parse(it.videoUrl)
                binding.drinkVideo.setVideoURI(uri)
            } else {
                binding.drinkVideo.visibility = View.GONE
                binding.drinkImage.visibility = View.VISIBLE
                binding.drinkImage.load(it.imageUrl)
            }

            binding.drinkName.text = it.name
            binding.drinkIngredients.text = it.ingredients.joinToString(separator = ", ").trim()
        }
    }
    companion object {
        fun newInstance() = DetailFragment()
    }
}
