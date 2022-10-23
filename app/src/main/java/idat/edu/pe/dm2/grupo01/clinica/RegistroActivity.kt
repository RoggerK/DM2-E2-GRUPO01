package idat.edu.pe.dm2.grupo01.clinica

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import idat.edu.pe.dm2.grupo01.clinica.common.AppMessage
import idat.edu.pe.dm2.grupo01.clinica.common.TypeMessage
import idat.edu.pe.dm2.grupo01.clinica.databinding.ActivityRegistroBinding

class RegistroActivity: AppCompatActivity(), OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var binding : ActivityRegistroBinding
    private val listaUsuarios = ArrayList<String>()
    private val listaPreferencias = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun irLista(){
        val intentLista = Intent(
            this, ListaActivity::class.java
        ).apply {
            putExtra("listausuario", listaUsuarios) }
        startActivity(intentLista)
    }

    fun validarTextos() : Boolean {
        var respuesta = true
        if(binding.etnombre.text.toString().trim().isEmpty()){
            binding.etnombre.isFocusableInTouchMode = true
            binding.etnombre.requestFocus()
            respuesta = false
        } else if(binding.etapellidos.text.toString().trim().isEmpty()){
            binding.etapellidos.isFocusableInTouchMode = true
            binding.etapellidos.requestFocus()
            respuesta = false
        } else if(binding.etdni.text.toString().trim().isEmpty()){
            binding.etdni.isFocusableInTouchMode = true
            binding.etdni.requestFocus()
            respuesta = false
        } else if(binding.etcorreo.text.toString().trim().isEmpty()){
            binding.etcorreo.isFocusableInTouchMode = true
            binding.etcorreo.requestFocus()
            respuesta = false
        } else if(binding.etcontraseA.text.toString().trim().isEmpty()) {
            binding.etcontraseA.isFocusableInTouchMode = true
            binding.etcontraseA.requestFocus()
            respuesta = false
        }
        return respuesta
    }

    fun validarGenero(): Boolean{
        var respuesta = true
        if(binding.radioGroup.checkedRadioButtonId == -1){
            respuesta = false
        }
        return respuesta
    }

    fun validarHobbies() : Boolean{
        var respuesta = true
        if(binding.cbdeporte.isChecked || binding.cbdibujo.isChecked || binding.cbotros.isChecked){
            respuesta = false
        }
        return respuesta
    }

    private fun agregarQuitarPreferencias(checkBox: CheckBox) {
        if(checkBox.isChecked)
            listaPreferencias.add(checkBox.text.toString())
        else
            listaPreferencias.remove(checkBox.text.toString())
    }

    fun obtenerGeneroSelccionado(): String{
        var genero = ""
        when(binding.radioGroup.checkedRadioButtonId){
            R.id.rbmasculino -> genero = binding.rbmasculino.text.toString()
            R.id.rbfemenino -> genero = binding.rbfemenino.text.toString()
        }
        return genero
    }

    fun validarFormulario(): Boolean{
        var respuesta = false
        if(!validarTextos()){
            AppMessage.enviarMensaje(binding.root,"Ingrese los datos requeridos", TypeMessage.DANGER)
        }else if(!validarHobbies()){
            AppMessage.enviarMensaje(binding.root,"Seleccione sus Hobbies, por favor", TypeMessage.DANGER)
        }else if(!validarGenero()){
            AppMessage.enviarMensaje(binding.root,"Seleccione un género, por favor", TypeMessage.DANGER)
        }else{
            respuesta = true
        }
        return respuesta
    }















    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}