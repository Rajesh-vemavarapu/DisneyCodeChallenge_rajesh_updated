package com.sample.disneycodechallenge_rajesh.ui.fragments.conflictfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.disneycodechallenge_rajesh.R
import com.sample.disneycodechallenge_rajesh.databinding.FragmentConflictsBinding
import com.sample.disneycodechallenge_rajesh.ui.activities.mainactivity.MainActivity
import com.sample.disneycodechallenge_rajesh.ui.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConflictFragment : BaseFragment() {

    private var _binding: FragmentConflictsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentConflictsBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).supportActionBar?.title = getString(R.string.conflict)
        return binding.root
    }
}