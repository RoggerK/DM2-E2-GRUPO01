package idat.edu.pe.dm2.grupo01.clinica

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import idat.edu.pe.dm2.grupo01.clinica.common.AppMessage
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
        binding.btIrListaCuestionario.setOnClickListener(this)

        binding.cbGustoOlfato.setOnClickListener(this)
        binding.cbTos.setOnClickListener(this)
        binding.cbGarganta.setOnClickListener(this)
        binding.cbCongestion.setOnClickListener(this)
        binding.cbFiebre.setOnClickListener(this)
        binding.cbNinguno.setOnClickListener(this)

        binding.cbLuz.setOnClickListener(this)
        binding.cbAgua.setOnClickListener(this)
        binding.cbInternet.setOnClickListener(this)
        binding.cbCable.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view is CheckBox) {
            if (view.id == binding.cbGustoOlfato.id
                || view.id == binding.cbTos.id
                || view.id == binding.cbGarganta.id
                || view.id == binding.cbCongestion.id
                || view.id == binding.cbFiebre.id
                || view.id == binding.cbNinguno.id
            ) {
                agregarSintomas(view)
            } else {
                agregarServicios(view)
            }
        } else {
            when (view.id) {
                binding.btResolver.id -> resolverCuestionario()
                binding.btIrListaCuestionario.id -> irListaCuestionario()
            }
        }
    }

    private fun resolverCuestionario() {
        if (validarFormulario()) {
            val datosCuestionario = "Sintomas:\n${listaSintomas.toString()}\n" +
                    "Fiebre mayor a 37: ${obtenerResFiebre()}\n" +
                    "Vive solo en casa: ${obtenerResCasa()}\n" +
                    "Vive con un adulto mayor: ${obtenerResAdulto()}\n" +
                    "Servicios en casa:\n${listaServicios.toString()}"
            listaCuestionario.add(datosCuestionario)
            limpiarFormulario()
            AppMessage.enviarMensaje(
                binding.root,
                "Datos guardados con exito",
                TypeMessage.SUCCESSFULL
            )
        }
    }

    private fun irListaCuestionario() {
        val intentLista = Intent(
            this, ListarCuestionarioActivity::class.java
        ).apply {
            putExtra("listaCuestionario", listaCuestionario)
        }
        startActivity(intentLista)
    }

    private fun limpiarFormulario() {
        listaSintomas.clear()
        listaServicios.clear()
        binding.cbGustoOlfato.isChecked = false
        binding.cbTos.isChecked = false
        binding.cbGarganta.isChecked = false
        binding.cbCongestion.isChecked = false
        binding.cbFiebre.isChecked = false
        binding.cbNinguno.isChecked = false
        binding.cbLuz.isChecked = false
        binding.cbAgua.isChecked = false
        binding.cbInternet.isChecked = false
        binding.cbCable.isChecked = false

        binding.radgFiebre.clearCheck()
        binding.radgCasa.clearCheck()
        binding.radgAdulto.clearCheck()
    }

    private fun validarFormulario(): Boolean {
        if (!validarSintomas()) {
            AppMessage.enviarMensaje(binding.root, "Marca que sintomas tienes", TypeMessage.INFO)
            return false
        }

        if (!validarFiebre()) {
            AppMessage.enviarMensaje(
                binding.root,
                "Indica si es mayor a 37 grados la fiebre",
                TypeMessage.DANGER
            )
            return false
        }

        if (!validarCasa()) {
            AppMessage.enviarMensaje(binding.root, "Indica si vives solo", TypeMessage.DANGER)
            return false
        }

        if (!validarAdulto()) {
            AppMessage.enviarMensaje(
                binding.root,
                "Indica si vives con un mayor",
                TypeMessage.DANGER
            )
            return false
        }

        if (!validarServicios()) {
            AppMessage.enviarMensaje(binding.root, "Marca que servicios tienes", TypeMessage.INFO)
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
            || binding.cbFiebre.isChecked || binding.cbNinguno.isChecked
        ) {
            respuesta = true
        }
        return respuesta
    }

    fun validarServicios(): Boolean {
        var respuesta = false
        if (binding.cbLuz.isChecked || binding.cbAgua.isChecked
            || binding.cbInternet.isChecked || binding.cbCable.isChecked
        ) {
            respuesta = true
        }
        return respuesta
    }

    private fun obtenerResFiebre(): String {
        var respuesta = ""
        when (binding.radgFiebre.checkedRadioButtonId) {
            binding.radbFiebreSi.id -> respuesta = binding.radbFiebreSi.text.toString()
            binding.radbFiebreNo.id -> respuesta = binding.radbFiebreNo.text.toString()
        }
        return respuesta
    }

    private fun obtenerResCasa(): String {
        var respuesta = ""
        when (binding.radgCasa.checkedRadioButtonId) {
            binding.radbCasaSi.id -> respuesta = binding.radbCasaSi.text.toString()
            binding.radbCasaNo.id -> respuesta = binding.radbCasaNo.text.toString()
        }
        return respuesta
    }

    private fun obtenerResAdulto(): String {
        var respuesta = ""
        when (binding.radgAdulto.checkedRadioButtonId) {
            binding.radbAdultoSi.id -> respuesta = binding.radbAdultoSi.text.toString()
            binding.radbAdultoNo.id -> respuesta = binding.radbAdultoNo.text.toString()
        }
        return respuesta
    }

    private fun agregarSintomas(checkBox: CheckBox) {
        if (checkBox.isChecked) {
            listaSintomas.add(checkBox.text.toString())
        } else {
            listaSintomas.remove(checkBox.text.toString())
        }
    }

    private fun agregarServicios(checkBox: CheckBox) {
        if (checkBox.isChecked) {
            listaServicios.add(checkBox.text.toString())
        } else {
            listaServicios.remove(checkBox.text.toString())
        }
    }

}