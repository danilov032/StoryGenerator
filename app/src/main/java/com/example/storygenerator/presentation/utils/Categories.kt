package com.example.storygenerator.presentation.utils

enum class Categories {
    ANECDOTE,
    ANECDOTE18,
    STORIES,
    STORIES18,
    RHYMES,
    RHYMES18,
    APHORISMS,
    APHORISMS18,
    QUOTES,
    QUOTES18,
    TOASTS,
    TOASTS18,
    STATUSES,
    STATUSES18;

    fun getNameCategory(): String = when (this) {
        ANECDOTE -> "Анекдоты"
        ANECDOTE18 -> "Анекдоты18"
        STORIES -> "Рассказы"
        STORIES18 -> "Рассказы18"
        RHYMES -> "Стишки"
        RHYMES18 -> "Стишки18"
        APHORISMS -> "Афоризмы"
        APHORISMS18 -> "Афоризмы18"
        QUOTES -> "Цитаты"
        QUOTES18 -> "Цитаты18"
        TOASTS -> "Тосты"
        TOASTS18 -> "Тосты18"
        STATUSES -> "Статусы"
        STATUSES18 -> "Статусы18"
    }

    fun getId(): Int = when (this) {
        ANECDOTE -> 1
        ANECDOTE18 -> 11
        STORIES -> 2
        STORIES18 -> 12
        RHYMES -> 3
        RHYMES18 -> 13
        APHORISMS -> 4
        APHORISMS18 -> 14
        QUOTES -> 5
        QUOTES18 -> 15
        TOASTS -> 6
        TOASTS18 -> 16
        STATUSES -> 8
        STATUSES18 -> 18
    }

    companion object{
        fun getClassEnum(id: Int): Categories = when (id) {
            1 -> ANECDOTE
            11 -> ANECDOTE18
            2 -> STORIES
            12 -> STORIES18
            3 -> RHYMES
            13 -> RHYMES18
            4 -> APHORISMS
            14 -> APHORISMS18
            5 -> QUOTES
            15 -> QUOTES18
            6 -> TOASTS
            16 -> TOASTS18
            7 -> STATUSES
            17 -> STATUSES18
            else ->ANECDOTE
        }
    }
}