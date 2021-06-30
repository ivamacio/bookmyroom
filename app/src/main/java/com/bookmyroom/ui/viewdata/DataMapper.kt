package com.bookmyroom.ui.viewdata

interface DataMapper<SOURCE, RESULT> {
    fun map(source: SOURCE): RESULT
}