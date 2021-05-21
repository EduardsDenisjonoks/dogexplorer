package com.example.dogexplorer.utils

import android.annotation.SuppressLint
import android.util.Log

class LogUtils {

    companion object {

        /**
         * Will log debug and if exception is provided will log exception
         * @param tag - tag as sting to indicate what called this function
         * @param message - message as string
         * @param ex - optional (nullable) exception
         */
        @SuppressLint("LogNotTimber")
        fun debug(tag: String, message: String, ex: Throwable? = null) {
            Log.d(tag, message, ex)
        }

        /**
         * Will log error and if exception is provided will log exception
         * @param tag - tag as sting to indicate what called this function
         * @param message - message as string
         * @param ex - optional (nullable) exception
         */
        @SuppressLint("LogNotTimber")
        fun error(tag: String, message: String, ex: Throwable? = null) {
            Log.e(tag, message, ex)
        }

        /**
         * Will log warning and if exception is provided will log exception
         * @param tag - tag as sting to indicate what called this function
         * @param message - message as string
         * @param ex - optional (nullable) exception
         */
        @SuppressLint("LogNotTimber")
        fun warn(tag: String, message: String, ex: Throwable? = null) {
            Log.w(tag, message, ex)
        }

        /**
         * Will log info and if exception is provided will log exception
         * @param tag - tag as sting to indicate what called this function
         * @param message - message as string
         * @param ex - optional (nullable) exception
         */
        @SuppressLint("LogNotTimber")
        fun info(tag: String, message: String, ex: Throwable? = null) {
            Log.i(tag, message, ex)
        }

        /**
         * Will log verbose and if exception is provided will log exception
         * @param tag - tag as sting to indicate what called this function
         * @param message - message as string
         * @param ex - optional (nullable) exception
         */
        @SuppressLint("LogNotTimber")
        fun verbose(tag: String, message: String, ex: Throwable? = null) {
            Log.v(tag, message, ex)
        }
    }

}