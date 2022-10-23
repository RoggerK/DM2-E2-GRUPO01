package idat.edu.pe.dm2.grupo01.clinica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import idat.edu.pe.dm2.grupo01.clinica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnregistro.setOnClickListener{

            val intent:Intent = Intent(this, InformacionActivity::class.java)
            startActivity(intent)
        }

        binding.btnformulario.setOnClickListener{

            val intent:Intent = Intent(this, CuestionarioActivity::class.java)
            startActivity(intent)
        }

        binding.btnlistado.setOnClickListener{

            val intent:Intent = Intent(this, LibroActivity::class.java)
            startActivity(intent)
        }
    }
}