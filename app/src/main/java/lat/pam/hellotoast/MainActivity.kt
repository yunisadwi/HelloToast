package lat.pam.hellotoast

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import lat.pam.hellotoastjumat.NameViewModel


class MainActivity : AppCompatActivity() {
    private var mCount = 0;
    private val model: NameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mShowCount = findViewById<TextView>(R.id.show_count)
        val buttonCountUp = findViewById<Button>(R.id.button_count)
        val buttonBrowser = findViewById<Button>(R.id.button_browser)
        val buttonToast = findViewById<Button>(R.id.button_toast)
        val buttonSwitchPage = findViewById<Button>(
            R.id.button_switchpage

        )

        mCount = model.currentName.value?:0
        buttonCountUp.setOnClickListener(View.OnClickListener {
            mCount = mCount + 1
            if (mShowCount != null)
            //mShowCount.text = mCount.toString()
                model.currentName.setValue(mCount)
        })

        buttonBrowser.setOnClickListener(View.OnClickListener {
            val intentbrowse = Intent(Intent.ACTION_VIEW)
            intentbrowse.setData(Uri.parse("https://www.google.com/"))
            startActivity(intentbrowse)
        })

        buttonToast.setOnClickListener(View.OnClickListener {
            val tulisan: String = mShowCount?.text.toString()
            val toast: Toast =
                Toast.makeText(this, "Angka yang dimunculkan " + tulisan, Toast.LENGTH_LONG)
            toast.show()
        })


        buttonSwitchPage.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        })

        // Create the observer which updates the UI.
        val nameObserver = Observer<Int> { newName ->
            // Update the UI, in this case, a TextView.
            mShowCount.text = newName.toString()
        }


        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.currentName.observe(this, nameObserver)
    }

}

