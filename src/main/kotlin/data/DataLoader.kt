package data

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader   // proviene de kotlin-csv-jvm
import java.io.File

object DataLoader {
    fun load(path: String = "data/top-1000-trending-youtube-videos.csv"): List<Map<String, String>> =
        csvReader().readAllWithHeader(File(path))
}  // ← ¡Cierra bien la llave del objeto!
