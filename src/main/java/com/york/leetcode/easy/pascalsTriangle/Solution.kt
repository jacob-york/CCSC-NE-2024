package com.york.leetcode.easy.pascalsTriangle

import java.util.*

fun main() {
    readUserInput().map { case -> generate(case) }
        .forEach { triangle -> println(triangle) }
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

fun generate(numRows: Int): List<List<Int>> {
    val triangle = MutableList(numRows) { i -> MutableList(i + 1) { 1 } }

    for (i in triangle.indices) {
        for (j in triangle[i].indices) {
            if (j != 0 && j != i) {
                triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j]
            }
        }
    }

    return triangle
}