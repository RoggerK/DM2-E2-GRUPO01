package idat.edu.pe.dm2.grupo01.clinica

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import idat.edu.pe.dm2.grupo01.clinica.databinding.ActivityListarCuestionarioBinding

class ListarCuestionarioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListarCuestionarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListarCuestionarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listaCuestionario = intent
            .getSerializableExtra("listaCuestionario") as ArrayList<String>
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, listaCuestionario)

        binding.lvListarCuestionario.adapter = adapter
    }
}