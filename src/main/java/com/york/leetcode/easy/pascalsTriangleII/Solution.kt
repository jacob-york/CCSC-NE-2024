package com.york.leetcode.easy.pascalsTriangleII

fun main() {
    readUserInput().map {index -> getRow(index)}
        .forEach{ triangleRow -> println(triangleRow) }
}

fun getRow(rowIndex: Int): List<Int> {
    if (rowIndex == 0) return List(1) {1}
    var cur = List(2) {1}
    if (rowIndex == 1) return cur

    for (i in 2..rowIndex) {
        cur = List(i+1) { ind -> if (ind == 0 || ind == i) 1 else (cur[ind] + cur[ind-1])  }
    }

    return cur
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