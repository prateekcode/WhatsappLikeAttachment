package com.prateekcode.notificationsample

import android.os.Bundle
import android.util.Log
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.prateekcode.notificationsample.databinding.ActivityMainBinding
import com.prateekcode.whatsapplikeattachment.KeyboardPopup
import com.prateekcode.whatsapplikeattachment.adapter.GridMenuAdapter
import com.prateekcode.whatsapplikeattachment.widget.MenuEditText
import com.prateekcode.whatsapplikeattachment.widget.MenuRecyclerView

class MainActivity : AppCompatActivity(), MenuEditText.PopupListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var keyboardPopup: KeyboardPopup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        keyboardPopup = KeyboardPopup(
            this,
            binding.rootView,
            binding.messageEt,
            binding.messageEt,
            binding.menuContainer
        )


        binding.apply {
            messageEt.popupListener = this@MainActivity
            menuChat.setOnClickListener {
                toggle()
                //Log.d(TAG, "Clicked position and name ${keyboardPopup.clickedItem()}")
            }
        }
    }

    companion object {
        const val TAG = "MainActivityTag"
    }

    private fun toggle() {
        if (keyboardPopup.isShowing) {
            keyboardPopup.dismiss()
        } else {
            keyboardPopup.show()
        }
    }

    override fun onDestroy() {
        keyboardPopup.clear()
        super.onDestroy()
    }

    override fun getPopup(): PopupWindow {
        return keyboardPopup
    }


}