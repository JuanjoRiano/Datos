package cli

import data.DataLoader
import preprocess.Preprocessor
import model.ModelTrainer
import model.Evaluator

fun main() {
    println("=== Análisis Predictivo YouTube ===")
    val raw = DataLoader.load()
    val clean = Preprocessor.clean(raw)
    val (train, test) = Preprocessor.split(clean)

    // Ajusta estas columnas según tu CSV
    val features = listOf("views", "likes", "dislikes")
    val target = "comment_count"

    val trainer = ModelTrainer()
    trainer.train(train, features, target)

    val testX = test.map { row -> features.map { row[it]!! }.toDoubleArray() }
    val testY = test.map { it[target]!! }.toDoubleArray()
    val preds = testX.map { trainer.predict(it) }.toDoubleArray()

    println("MAE = ${Evaluator.mae(preds, testY)}")
    println("RMSE = ${Evaluator.rmse(preds, testY)}")
}