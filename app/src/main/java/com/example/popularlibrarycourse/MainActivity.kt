package com.example.popularlibrarycourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.popularlibrarycourse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IView {
    companion object {
        const val ARG_COUNTERS = "counters"
    }
    private val presenter = Presenter(view = this)
    private var vb: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.btnCounter1?.setOnClickListener {
            presenter.count(it.id)
        }
        vb?.btnCounter2?.setOnClickListener {
            presenter.count(it.id)
        }
        vb?.btnCounter3?.setOnClickListener {
            presenter.count(it.id)
        }

        presenter.init()
    }
    override fun showCounter1(counter: String) {
        vb?.btnCounter1?.text = counter
    }
    override fun showCounter2(counter: String) {
        vb?.btnCounter2?.text = counter
    }
    override fun showCounter3(counter: String) {
        vb?.btnCounter3?.text = counter
    }

//    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
//        super.onSaveInstanceState(outState, outPersistentState)
//        outState.putIntArray(ARG_COUNTERS, counters.toIntArray())
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putIntArray(ARG_COUNTERS, counters.toIntArray())
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        val countersArray = savedInstanceState.getIntArray(ARG_COUNTERS)
//        countersArray?.toList()?.let {
//            counters.clear()
//            counters.addAll(it)
//        }
//        initViews()
//    }
}