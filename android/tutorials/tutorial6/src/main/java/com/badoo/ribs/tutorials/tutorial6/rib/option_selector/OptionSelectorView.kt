package com.badoo.ribs.tutorials.tutorial6.rib.option_selector

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import com.badoo.mvicore.ModelWatcher
import com.badoo.mvicore.modelWatcher
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.tutorials.tutorial6.R
import com.badoo.ribs.tutorials.tutorial6.rib.option_selector.OptionSelectorView.Event
import com.badoo.ribs.tutorials.tutorial6.rib.option_selector.OptionSelectorView.ViewModel
import com.badoo.ribs.tutorials.tutorial6.rib.option_selector.OptionSelector
import com.badoo.ribs.tutorials.tutorial6.util.Lexem
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer


interface OptionSelectorView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event {
        data class ConfirmSelectionButtonClicked(val selectionIndex: Int) : Event()
    }

    data class ViewModel(
        val options: List<Lexem>,
        val selectionIndex: Int = -1
    )
}

class OptionSelectorViewImpl private constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, private val events: PublishRelay<Event>
) : ConstraintLayout(context, attrs, defStyle),
    OptionSelectorView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
    ) : this(context, attrs, defStyle, PublishRelay.create<Event>())

    override val androidView = this
    private val radioGroup: RadioGroup by lazy { findViewById<RadioGroup>(R.id.more_options_radio_group) }
    private val confirmButton: Button by lazy { findViewById<Button>(R.id.more_options_confirm_selection) }

    override fun onFinishInflate() {
        super.onFinishInflate()
        confirmButton.setOnClickListener { events.accept(
            Event.ConfirmSelectionButtonClicked(radioGroup.checkedIndex))
        }
    }

    private val RadioGroup.checkedIndex: Int
        get() {
            val checkedRadio = findViewById<RadioButton>(checkedRadioButtonId)
            return indexOfChild(checkedRadio)
        }

    private val watcher: ModelWatcher<ViewModel> = modelWatcher {
        watch(ViewModel::options) {
            createRadios(it)
        }

        watch(ViewModel::selectionIndex) {
            radioGroup.check(
                radioGroup.getChildAt(it)?.id ?: -1
            )
        }
    }

    fun createRadios(options: List<Lexem>) {
        radioGroup.removeAllViews()

        options.forEach { lexem ->
            RadioButton(context).apply {
                text = lexem.resolve(context)
                id = View.generateViewId()
                radioGroup.addView(this)
            }
        }
    }

    override fun accept(vm: ViewModel) {
        watcher.invoke(vm)
    }
}
