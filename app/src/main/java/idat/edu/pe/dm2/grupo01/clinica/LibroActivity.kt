package idat.edu.pe.dm2.grupo01.clinica

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_libro.*
import kotlinx.android.synthetic.main.list_item.*


class LibroActivity : AppCompatActivity() {
    private val title = arrayOf<String>("Anatomia", "Fundamentos de Biofisica","Fisica y Biofisica",
    "Cefalea ","Miologìa","Osteologìa","Ostologia Humana","Atlas de Ostologia","Osteopatìa","Diagnòstico Osteopàtico")
    private val image = arrayOf<Int>(
        R.drawable.anatomia,
        R.drawable.libro2,
        R.drawable.libro3,
        R.drawable.libro4,
        R.drawable.libro5,
        R.drawable.libro6,
        R.drawable.libro7,
        R.drawable.libro8,
        R.drawable.libro9,
        R.drawable.libro10,


    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.apply {

            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        setContentView(R.layout.activity_libro)

        list_view.adapter = ListAdapter(this,image,title)
        list_view.setOnItemClickListener { parent, view, position, id ->

            if (position == 0 ){

                Toast.makeText(this,"HAS SELECCIONADO ESTE LIBRO",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
