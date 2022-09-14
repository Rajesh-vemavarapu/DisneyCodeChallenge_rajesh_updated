package com.sample.disneycodechallenge_rajesh

import android.app.Activity
import androidx.navigation.findNavController
import javax.inject.Inject

class NavigationManager @Inject constructor(
    val activity: Activity
) {
    fun navigateToConflictFragment() {
        activity.findNavController(R.id.nav_host_fragment_activity_main).navigate(
            R.id.navigation_conflicts
        )
    }
}
