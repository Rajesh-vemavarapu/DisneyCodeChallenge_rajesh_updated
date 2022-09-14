package com.sample.disneycodechallenge_rajesh.ui.activities.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.sample.disneycodechallenge_rajesh.R
import com.sample.disneycodechallenge_rajesh.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.nav_host_fragment_activity_main).previousBackStackEntry?.let {
            findNavController(R.id.nav_host_fragment_activity_main).popBackStack()
        } ?: run {
            onBackPressed()
        }
        return super.onSupportNavigateUp()
    }
}
