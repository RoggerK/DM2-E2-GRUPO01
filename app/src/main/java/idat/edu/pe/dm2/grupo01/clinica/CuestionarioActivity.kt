package idat.edu.pe.dm2.grupo01.clinica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import idat.edu.pe.dm2.grupo01.clinica.common.AppMessage
import idat.edu.pe.dm2.grupo01.clinica.common.MyApplication
import idat.edu.pe.dm2.grupo01.clinica.common.TypeMessage
import idat.edu.pe.dm2.grupo01.clinica.databinding.ActivityCuestionarioBinding

class CuestionarioActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCuestionarioBinding
    private var listaCuestionario = ArrayList<String>()
    private var listaSintomas = ArrayList<String>()
    private var listaServicios = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCuestionarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btResolver.setOnClickListener(this)
        binding.cbGustoOlfato.setOnClickListener(this)
        binding.cbTos.setOnClickListener(this)
        binding.cbGarganta.setOnClickListener(this)
        binding.cbCongestion.setOnClickListener(this)
        binding.cbFiebre.setOnClickListener(this)
        binding.cbNinguno.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view is CheckBox) {
            agregarSintomas(view)
        } else {
            resolverCuestionario()
        }
    }

    private fun resolverCuestionario() {
        if(validarFormulario()) {
            AppMessage.enviarMensaje(binding.root, "Se guardo el cuestionario", TypeMessage.SUCCESSFULL)
        }
    }

    private fun validarFormulario(): Boolean {
        if(!validarSintomas()) {
            AppMessage.enviarMensaje(binding.root, "Debes marcar que sintomas tienes", TypeMessage.DANGER)
            return false
        }

        if(!validarFiebre()) {
            AppMessage.enviarMensaje(binding.root, "Indica si es mayor a 37 grados la fiebre", TypeMessage.DANGER)
            return false
        }

        if(!validarCasa()) {
            AppMessage.enviarMensaje(binding.root, "Indica si vives solo", TypeMessage.DANGER)
            return false
        }

        if(!validarAdulto()) {
            AppMessage.enviarMensaje(binding.root, "Indica si vives con un mayor", TypeMessage.DANGER)
            return false
        }

        return true
    }

    private fun validarFiebre(): Boolean {
        var respuesta = true
        if (binding.radgFiebre.checkedRadioButtonId == -1) {
            respuesta = false
        }
        return respuesta
    }

    private fun validarCasa(): Boolean {
        var respuesta = true
        if (binding.radgCasa.checkedRadioButtonId == -1) {
            respuesta = false
        }
        return respuesta
    }

    private fun validarAdulto(): Boolean {
        var respuesta = true
        if (binding.radgAdulto.checkedRadioButtonId == -1) {
            respuesta = false
        }
        return respuesta
    }

    fun validarSintomas(): Boolean {
        var respuesta = false
        if (binding.cbGustoOlfato.isChecked || binding.cbTos.isChecked
            || binding.cbGarganta.isChecked || binding.cbCongestion.isChecked
            || binding.cbFiebre.isChecked || binding.cbNinguno.isChecked) {
            respuesta = true
        }
        return respuesta
    }

    private fun agregarSintomas(checkBox: CheckBox) {
        if(checkBox.isChecked) {
            listaSintomas.add(checkBox.text.toString())
            AppMessage.enviarMensaje(binding.root, "Marcaste " + checkBox.text.toString(), TypeMessage.INFO)
        } else {
            listaSintomas.remove(checkBox.text.toString())
            AppMessage.enviarMensaje(binding.root, "Desmarcaste " + checkBox.text.toString(), TypeMessage.INFO)
        }
    }

}