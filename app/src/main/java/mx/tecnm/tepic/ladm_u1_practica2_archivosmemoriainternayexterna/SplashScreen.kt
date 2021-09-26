package mx.tecnm.tepic.ladm_u1_practica2_archivosmemoriainternayexterna

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        /*
        * ESCONDER LA BARRA DE ESTADO Y PONERLO EN
        * PANTALLA COMPLETA
        * */
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        /*
        * Se utiliza el postDelayed para mostrar un mensaje
        * con tiempo de retardo.
        * */
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}