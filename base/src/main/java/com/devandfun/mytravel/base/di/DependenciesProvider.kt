package com.devandfun.mytravel.base.di

abstract class DependenciesProvider<T>: Destroyable {
    protected var instance: T? = null

    fun getExistingInstance(): T? {
        return instance
    }

    override fun destroy() {
        instance = null
    }
}