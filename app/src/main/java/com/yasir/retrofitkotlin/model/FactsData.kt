package com.yasir.retrofitkotlin.model

data class FactsData(val text: String, val createdAt: String){
    constructor(): this("", "")
}
