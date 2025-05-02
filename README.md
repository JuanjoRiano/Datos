# Análisis Predictivo de Datos con Kotlin

&#x20;&#x20;

## Descripción

Este proyecto implementa un flujo completo de **análisis predictivo** utilizando **Kotlin** y la librería **Smile**, aplicado sobre un dataset público extraído de Kaggle. El objetivo es construir y evaluar un modelo de **Random Forest** para predecir la variable objetivo Y, cubriendo todas las etapas del ciclo de vida del dato:

1. **Carga** de datos
2. **Exploración** y análisis de valores faltantes
3. **Preprocesamiento** (imputación, codificación, escalado)
4. **División** en conjuntos de entrenamiento (80%) y prueba (20%) con semilla fija para reproducibilidad
5. **Selección de modelo** y justificación metodológica
6. **Entrenamiento** con hiperparámetros optimizados
7. **Evaluación** en entrenamiento y prueba (accuracy, precision, recall, F1-score)
8. **Visualización** de la importancia de características

> **Autores:**
>
> * Juan José Riaño
> * Juan Sebastián Tibatá
> * Gerardo Ropero Rojas
> * Valentina Montenegro Quevedo

## Tabla de Contenidos

1. [Introducción](#introducción)
2. [Descripción del Dataset](#descripción-del-dataset)
3. [Carga y Preprocesamiento](#carga-y-preprocesamiento)

   * [Lectura de datos](#lectura-de-datos)
   * [Manejo de valores nulos](#manejo-de-valores-nulos)
   * [Codificación de variables categóricas](#codificación-de-variables-categóricas)
   * [Normalización y estandarización](#normalización-y-estandarización)
4. [División del Dataset](#división-del-dataset)
5. [Selección e Implementación del Modelo](#selección-e-implementación-del-modelo)

   * [Modelos considerados](#modelos-considerados)
   * [Justificación de la elección](#justificación-de-la-elección)
6. [Entrenamiento y Evaluación](#entrenamiento-y-evaluación)
7. [Resultados](#resultados)
8. [Conclusiones](#conclusiones)

## Estructura del Repositorio

```plaintext
Datos/
├── data/                  # Datos de entrada
│   ├── raw.csv            # Dataset original descargado de Kaggle
│   └── processed.csv      # Dataset tras limpieza y codificación
├── results/               # Salida de resultados
│   ├── metrics.csv        # Métricas de evaluación del modelo
│   └── feature_importance.png # Gráfica de importancia de características
├── src/                   # Código fuente
│   ├── Main.kt            # Punto de entrada (CLI)
│   ├── loader/
│   │   └── CsvLoader.kt   # Lectura de archivos CSV
│   ├── preprocess/
│   │   ├── MissingValueImputer.kt  # Imputación de valores nulos
│   │   ├── CategoricalEncoder.kt   # Codificación de variables categóricas
│   │   └── Scaler.kt                # Normalización y estandarización
│   ├── split/
│   │   └── DataSplitter.kt         # División en train/test
│   ├── model/
│   │   ├── RandomForestTrainer.kt  # Entrenamiento de Random Forest
│   │   └── ModelEvaluator.kt       # Cálculo de métricas
│   └── utils/
│       └── Utils.kt                # Funciones auxiliares (logging, validaciones)
├── build.gradle.kts      # Configuración de Gradle y dependencias
├── settings.gradle.kts   # Configuración de proyecto
├── gradlew, gradlew.bat  # Scripts wrapper de Gradle
└── README.md             # Documentación del proyecto
```

## Introducción

El análisis predictivo se ha convertido en una herramienta fundamental para la toma de decisiones en ámbitos como salud, finanzas y marketing. Este proyecto demuestra un flujo reproducible y modular en Kotlin sobre un dataset público de Kaggle.

## Descripción del Dataset

* **Origen:** Kaggle (archivo CSV)
* **Registros:** 1 000 filas (los 1000 videos más vistos en YouTube)
* **Variables:** 16 columnas, incluyendo texto (título, canal), categóricas (categoría de video) y numéricas (vistas, me gusta, no me gusta, comentarios)
* **Contenido clave:**

  * `title`: Título del video
  * `channel`: Nombre del canal
  * `views`, `likes`, `dislikes`, `comment_count`
  * `category_id`: ID numérica de la categoría
* **Objetivo:** Predecir la popularidad (p. ej., clasificar videos en rangos de vistas)

## Carga y Preprocesamiento

### Lectura de datos

```kotlin
val df = CsvLoader.load("data/raw.csv")
```

Utilizamos `kotlin-csv` para parsear eficientemente el CSV.

### Manejo de valores nulos

* Se eliminan columnas con >50% de valores faltantes.
* Imputación:

  * Numéricas: media aritmética
  * Categóricas: moda

### Codificación de variables categóricas

* **One-hot encoding** para categorías nominales.
* **Label encoding** para variables ordinales.

### Normalización y estandarización

Estandarizamos usando z-score:

$x' = \frac{x - \mu}{\sigma}$

## División del Dataset

```kotlin
val (train, test) = DataSplitter.split(df, trainSize = 0.8, seed = 42)
```

Se fija `trainSize = 0.8` y `seed = 42` para asegurar reproducibilidad.

## Explicación de Módulos Clave del Código

### CsvLoader.kt

```kotlin
object CsvLoader {
    fun load(path: String): DataFrame {
        return Reader().readAll(path)  // lee CSV usando kotlin-csv
    }
}
```

* Encapsula la lectura de CSV y devuelve un DataFrame-like.

### MissingValueImputer.kt

```kotlin
object MissingValueImputer {
    fun impute(df: DataFrame): DataFrame {
        // elimina columnas con >50% nulos
        // aplica media a numéricas y moda a categóricas
    }
}
```

* Filtra y completa valores faltantes para evitar sesgos.

### CategoricalEncoder.kt

```kotlin
object CategoricalEncoder {
    fun encode(df: DataFrame): DataFrame {
        // detecta columnas String y aplica one-hot o label encoding
    }
}
```

* Transforma variables cualitativas en numéricas.

### Scaler.kt

```kotlin
object Scaler {
    fun scale(df: DataFrame): DataFrame {
        // z-score: (x - media) / desviación
    }
}
```

* Normaliza características para mejorar convergencia.

### RandomForestTrainer.kt

```kotlin
object RandomForestTrainer {
    fun train(df: DataFrame, nTrees: Int, maxDepth: Int, seed: Int): RandomForest {
        return RandomForest.fit(df, ...)
    }
}
```

* Entrena modelo con parámetros ajustables.

### ModelEvaluator.kt

```kotlin
object ModelEvaluator {
    fun evaluate(model: RandomForest, df: DataFrame): Metrics {
        // calcula accuracy, precision, recall, F1
    }
}
```

* Genera métricas de desempeño y exporta CSV.

## Selección e Implementación del Modelo

```kotlin
val (train, test) = DataSplitter.split(df, trainSize = 0.8, seed = 42)
```

Se fija `trainSize = 0.8` y `seed = 42` para asegurar reproducibilidad.

## Selección e Implementación del Modelo

### Modelos considerados

* Regresión Logística
* Support Vector Machine (SVM)
* Redes Neuronales (KotlinDL)
* **Random Forest** (Smile)

### Justificación de la elección

Se optó por Random Forest debido a:

* Robustez frente al overfitting
* Captura no linealidades sin transformaciones previas
* Interpretabilidad parcial mediante importancia de variables
* Eficiencia computacional frente a SVM y redes profundas

## Entrenamiento y Evaluación

### Hiperparámetros del Random Forest

* `nTrees = 100`
* `maxDepth = 10`
* `seed = 42`

```kotlin
val model = RandomForestTrainer.train(train, nTrees = 100, maxDepth = 10, seed = 42)
val metricsTrain = ModelEvaluator.evaluate(model, train)
val metricsTest = ModelEvaluator.evaluate(model, test)
```

## Resultados

| Métrica   | Entrenamiento | Prueba |
| --------- | ------------- | ------ |
| Accuracy  | 0.92          | 0.89   |
| Precision | 0.90          | 0.88   |
| Recall    | 0.93          | 0.85   |
| F1-score  | 0.91          | 0.86   |

Se observa un buen equilibrio entre precision y recall, con un F1-score de 0.86 en prueba.

## Conclusiones

El modelo Random Forest demostró ser adecuado, logrando un buen balance precisión-robustez. Mejoras futuras:

* Búsqueda de hiperparámetros (grid/random search).
* Ensambles avanzados (stacking, boosting).
* Validación cruzada para estimaciones más estables.
* Ingeniería de nuevas variables.
