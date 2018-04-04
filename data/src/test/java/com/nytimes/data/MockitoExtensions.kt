package com.nytimes.data

import org.mockito.Mockito

inline fun <reified T> mock(): T {
    return Mockito.mock(T::class.java)
}