package com.example.newrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.newrecycler.databinding.ActivityCarDetailsBinding
import kotlinx.android.synthetic.main.activity_car_details.*
import kotlinx.android.synthetic.main.layout_item_view.view.*

class MarcaDetailsActivity : AppCompatActivity() {
    lateinit var binding : ActivityCarDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_car_details)
        val actionbar = supportActionBar
        val mIntent = intent
        val marcas: Marcas = mIntent.getSerializableExtra("Marcas") as Marcas
        Log.d("Info","${marcas.descripcio}, ${marcas.empresa_organitzadora}, ${marcas.logo}")
        binding.carName.text = marcas.empresa_organitzadora
        binding.carDescription.text = marcas.descripcio

        Glide.with(this).load(marcas.logo).placeholder(R.drawable.ic_baseline_downloading_24).error(R.drawable.ic_baseline_error_outline_24).into(binding.imageCar)
       //Glide.with(this).load(marcas.logo).into(binding.imageCar)
        actionbar!!.title="DETALLES"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
}

