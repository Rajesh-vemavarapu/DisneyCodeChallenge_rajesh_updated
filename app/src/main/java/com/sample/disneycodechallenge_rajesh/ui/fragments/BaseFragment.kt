package com.sample.disneycodechallenge_rajesh.ui.fragments

import androidx.fragment.app.Fragment
import com.sample.disneycodechallenge_rajesh.NavigationManager
import javax.inject.Inject

open class BaseFragment : Fragment() {
    var navigationManager: NavigationManager? = null
        @Inject set
}