# Análisis Predictivo de Datos con Kotlin

## Autores

- Juan José Riaño  
- Juan Sebastián Tibatá  
- Gerardo Ropero Rojas  
- Valentina Montenegro Quevedo  

## Descripción

Este proyecto implementa un flujo completo de **análisis predictivo de datos en Kotlin**, aplicado sobre un dataset público descargado de [Kaggle](https://www.kaggle.com/). Cubre todas las fases del ciclo de vida del dato: carga, preprocesamiento, partición, modelado, evaluación y visualización de resultados.

## Estructura del Repositorio

/
├── data/
│ ├── raw.csv # Dataset original descargado de Kaggle
│ └── processed.csv # Dataset tras limpieza y codificación
├── results/
│ ├── metrics.csv # Métricas de evaluación del modelo
│ └── feature_importance.png # Gráfica de importancia de características
├── src/
│ ├── Main.kt # Punto de entrada (CLI)
│ ├── loader/
│ │ └── CsvLoader.kt # Lectura de archivos CSV
│ ├── preprocess/
│ │ ├── MissingValueImputer.kt # Gestión de valores nulos
│ │ ├── CategoricalEncoder.kt # Codificación de variables categóricas
│ │ └── Scaler.kt # Normalización y estandarización
│ ├── split/
│ │ └── DataSplitter.kt # División train/test
│ ├── model/
│ │ ├── RandomForestTrainer.kt # Entrenamiento de Random Forest
│ │ └── ModelEvaluator.kt # Cálculo de métricas
│ └── utils/
│ └── Utils.kt # Funciones auxiliares
├── build.gradle.kts # Configuración de Gradle y dependencias
├── report.tex # Informe técnico en LaTeX
└── README.md # Este archivo

markdown
Copiar
Editar

## Requisitos

- Java Development Kit (JDK) 11 o superior  
- Kotlin 1.5+  
- Gradle 7+  

### Dependencias principales

- [Smile](https://haifengl.github.io/) – Biblioteca de aprendizaje automático  
- [kotlin-csv](https://github.com/doyaaaaaken/kotlin-csv) – Lectura y escritura de CSV  
- (Opcional) [Weka](https://www.cs.waikato.ac.nz/ml/weka/) o [DL4J](https://deeplearning4j.konduit.ai/) – Para comparativas

## Instalación

1. Clonar el repositorio:

```bash
git clone https://github.com/JuanjoRiano/Datos.git
cd Datos
Descargar el dataset:

Regístrate en Kaggle, descarga el archivo CSV del dataset elegido.

Renómbralo como raw.csv y colócalo dentro de la carpeta data/.

Construir el proyecto:

bash
Copiar
Editar
./gradlew build
Uso
Ejecución desde CLI
bash
Copiar
Editar
./gradlew run --args="--input data/raw.csv --output results/"
Parámetros:

--input <ruta>: Ruta al archivo CSV original (por ejemplo, data/raw.csv)

--output <carpeta>: Carpeta donde se guardarán los resultados (métricas y gráficas)

Flujo de Trabajo
Carga de datos
CsvLoader.load(path: String): DataFrame

Preprocesamiento

Imputación de valores nulos (MissingValueImputer)

Codificación de variables categóricas (CategoricalEncoder)

Normalización o estandarización (Scaler)

División del dataset

80% entrenamiento / 20% prueba (DataSplitter)

Entrenamiento del modelo

kotlin
Copiar
Editar
RandomForestTrainer.train(trainData, nTrees = 100, maxDepth = 10, seed = 42)
Evaluación
ModelEvaluator.evaluate(model, testData)

Métricas: Accuracy, Precision, Recall, F1-score

Visualización

Importancia de características (results/feature_importance.png)

Configuración y Personalización
Hiperparámetros del Random Forest
Modifica RandomForestTrainer.kt para ajustar nTrees, maxDepth, seed, etc.

Proporción del split
Edita DataSplitter.kt para usar una división distinta (por ejemplo, 70/30)

Incluir otros modelos
Agrega clases en model/ que implementen tu modelo y actualiza Main.kt para integrarlos al CLI.

Resultados de Ejemplo
Al ejecutar el flujo completo se genera:

results/metrics.csv

mathematica
Copiar
Editar
Métrica,Valor
Accuracy,0.89
Precision,0.88
Recall,0.85
F1-score,0.86
results/feature_importance.png
Gráfica que muestra la importancia relativa de cada característica.

Informe Técnico
El archivo report.tex contiene:

Introducción y objetivo

Descripción del dataset

Metodología de carga y preprocesamiento

Justificación del modelo y decisiones tomadas

Resultados y análisis

Conclusiones y recomendaciones

Para compilar el informe:

bash
Copiar
Editar
pdflatex report.tex
Licencia
Este proyecto está licenciado bajo la MIT License.
Consulta el archivo LICENSE para más información.

