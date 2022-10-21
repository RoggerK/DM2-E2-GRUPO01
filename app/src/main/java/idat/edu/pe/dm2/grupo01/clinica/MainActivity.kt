package idat.edu.pe.dm2.grupo01.clinica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import idat.edu.pe.dm2.grupo01.clinica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}