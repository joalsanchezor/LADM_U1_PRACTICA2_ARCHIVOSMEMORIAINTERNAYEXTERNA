package mx.tecnm.tepic.ladm_u1_practica2_archivosmemoriainternayexterna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.util.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val radio_group = findViewById<RadioGroup>(R.id.radioGroup)
        val radio_group2 = findViewById<RadioGroup>(R.id.radioGroup2)
        val guardar = findViewById<ImageView>(R.id.btn_nueva_nueva)
        var nombreArchivo = ""

        guardar.setOnClickListener {
            //ASIGNAR EL NOMBRE DE LA NOTA
                //OBTENER EL ID DEL RADIO BUTTON SELECCIONADO
            var tipoSeleccionado = radio_group2.indexOfChild(findViewById(radio_group2.checkedRadioButtonId)).toInt()
            if(tipoSeleccionado==0){
                //GENERAR ID DINÁMICO Y UNICO
                nombreArchivo = UUID.randomUUID().toString()
            }else{
                val nombre = EditText(this)
                nombre.inputType = InputType.TYPE_CLASS_TEXT
                nombre.setHint("Ingrese nombre del archivo")

                AlertDialog.Builder(this)
                    .setTitle("ATENCION")
                    .setMessage("Ingrese el nombre del archivo")
                    .setView(nombre)
                    .setPositiveButton("GUARDAR") { d, i ->
                        nombreArchivo = nombre.text.toString().toString()
                        d.dismiss()
                    }
                    .show()
                if(nombre.toString().isEmpty()){
                    //si el nombre está vacío, entonces se genera uno dinámico.
                    nombreArchivo = UUID.randomUUID().toString()
                }
            }

            //OBTENER EL ID DEL RADIO BUTTON SELECCIONADO
            var index = radio_group.indexOfChild(findViewById(radio_group.checkedRadioButtonId)).toInt()

            if(index==0) {
                if (guardarEnArchivoInterno(nombreArchivo.toString())) {
                    findViewById<EditText>(R.id.contenido).setText("")
                    Toast.makeText(this, "SE GUARDÓ LA NOTA EN MEMORIA INTERNA: "+nombreArchivo, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "ERROR AL GUARDAR LA NOTA", Toast.LENGTH_SHORT).show()
                }
            }else{
                if (guardarEnArchivoExterno(nombreArchivo.toString())) {
                    findViewById<EditText>(R.id.contenido).setText("")
                    Toast.makeText(this, "SE GUARDÓ LA NOTA EN MEMORIA EXTERNA: "+nombreArchivo, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "ERROR AL GUARDAR LA NOTA", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun guardarEnArchivoInterno(nombre:String) : Boolean{
        try {
            val archivo = OutputStreamWriter(openFileOutput(nombre+".txt", MODE_PRIVATE))
            var textoAGuardar = findViewById<EditText>(R.id.contenido).text.toString()

            archivo.write(textoAGuardar)
            archivo.flush()
            archivo.close()
            return true
        }catch (io: IOException){
            AlertDialog.Builder(this)
                .setTitle("¡ERROR!")
                .setMessage(io.message)
                .setPositiveButton("ACEPTAR") {dialog, exception->
                    dialog.dismiss()
                }
                .show()
            return false
        }
    }

    private fun guardarEnArchivoExterno(nombre:String) : Boolean{
        try {
            val raiz = getExternalFilesDir(null)
            val archivo = File(raiz?.absolutePath,nombre+".txt")
            var textoAGuardar = findViewById<EditText>(R.id.contenido).text.toString()

            val objescribir = OutputStreamWriter(FileOutputStream(archivo))

            objescribir.write(textoAGuardar)
            objescribir.flush()
            objescribir.close()
            return true
        }catch (io: IOException){
            AlertDialog.Builder(this)
                .setTitle("¡ERROR!")
                .setMessage(io.message)
                .setPositiveButton("ACEPTAR") {dialog, exception->
                    dialog.dismiss()
                }
                .show()
            return false
        }
    }

    /*fun solicitarNombreArchivo() {
        val nombre = EditText(this)
        nombre.inputType = InputType.TYPE_CLASS_TEXT
        nombre.setHint("Ingrese nombre del archivo")

        AlertDialog.Builder(this)
            .setTitle("ATENCION")
            .setMessage("Ingrese el nombre del archivo")
            .setView(nombre)
            .setPositiveButton("GUARDAR") { d, i ->
                generarCamposTextoDinamicos(nombre.text.toString().toInt())
                d.dismiss()
            }
            .setNegativeButton("CANCELAR"){d, i ->
                d.cancel()
            }
            .show()
    }*/


}