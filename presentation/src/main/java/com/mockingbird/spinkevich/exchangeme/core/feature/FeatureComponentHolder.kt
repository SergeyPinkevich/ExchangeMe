package com.mockingbird.spinkevich.exchangeme.core.feature

import timber.log.Timber
import java.util.concurrent.ConcurrentHashMap

object FeatureComponentHolder {

    private val linkCounter = ConcurrentHashMap<Int, Int>()
    private val components = ConcurrentHashMap<Int, Any>()

    @Synchronized
    fun <C> getOrCreate(companion: FeatureComponentCompanion<C>): C where C : Any {
        val hash = companion::class.hashCode()

        val currentLinkCount = linkCounter[hash] ?: 0
        val newLinkCount = currentLinkCount + 1
        linkCounter[hash] = newLinkCount
        Timber.d("Component with companion $companion link count $newLinkCount")

        return components.getOrPut(hash) { companion.createComponent() } as C
    }

    @Synchronized
    fun <C> destroyIfNeeded(companion: FeatureComponentCompanion<C>) where C : Any {
        val hash = companion::class.hashCode()

        val currentLinkCount = linkCounter[hash] ?: 0
        val newLinkCount = Math.max(currentLinkCount - 1, 0)
        linkCounter[hash] = newLinkCount
        Timber.d("Component with companion $companion link count $newLinkCount")

        if (newLinkCount == 0) {
            components.remove(hash)
            Timber.d("Component with companion was removed")
        }
    }
}