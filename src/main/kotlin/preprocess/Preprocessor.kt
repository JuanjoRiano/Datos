package preprocess

object Preprocessor {
    fun clean(
        data: List<Map<String, String>>
    ): List<Map<String, Double>> = data.map { row ->
        row.mapValues { (_, v) -> v.toDoubleOrNull() ?: 0.0 }
    }

    fun split(
        data: List<Map<String, Double>>,
        trainRatio: Double = 0.8
    ): Pair<List<Map<String, Double>>, List<Map<String, Double>>> {
        val shuffled = data.shuffled()
        val cutoff = (shuffled.size * trainRatio).toInt()
        return shuffled.take(cutoff) to shuffled.drop(cutoff)
    }
}