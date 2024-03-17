package com.adit.kandang.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnFocusChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adit.kandang.R
import com.adit.kandang.databinding.ActivityMainBinding
import com.adit.kandang.model.StatusResponse
import com.adit.kandang.model.data.remote.AppCreator
import com.adit.kandang.utils.FormatSpannable

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

   private lateinit var mainViewModel: MainViewModel
    private val kandangAdapter: KandangAdapter = KandangAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this  ))
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(AppCreator.getApiHelperInstance())
        )[MainViewModel::class.java]

        binding.btnActive.isCheckable = true

        with(binding){
            if (btnActive.isChecked || btnActive.isToggleCheckedStateOnClick){
                loadDataKandangAktif()
            }

            txtInputSearch.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
                toggleGroup.isVisible = !hasFocus
            }

            btnActive.setOnClickListener {
                btnActive.setBackgroundDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.background_button_selector))
                btnActive.backgroundTintList = ContextCompat.getColorStateList(this@MainActivity, R.color.color_selector)

                btnOff.setBackgroundDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.background_button_selector))
                btnOff.backgroundTintList = ContextCompat.getColorStateList(this@MainActivity, R.color.color_selector)

            }
            btnOff.setOnClickListener {
                btnActive.setBackgroundDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.background_button_selector))
                btnActive.backgroundTintList = ContextCompat.getColorStateList(this@MainActivity, R.color.color_selector)

                btnOff.setBackgroundDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.background_button_selector))
                btnOff.backgroundTintList = ContextCompat.getColorStateList(this@MainActivity, R.color.color_selector)

            }

            toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
                if (isChecked)
                    when(checkedId){
                        R.id.btnActive -> {
                            loadDataKandangAktif()
                        }

                        R.id.btnOff -> {
                           loadDataKandangRehat()
                        }
                    }
            }

            rvKandang.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = kandangAdapter
            }
        }
    }

    private fun loadDataKandangAktif(){
        mainViewModel.getKandangActive().observe(this@MainActivity) {
            when(it.status){
                StatusResponse.SUCCESS -> {
                    val message = "Kamu memiliki ${it.data?.size.toString()} kandang aktif."
                    val textSpannable = "${it.data?.size.toString()} kandang aktif"
                    val spannable = FormatSpannable.formatSpannableBoldBlack(
                        sentence = message, textSpannable
                    )
                    binding.tvCountStatus.setText(spannable, TextView.BufferType.SPANNABLE)
                    kandangAdapter.submitList(it.data!!)
                }
                StatusResponse.FAILURE -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Failed to load the data ${it.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                StatusResponse.LOADING -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Loading...",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun loadDataKandangRehat(){
        mainViewModel.getKandangRehat().observe(this@MainActivity) {
            when(it.status){
                StatusResponse.SUCCESS -> {

                    val message = "Kamu memiliki ${it.data?.size.toString()} kandang rehat."
                    val textSpannable = "${it.data?.size.toString()} kandang rehat"
                    val spannable = FormatSpannable.formatSpannableBoldBlack(
                        sentence = message, textSpannable
                    )
                    binding.tvCountStatus.setText(spannable, TextView.BufferType.SPANNABLE)

                    kandangAdapter.submitList(it.data!!)
                }
                StatusResponse.FAILURE -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Failed to load the data ${it.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                StatusResponse.LOADING -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Loading...",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}