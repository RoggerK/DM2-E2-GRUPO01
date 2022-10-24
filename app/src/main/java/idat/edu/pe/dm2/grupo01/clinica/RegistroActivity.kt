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

class RegistroActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityRegistroBinding
    private val listaUsuarios = ArrayList<String>()
    private val listaPreferencias = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAcceder.setOnClickListener(this)
        binding.btnLista.setOnClickListener(this)
        binding.cbdeporte.setOnClickListener(this)
        binding.cbdibujo.setOnClickListener(this)
        binding.cbotros.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view is CheckBox) {
            agregarQuitarPreferencias(view)
        } else {
            when (view.id) {
                R.id.btnLista -> irLista()
                R.id.btnAcceder -> registrarPersona()
            }
        }
    }

    private fun registrarPersona() {
        if (validarFormulario()) {
            var infoPersona =
                binding.etnombre.text.toString() + " " + "\n" + binding.etapellidos.text.toString() + " " + "\n" +
                        binding.etdni.text.toString() + " " + "\n" + binding.etcorreo.text.toString() + " " + "\n" + binding.etcontraseA.text.toString() + " " + "\n" +
                        obtenerGeneroSelccionado() + " " + "\n" + listaPreferencias.toString()
            listaUsuarios.add(infoPersona)
            AppMessage.enviarMensaje(
                binding.root,
                "Persona correctamente registrada",
                TypeMessage.SUCCESSFULL
            )
            setearFormulario()
        }
    }

    private fun setearFormulario() {
        listaPreferencias.clear()
        binding.etnombre.setText("")
        binding.etapellidos.setText("")
        binding.etdni.setText("")
        binding.etcorreo.setText("")
        binding.etcontraseA.setText("")
        binding.cbdeporte.isChecked = false
        binding.cbdibujo.isChecked = false
        binding.cbotros.isChecked = false
        binding.radioGroup.clearCheck()
        binding.etnombre.isFocusableInTouchMode = true
        binding.etnombre.requestFocus()

    }

    private fun irLista() {
        val intentLista = Intent(
            this, ListaActivity::class.java
        ).apply {
            putExtra("listausuario", listaUsuarios)
        }
        startActivity(intentLista)
    }

    fun validarTextos(): Boolean {
        var respuesta = true
        if (binding.etnombre.text.toString().trim().isEmpty()) {
            binding.etnombre.isFocusableInTouchMode = true
            binding.etnombre.requestFocus()
            respuesta = false
        } else if (binding.etapellidos.text.toString().trim().isEmpty()) {
            binding.etapellidos.isFocusableInTouchMode = true
            binding.etapellidos.requestFocus()
            respuesta = false
        } else if (binding.etdni.text.toString().trim().isEmpty()) {
            binding.etdni.isFocusableInTouchMode = true
            binding.etdni.requestFocus()
            respuesta = false
        } else if (binding.etcorreo.text.toString().trim().isEmpty()) {
            binding.etcorreo.isFocusableInTouchMode = true
            binding.etcorreo.requestFocus()
            respuesta = false
        } else if (binding.etcontraseA.text.toString().trim().isEmpty()) {
            binding.etcontraseA.isFocusableInTouchMode = true
            binding.etcontraseA.requestFocus()
            respuesta = false
        }
        return respuesta
    }

    fun validarGenero(): Boolean {
        var respuesta = true
        if (binding.radioGroup.checkedRadioButtonId == -1) {
            respuesta = false
        }
        return respuesta
    }

    fun validarHobbies(): Boolean {
        var respuesta = false
        if (binding.cbdeporte.isChecked || binding.cbdibujo.isChecked || binding.cbotros.isChecked) {
            respuesta = true
        }
        return respuesta
    }

    private fun agregarQuitarPreferencias(checkBox: CheckBox) {
        if (checkBox.isChecked)
            listaPreferencias.add(checkBox.text.toString())
        else
            listaPreferencias.remove(checkBox.text.toString())
    }

    fun obtenerGeneroSelccionado(): String {
        var genero = ""
        when (binding.radioGroup.checkedRadioButtonId) {
            R.id.rbmasculino -> genero = binding.rbmasculino.text.toString()
            R.id.rbfemenino -> genero = binding.rbfemenino.text.toString()
        }
        return genero
    }

    fun validarFormulario(): Boolean {
        var respuesta = false
        if (!validarTextos()) {
            AppMessage.enviarMensaje(
                binding.root,
                "Ingrese los datos requeridos",
                TypeMessage.DANGER
            )
        } else if (!validarHobbies()) {
            AppMessage.enviarMensaje(
                binding.root,
                "Seleccione sus Hobbies, por favor",
                TypeMessage.DANGER
            )
        } else if (!validarGenero()) {
            AppMessage.enviarMensaje(
                binding.root,
                "Seleccione un género, por favor",
                TypeMessage.DANGER
            )
        } else {
            respuesta = true
        }
        return respuesta
    }

}