package com.nytimes.presentation

import org.mockito.Mockito

inline fun <reified T> mock(): T {
    return Mockito.mock(T::class.java)
}