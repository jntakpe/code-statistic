package com.github.jntakpe.codestatistic

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

fun main(args: Array<String>) {
    val kotlin = Extension.KOTLIN to Paths.get("/Users/jntakpe/dev/release-monitor/src/main/kotlin")
    val java = Extension.JAVA to Paths.get("/Users/jntakpe/dev/release-monitor-java/src/main/java")
    listOf<Pair<Extension, Path>>(kotlin, java).forEach { (e, p) ->
        listFiles(p, e).use {
            val stat = it.map { codeLines(it) }.map { Stat(it.count(), codeChars(it)) }.reduce(Stat(0, 0), { a, c -> a + c })
            println("$e : $stat")
        }
    }
}

private fun listFiles(path: Path, ext: Extension) = Files.walk(path).filter { Files.isRegularFile(it) }.filter { it.endsWith(ext) }

private fun Path.endsWith(ext: Extension) = fileName.toString().endsWith(ext.value)

private fun codeLines(path: Path) = Files.readAllLines(path).filter { !it.startsWith("import") }.filter { it.isNotBlank() }

private fun codeChars(lines: List<String>) = lines.map { it.trim().count() }.sum()

enum class Extension(val value: String) {
    JAVA(".java"),
    KOTLIN("kt")
}

data class Stat(private val lineNumber: Int, private val charNumber: Int) {
    operator fun plus(other: Stat) = Stat(lineNumber + other.lineNumber, charNumber + other.charNumber)
}