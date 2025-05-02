package model

import kotlin.math.abs
import kotlin.math.sqrt

object Evaluator {
    fun mae(pred: DoubleArray, actual: DoubleArray): Double =
        pred.zip(actual).map { (p, a) -> abs(p - a) }.average()

    fun rmse(pred: DoubleArray, actual: DoubleArray): Double =
        sqrt(pred.zip(actual).map { (p, a) -> (p - a) * (p - a) }.average())
}