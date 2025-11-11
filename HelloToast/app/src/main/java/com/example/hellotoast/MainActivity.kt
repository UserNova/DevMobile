package com.example.hellotoast
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // Déclaration d'une variable pour stocker la valeur du compteur
    private var count = 0

    // Référence vers le composant TextView
    private var textCount: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // lie le XML au code

        // Liaison des composants XML avec le code Java
        textCount = findViewById<TextView?>(R.id.text_count)
        val buttonToast = findViewById<Button?>(R.id.button_toast)
        val buttonCount = findViewById<Button?>(R.id.button_count)

        // Lorsque l'utilisateur clique sur "Afficher un message"
        buttonToast.setOnClickListener(View.OnClickListener { v: View? ->
            Toast.makeText(this, "Bonjour !", Toast.LENGTH_SHORT).show()
        })

        // Lorsque l'utilisateur clique sur "Incrémenter le compteur"
        buttonCount.setOnClickListener(View.OnClickListener { v: View? ->
            count++ // ajoute 1
            textCount!!.setText(count.toString()) // affiche la nouvelle valeur
        })
    }
}