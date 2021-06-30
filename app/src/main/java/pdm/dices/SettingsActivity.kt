package pdm.dices

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import pdm.dices.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var activitySettingsBinding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activitySettingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(activitySettingsBinding.root)
    }

    fun onSave(view: View) {
        val shared = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)
        var dicesAmount = shared.getInt(getString(R.string.dices_amount),1)
        var facesAmount = shared.getInt(getString(R.string.faces_amount),6)

        if (activitySettingsBinding.etDicesAmount.text.toString() != ""){
            dicesAmount = activitySettingsBinding.etDicesAmount.text.toString().toInt()
        }

        if (activitySettingsBinding.etFacesAmount.text.toString() != ""){
            facesAmount = activitySettingsBinding.etFacesAmount.text.toString().toInt()
        }

        with(shared.edit()) {
            putInt(getString(R.string.dices_amount), dicesAmount)
            putInt(getString(R.string.faces_amount), facesAmount)
            apply()
        }
        finish()
    }
}