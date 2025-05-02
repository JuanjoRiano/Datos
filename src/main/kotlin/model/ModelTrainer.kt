package model

import smile.regression.OLS  // está en la dependencia smile-core:2.6.0

class ModelTrainer {
    lateinit var model: OLS

    fun train(
        trainData: List<Map<String, Double>>,
        features: List<String>,
        target: String
    ) {
        val x = trainData.map { row -> features.map { row[it]!! }.toDoubleArray() }.toTypedArray()
        val y = trainData.map { it[target]!! }.toDoubleArray()
        model = OLS.fit(x, y)
    }

    fun predict(featuresRow: DoubleArray): Double = model.predict(featuresRow)
}
