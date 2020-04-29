package com.badoo.ribs.customisation

import com.badoo.ribs.core.Node
import kotlin.reflect.KClass

interface MutableRibCustomisationDirectory : RibCustomisationDirectory {

    fun <T : Node<*>> putSubDirectory(key: KClass<T>, value: RibCustomisationDirectory)

    fun <T : RibCustomisation> put(key: KClass<T>, value: T)
}
