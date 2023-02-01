package com.senemyalin.cocktailsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.senemyalin.cocktailsapp.R
import com.senemyalin.cocktailsapp.databinding.FragmentHomeBinding
import com.senemyalin.cocktailsapp.ui.SharedViewModel
import com.senemyalin.cocktailsapp.utility.observeTextChanges
import com.senemyalin.cocktailsapp.utility.okWith
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var data: List<HomeUiData>

    private lateinit var adapter: DrinkRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeSearchTextChanges()
        observeUiState()
    }

    private fun setRecyclerView() {
        adapter = DrinkRecyclerViewAdapter { it ->
            sharedViewModel.updatePosition(it)

            sharedViewModel.position.observe(viewLifecycleOwner) {
                if (sharedViewModel.drinks.value != null) {
                    sharedViewModel.updateDrinkDetails(sharedViewModel.drinks.value!![it])
                }
            }

            navigateToDetailScreen()
        }

        binding.drinkListRecyclerView.adapter = adapter
    }

    private fun observeUiState() {
        viewModel.drinkHomeUiState.observe(viewLifecycleOwner) {
            when (it) {
                is HomeUiState.Error -> {

                }
                HomeUiState.Loading -> {

                }
                is HomeUiState.Success -> {
                    handleSuccessUiState(it.data)
                }
            }
        }
    }

    private fun handleSuccessUiState(data: List<HomeUiData>) {
        sharedViewModel.updateDrinkDetails(data)

        setRecyclerView()
        adapter.updateItems(data)
    }

    private fun navigateToDetailScreen() {
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }

    private fun observeSearchTextChanges() {
        binding.searchEditText.observeTextChanges()
            .filter { it okWith MINIMUM_SEARCH_LENGTH }
            .debounce(SEARCH_DEBOUNCE_TIME_IN_MS)
            .distinctUntilChanged()
            .onEach {
                viewModel.getDrinksWithFirstLetter(it)
            }.launchIn(lifecycleScope)
    }

    companion object {
        private const val MINIMUM_SEARCH_LENGTH = 1
        private const val SEARCH_DEBOUNCE_TIME_IN_MS = 300L
        fun newInstance() = HomeFragment()
    }

}