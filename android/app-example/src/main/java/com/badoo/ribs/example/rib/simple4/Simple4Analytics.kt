package com.badoo.ribs.example.rib.simple4

import android.util.Log
import io.reactivex.functions.Consumer

class Simple4Analytics : Consumer<Simple4View.Event> {

    override fun accept(event: Simple4View.Event) {
        Log.d("Simple4Analytics", event.toString())
    }
}
