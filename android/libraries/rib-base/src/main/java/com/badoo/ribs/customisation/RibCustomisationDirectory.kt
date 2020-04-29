package com.badoo.ribs.customisation

import com.badoo.ribs.core.Node
import kotlin.reflect.KClass

interface RibCustomisationDirectory {

    val parent: RibCustomisationDirectory?

    fun <T : Node<*>> getSubDirectory(key: KClass<T>) : RibCustomisationDirectory?

    fun <T : Node<*>> getSubDirectoryOrSelf(key: KClass<T>) : RibCustomisationDirectory =
        getSubDirectory(key) ?: this

    fun <T : RibCustomisation> get(key: KClass<T>) : T?

    fun <T : RibCustomisation> getRecursively(key: KClass<T>) : T?

    fun <T : RibCustomisation> getRecursivelyOrDefault(default: T) : T =
        get(default::class) ?: parent?.get(default::class) ?: default
}
