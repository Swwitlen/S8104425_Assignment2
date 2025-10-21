package com.example.s8104425_assignment2.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s8104425_assignment2.databinding.ActivityDashboardBinding
import com.example.s8104425_assignment2.ui.details.DetailsActivity
import com.example.s8104425_assignment2.util.hide
import com.example.s8104425_assignment2.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
//show dashboard list in RecycleView
//handle click--which mean navigate to Details screen
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: EntityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get keypass from Intent passed from LoginActivities
        val keypass = intent.getStringExtra("KEYPASS") ?: ""

        //initially hide RecycleView until data loads
        binding.recyclerView.hide()

        //fetch dashboard data
        viewModel.fetchDashboard(keypass)

        //observe liveData for dashboard
        viewModel.dashboardLiveData.observe(this) { entities ->
            //initialize recycleView adapter with click listener
            adapter = EntityAdapter(entities) { entity ->
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("ENTITY", entity) //pass sportEntity to details
                startActivity(intent)
            }
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)

            //show recycleView when data is loaded
            binding.recyclerView.show()
        }

        //observe liveData fro errors
        viewModel.errorLiveData.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            //hide RecycleView on error
            binding.recyclerView.hide()
        }
    }
}