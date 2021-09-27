package mx.tecnm.tepic.ladm_u1_practica2_archivosmemoriainternayexterna

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    var titulo: String = ""
    var contenido: String = ""
    var notas = ArrayList<String>()
    var nombreNota: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val abrir = findViewById<ImageView>(R.id.btnBuscar)

        val nueva_nota = findViewById<ImageView>(R.id.new_nota)
        nueva_nota.setOnClickListener {
            var ventanaNotaNueva = Intent(this,MainActivity2::class.java)
            startActivity(ventanaNotaNueva)
        }

        abrir.setOnClickListener(){
            abrirNotas()
        }
    }

    private fun abrirNotas() {
        val buscar = EditText(this)
        buscar.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog.Builder(this)
            .setTitle("ATENCION")
            .setMessage("Nombre Nota:")
            .setView(buscar)
            .setPositiveButton("ABRIR") { d, i ->
                nombreNota = buscar.text.toString()+".txt"
                leerNotas(nombreNota)
            }
            .setNegativeButton("CANCELAR") { d, i ->
                d.cancel()
            }
            .show()
    }

    private fun leerNotas(nombreNota: String) {
        try {
            val archivo = BufferedReader(InputStreamReader(openFileInput(nombreNota)))
            contenido = archivo.readLine()
            archivo.close()
            crearNotas()
            notas.add(nombreNota)
        } catch (io: IOException) {
            AlertDialog.Builder(this)
                .setTitle("ERROR!")
                .setMessage(io.message)
                .setNegativeButton("ACEPTAR") { dialog, i ->
                    dialog.cancel()
                }
                .show()
        }
    }

    private fun crearNotas() {
        val notasTxt = TextView(this)
        notasTxt.setBackgroundColor(Color.YELLOW)
        notasTxt.setPadding(60, 60, 60, 60)
        notasTxt.setText(contenido)
        notasTxt.setTextColor(Color.BLUE)
        contenidoNotas.addView(notasTxt)
        /*val notasLista = ListView(this)
        notasLista.setBackgroundColor(Color.YELLOW)
        notasLista.addView(contenido)*/
    }
}