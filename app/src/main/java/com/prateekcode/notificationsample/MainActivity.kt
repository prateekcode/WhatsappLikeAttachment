package com.prateekcode.notificationsample

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.prateekcode.notificationsample.databinding.ActivityMainBinding
import com.prateekcode.whatsapplikeattachment.KeyboardPopup
import com.prateekcode.whatsapplikeattachment.adapter.GridMenuAdapter
import com.prateekcode.whatsapplikeattachment.widget.MenuEditText
import com.prateekcode.whatsapplikeattachment.widget.MenuRecyclerView

class MainActivity : AppCompatActivity(), MenuEditText.PopupListener, GridMenuAdapter.ItemClickListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var keyboardPopup: KeyboardPopup
    private lateinit var customRecyclerView: MenuRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        customRecyclerView = MenuRecyclerView(this)
        keyboardPopup = KeyboardPopup(
            this,
            binding.rootView,
            binding.messageEt,
            binding.messageEt,
            binding.menuContainer
        )

 //       initMenuView()

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
            initMenuView()
            //customRecyclerView.clickMenuListener(this)
        }
    }

    override fun onDestroy() {
        keyboardPopup.clear()
        super.onDestroy()
    }

    override fun getPopup(): PopupWindow {
        return keyboardPopup
    }

    private fun initMenuView(){
        val view = LayoutInflater
            .from(applicationContext)
            .inflate(com.prateekcode.whatsapplikeattachment.R.layout.menu_attachment, binding.rootView, false)
        view.findViewById<MenuRecyclerView>(R.id.rvMenu).apply {
            clickMenuListener(this@MainActivity)
        }
    }

    override fun onClick(position: Int, itemList: List<GridMenuAdapter.Menu>) {
        Toast.makeText(this, "Item is ${itemList[position].name}", Toast.LENGTH_SHORT).show()
    }


}