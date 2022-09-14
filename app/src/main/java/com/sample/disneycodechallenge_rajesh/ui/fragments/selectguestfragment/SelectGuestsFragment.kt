package com.sample.disneycodechallenge_rajesh.ui.fragments.selectguestfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sample.disneycodechallenge_rajesh.R
import com.sample.disneycodechallenge_rajesh.adapter.GuestsRecyclerViewAdapter
import com.sample.disneycodechallenge_rajesh.databinding.FragmentSelectGuestsBinding
import com.sample.disneycodechallenge_rajesh.listener.OnRecyclerViewItemClick
import com.sample.disneycodechallenge_rajesh.ui.activities.mainactivity.MainActivity
import com.sample.disneycodechallenge_rajesh.ui.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectGuestsFragment : BaseFragment(), OnRecyclerViewItemClick {

    private var _binding: FragmentSelectGuestsBinding? = null
    private val binding get() = _binding!!

    private var selectGuestsViewModel: SelectGuestsViewModel? = null
    private var haveAdapter: GuestsRecyclerViewAdapter? = null
    private var needAdapter: GuestsRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        selectGuestsViewModel =
            ViewModelProvider(this)[SelectGuestsViewModel::class.java]
        _binding = FragmentSelectGuestsBinding.inflate(inflater, container, false)

        (requireActivity() as MainActivity).supportActionBar?.title =
            getString(R.string.select_guests)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        initObservers()

        selectGuestsViewModel?.getAllGuests()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onItemClick(position: Int) {
        val haveItems = haveAdapter?.getUpdatedData()?.filter { it.isChecked }
        val needItems = needAdapter?.getUpdatedData()?.filter { it.isChecked }

        binding.btnContinue.isEnabled = !(haveItems.isNullOrEmpty() && needItems.isNullOrEmpty())
    }

    private fun initViews() {
        with(binding) {
            rvHaveReservations.layoutManager = LinearLayoutManager(requireContext())
            haveAdapter = GuestsRecyclerViewAdapter(this@SelectGuestsFragment)
            rvHaveReservations.adapter = haveAdapter

            rvNeedReservations.layoutManager = LinearLayoutManager(requireContext())
            needAdapter = GuestsRecyclerViewAdapter(this@SelectGuestsFragment)
            rvNeedReservations.adapter = needAdapter
        }
    }

    private fun initListeners() {
        binding.btnContinue.setOnClickListener {
            val haveItems = haveAdapter?.getUpdatedData()?.filter { it.isChecked }

            if (haveItems.isNullOrEmpty()) {
                showSnackBar()
            } else {
                navigationManager?.navigateToConflictFragment()
            }
        }
    }

    private fun initObservers() {
        selectGuestsViewModel?.guests?.observe(viewLifecycleOwner) {
            it?.filter { it.needReservation != null }?.let { needReservationList ->
                needAdapter?.setData(needReservationList)
            }

            it?.filter { it.haveReservation != null }?.let { haveReservationList ->
                haveAdapter?.setData(haveReservationList)
            }
        }
    }

    private fun showSnackBar() {
        val snackBar = Snackbar.make(requireView(), "", Snackbar.LENGTH_LONG)
        val customSnackBar = layoutInflater.inflate(R.layout.custom_snackbar, null)
        val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
        snackBarLayout.setPadding(0, 0, 0, 0)
        with(customSnackBar) {
            findViewById<ImageView>(R.id.ivDismiss).setOnClickListener {
                snackBar.dismiss()
            }
            findViewById<TextView>(R.id.tvHeading).text = getString(R.string.alert_heading)
            findViewById<TextView>(R.id.tvDescription).text = getString(R.string.alert_description)
        }
        snackBarLayout.addView(customSnackBar, 0)
        snackBar.show()
    }
}
