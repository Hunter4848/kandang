package com.adit.kandang.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.adit.kandang.databinding.ActivityDetailBinding
import com.adit.kandang.model.data.remote.ResponseKandang

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private var dataKandang : ResponseKandang ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        dataKandang = intent.getSerializableExtra(ARGS_DETAILS_) as ResponseKandang

        with(binding){
            dataBind = dataKandang
            actionBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
                finish()
            }
        }
    }

    companion object {
        const val ARGS_DETAILS_ = "ARGS_DETAILS_"
    }
}