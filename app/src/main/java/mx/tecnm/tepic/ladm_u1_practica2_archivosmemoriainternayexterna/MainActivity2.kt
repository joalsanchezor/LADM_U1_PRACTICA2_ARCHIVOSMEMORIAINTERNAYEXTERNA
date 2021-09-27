package mx.tecnm.tepic.ladm_u1_practica2_archivosmemoriainternayexterna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.io.IOException
import java.io.OutputStreamWriter

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val radio_group = findViewById<RadioGroup>(R.id.radioGroup)

        val guardar = findViewById<ImageView>(R.id.btn_nueva_nueva)
        guardar.setOnClickListener {
            //OBTENER EL ID DEL RADIO BUTTON SELECCIONADO
            var index = radio_group.indexOfChild(findViewById(radio_group.checkedRadioButtonId)).toInt()

            if(index==0) {
                if (guardarEnArchivoInterno()) {
                    findViewById<EditText>(R.id.contenido).setText("")
                    Toast.makeText(this, "SE GUARDÓ LA NOTA", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "ERROR AL GUARDAR LA NOTA", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "MEMORIA EXTERNA", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun guardarEnArchivoInterno() : Boolean{
        try {
            val archivo = OutputStreamWriter(openFileOutput("archivo.txt", MODE_PRIVATE))
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


}