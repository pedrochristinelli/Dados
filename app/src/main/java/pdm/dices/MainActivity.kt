package pdm.dices

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import pdm.dices.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var randomGenerator: Random

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_settings -> {
                openSettingsActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun openSettingsActivity() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun onClick(view: View) {
        val shared = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)
        randomGenerator = Random(System.currentTimeMillis())
        val dicesAmount = shared.getInt(getString(R.string.dices_amount), 1)
        val facesAmount = shared.getInt(getString(R.string.faces_amount), 6)

        activityMainBinding.llDices.removeAllViews()
        var resultNumbers = ""
        for (i in 1..dicesAmount) {
            val result: Int = randomGenerator.nextInt(facesAmount)+1;
            val resultImage = "dice_$result"
            resultNumbers += " $result"

            val iv = ImageView(this)
            iv.setImageResource(resources.getIdentifier(resultImage, "drawable", packageName))

            activityMainBinding.llDices.addView(iv)
        }
        activityMainBinding.resultadoTv.text = activityMainBinding.resultadoTv.text.let { "O resultado da rolagem é:$resultNumbers" }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return true
    }
}