package com.example.dogexplorer.utils

import android.util.Log
import timber.log.Timber


class ProductionTree : Timber.Tree() {
    override fun isLoggable(tag: String?, priority: Int) =
        priority == Log.ERROR || priority == Log.WARN

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) =
        when (priority) {
            Log.ERROR -> LogUtils.error(tag ?: "", message, t)
            Log.WARN -> LogUtils.warn(tag ?: "", message, t)
            else -> LogUtils.debug(tag ?: "", message, t)
        }
}

class DebugTree : Timber.DebugTree() {
    override fun isLoggable(tag: String?, priority: Int) = true

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) =
        when (priority) {
            Log.DEBUG -> LogUtils.debug(tag ?: "", message, t)
            Log.ERROR -> LogUtils.error(tag ?: "", message, t)
            Log.WARN -> LogUtils.warn(tag ?: "", message, t)
            Log.INFO -> LogUtils.info(tag ?: "", message, t)
            Log.VERBOSE -> LogUtils.verbose(tag ?: "", message, t)
            else -> LogUtils.debug(tag ?: "", message, t)
        }
}