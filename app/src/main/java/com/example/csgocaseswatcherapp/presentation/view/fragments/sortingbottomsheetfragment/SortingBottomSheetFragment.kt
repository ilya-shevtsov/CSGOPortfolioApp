package com.example.csgocaseswatcherapp.presentation.view.fragments.sortingbottomsheetfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.csgocaseswatcherapp.databinding.FragmentSortingBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortingBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSortingBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSortingBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

        }
    }
}