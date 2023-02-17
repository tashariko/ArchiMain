package com.tasha.archimain.data

class ErrorType(val type: Type, val message: String? = null) {
    enum class Type {
        Backend,
        Generic
    }
}