package com.york.leetcode.easy.fibonacci

import java.util.ArrayList

fun main() {
    readUserInput().map { index -> getSequence(index) }
        .forEach { sequence -> println(sequence) }
}

fun getSequence(index: Int): List<Int> {
    val returnVal: MutableList<Int> = ArrayList()

    returnVal.add(0)
    if (index == 0) return returnVal
    returnVal.add(1)
    if (index == 1) return returnVal

    for (i in 2..index) {
        returnVal.add(returnVal[i-2] + returnVal[i-1])
    }

    return returnVal
}

fun readUserInput(): MutableList<Int> {
    val cases: MutableList<Int> = ArrayList()

    var current = readln().toInt()
    while (current != -1) {
        cases.add(current)
        current = readln().toInt()
    }
    return cases
}