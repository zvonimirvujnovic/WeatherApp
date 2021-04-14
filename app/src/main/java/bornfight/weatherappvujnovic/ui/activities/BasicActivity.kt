package bornfight.weatherappvujnovic.ui.activities

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bornfight.weatherappvujnovic.R
import kotlinx.android.synthetic.main.message_dialog.*

open class BasicActivity : AppCompatActivity() {
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
    }

    fun showLoadingDialog() {
        dialog = Dialog(this@BasicActivity)
        with(dialog) {
            setContentView(R.layout.loading_dialog)
            setCancelable(false)
            show()
        }
    }

    fun dismissDialog() {
        if (this::dialog.isInitialized) {
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }
    }

    fun showMessageDialog(text: String) {
        dialog = Dialog(this@BasicActivity)
        with(dialog) {
            setContentView(R.layout.message_dialog)
            setCanceledOnTouchOutside(true);
            dialog_message.text = text
            ok_text_btn.setOnClickListener {
                dialog.dismiss()
            }
            show()
        }
    }
}