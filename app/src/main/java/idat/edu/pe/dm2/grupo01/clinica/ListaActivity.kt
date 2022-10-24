package idat.edu.pe.dm2.grupo01.clinica

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import idat.edu.pe.dm2.grupo01.clinica.databinding.ActivityListarinformacionBinding

class ListaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListarinformacionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListarinformacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listapersonas = intent.getSerializableExtra("listausuario")
                as ArrayList<String>
        val adapter = ArrayAdapter(
            this, R.layout.simple_list_item_1,
            listapersonas
        )
        binding.lvpersonas.adapter = adapter
    }
}