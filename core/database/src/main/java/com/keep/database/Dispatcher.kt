package com.keep.database

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher (val binDispatcher: BinDispatcher)

enum class BinDispatcher {
    MAIN,
    DEFAULT,
    IO
}