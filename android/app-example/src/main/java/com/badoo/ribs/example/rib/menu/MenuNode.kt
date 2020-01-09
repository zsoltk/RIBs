package com.badoo.ribs.example.rib.menu

import android.os.Bundle
import android.view.ViewGroup
import com.badoo.ribs.core.Node

class MenuNode(
    viewFactory: ((ViewGroup) -> MenuView?)?,
    interactor: MenuInteractor,
    savedInstanceState: Bundle?
) : Node<MenuView>(
    savedInstanceState = savedInstanceState,
    identifier = object : Menu {},
    viewFactory = viewFactory,
    router = null,
    interactor = interactor
), Menu.Workflow {

//    override val screenName: String
//        get() = "SCREEN_NAME_MENU"

}
