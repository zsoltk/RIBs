package com.badoo.ribs.example.rib.simple2

import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.customisation.inflate
import com.badoo.ribs.example.R

interface Simple2View : RibView {

    interface Factory : ViewFactory<Nothing?, Simple2View>
}


class Simple2ViewImpl private constructor(
    override val androidView: ViewGroup
) : Simple2View {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_foobar
    ) : Simple2View.Factory {
        override fun invoke(deps: Nothing?): (ViewGroup) -> Simple2View = {
            Simple2ViewImpl(
                inflate(it, layoutRes)
            )
        }
    }

    private val text: TextView = androidView.findViewById(R.id.foobar_debug)
    private val checkButton: Button = androidView.findViewById(R.id.foobar_button_check_permissions)
    private val requestButton: Button = androidView.findViewById(R.id.foobar_button_request_permissions)

    init {
        text.text = "Simple2ViewImpl"
        checkButton.setOnClickListener { Toast.makeText(androidView.context, "checkButton clicked", Toast.LENGTH_SHORT).show()  }
        requestButton.setOnClickListener { Toast.makeText(androidView.context, "requestButton clicked", Toast.LENGTH_SHORT).show()  }
    }
}
