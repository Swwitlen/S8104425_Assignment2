package com.example.s8104425_assignment2.ui.details

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.s8104425_assignment2.databinding.ActivityDetailsBinding
import com.example.s8104425_assignment2.model.SportEntity
import com.example.s8104425_assignment2.util.hide
import com.example.s8104425_assignment2.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide all TextViews initially
        binding.tvSportName.hide()
        binding.tvPlayerCount.hide()
        binding.tvFieldType.hide()
        binding.tvOlympic.hide()
        binding.tvDescription.hide()

        //get SportEntities fro Intent (which is passed from DashboardActivity)
        val entity: SportEntity? = intent.getParcelableExtra(
            "ENTITY",
            SportEntity::class.java
        )

        entity?.let {
            //display all the details in TextView
            binding.tvSportName.text = it.sportName
            binding.tvPlayerCount.text = "Players: ${it.playerCount}"
            binding.tvFieldType.text = "Field: ${it.fieldType}"
            binding.tvOlympic.text = "Olympic Sport: ${if (it.olympicSport) "Yes" else "No"}"
            binding.tvDescription.text = it.description

            //show all textView after data is set
            binding.tvSportName.show()
            binding.tvPlayerCount.show()
            binding.tvFieldType.show()
            binding.tvOlympic.show()
            binding.tvDescription.show()
        }
    }
}
