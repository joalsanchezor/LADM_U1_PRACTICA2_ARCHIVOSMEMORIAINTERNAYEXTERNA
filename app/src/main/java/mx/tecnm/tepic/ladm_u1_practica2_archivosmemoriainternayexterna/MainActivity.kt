package mx.tecnm.tepic.ladm_u1_practica2_archivosmemoriainternayexterna

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nueva_nota = findViewById<ImageView>(R.id.new_nota)
        nueva_nota.setOnClickListener {
            var ventanaNotaNueva = Intent(this,MainActivity2::class.java)
            startActivity(ventanaNotaNueva)
        }
    }
}