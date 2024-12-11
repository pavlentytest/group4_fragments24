package com.example.myapplication

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MyDialog : DialogFragment() {
    // 1 - onCreateView - для своей разметки окна
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.message))
            .setNegativeButton(getString(R.string.ok)){ _,_ ->}
            .setPositiveButton(getString(R.string.cancel)){ _,_ ->}
            .create()
    }
}



class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var fm: FragmentManager
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener(this)
        findViewById<Button>(R.id.button2).setOnClickListener(this)
        fm = supportFragmentManager
        MyDialog().show(fm,null)
        val fragments_arrays = arrayOf(FirstFragment(),SecondFragment())
        /*val job: Job = CoroutineScope(Dispatchers.Default).launch {
            while(true) {
                delay(3000)
                val f = fragments_arrays[counter++%2]  // 0 или 1
                val transaction: FragmentTransaction = fm.beginTransaction()
                transaction.replace(R.id.fragmentContainerView,f)
                transaction.commit()
            }
        }
        job.cancel()
        */

    }


    fun changeFragment(f: Fragment) {
        val transaction: FragmentTransaction = fm.beginTransaction()
        val bundle = Bundle()
        bundle.putString("param1","123123")
        f.arguments = bundle
        transaction.replace(R.id.fragmentContainerView,f)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onClick(v: View?) {
       if(v?.id == R.id.button) {
           changeFragment(FirstFragment())
       } else {
            changeFragment(SecondFragment())
       }
    }




}