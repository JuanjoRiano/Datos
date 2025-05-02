Análisis Predictivo de Datos con Kotlin

  

Descripción

Este proyecto implementa un flujo completo de análisis predictivo de datos utilizando Kotlin, aplicado sobre un dataset público descargado de Kaggle. Cubre todas las fases del ciclo de vida del dato:

Carga de datos

Preprocesamiento (imputación, codificación, escalado)

División en conjuntos de entrenamiento y prueba

Entrenamiento de un modelo de bosque aleatorio (Random Forest)

Evaluación de métricas (accuracy, precision, recall, F1-score)

Visualización de la importancia de características

Autores:

Juan José Riaño

Juan Sebastián Tibatá

Gerardo Ropero Rojas

Valentina Montenegro Quevedo

Tabla de Contenidos

Estructura del Repositorio

Requisitos

Instalación

Uso

Flujo de Trabajo

Explicación del Código

Personalización y Extensiones

Resultados de Ejemplo

Informe Técnico

Contribución

Licencia

Estructura del Repositorio

Datos/
├── data/                  # Datos de entrada
│   ├── raw.csv            # Dataset original descargado de Kaggle
│   └── processed.csv      # Dataset tras limpieza y codificación
├── results/               # Salida de resultados
│   ├── metrics.csv        # Métricas de evaluación del modelo
│   └── feature_importance.png # Gráfica de importancia de características
├── src/
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
│   │   └── ModelEvaluator.kt       # Cálculo de métricas (accuracy, precision, recall, F1)
│   └── utils/
│       └── Utils.kt                # Funciones auxiliares (logging, validaciones)
├── build.gradle.kts      # Configuración de Gradle y dependencias
├── settings.gradle.kts   # Configuración de proyecto
├── gradlew, gradlew.bat  # Scripts wrapper de Gradle
├── report.tex            # Informe técnico en LaTeX
└── README.md             # Documento de descripción (este archivo)

Requisitos

Java Development Kit (JDK) 11 o superior

Kotlin 1.5 o superior

Gradle 7 o superior

Dependencias Principales

Smile – Biblioteca de aprendizaje automático

kotlin-csv – Lectura y escritura de CSV

(Opcional) Weka o DL4J – Para comparativas

Instalación

Clonar el repositorio:

git clone https://github.com/JuanjoRiano/Datos.git
cd Datos

Colocar el dataset descargado de Kaggle:

Descarga el CSV desde Kaggle y renómbralo a raw.csv.

Copia raw.csv dentro de la carpeta data/.

Construir el proyecto con Gradle:

./gradlew build

Uso

Ejecuta el flujo completo desde la línea de comandos:

./gradlew run --args="--input data/raw.csv --output results/"

Parámetros:

--input <ruta>: Ruta al archivo CSV original (por ejemplo, data/raw.csv).

--output <carpeta>: Carpeta donde se guardarán los resultados (por ejemplo, results/).

Flujo de Trabajo

Carga de datos

val df = CsvLoader.load(inputPath)

Preprocesamiento

Imputación de valores nulos: MissingValueImputer

Codificación de variables categóricas: CategoricalEncoder

Escalado de características: Scaler

División del conjunto

val (train, test) = DataSplitter.split(df, trainSize = 0.8)

Entrenamiento del modelo

val model = RandomForestTrainer.train(train, nTrees = 100, maxDepth = 10, seed = 42)

Evaluación

val metrics = ModelEvaluator.evaluate(model, test)

Visualización

Se genera la gráfica results/feature_importance.png mostrando la importancia relativa de cada característica.

Explicación del Código

CsvLoader.kt: Utiliza kotlin-csv para leer un CSV en una estructura de datos interna (DataFrame-like).

MissingValueImputer.kt: Detecta columnas con valores nulos y aplica estrategias de imputación (media, mediana o moda).

CategoricalEncoder.kt: Transforma variables categóricas en representaciones numéricas (one-hot encoding o label encoding).

Scaler.kt: Normaliza o estandariza columnas numéricas según configuración (min-max o z-score).

DataSplitter.kt: Divide aleatoriamente el dataset en subconjuntos de entrenamiento y prueba según proporción definida.

RandomForestTrainer.kt: Construye y entrena un modelo de bosque aleatorio usando la librería Smile.

ModelEvaluator.kt: Calcula métricas clave de clasificación: accuracy, precision, recall y F1-score, y exporta metrics.csv.

Utils.kt: Funciones auxiliares para validación de rutas, logging y formateo de resultados.

Main.kt: Gestiona los argumentos de CLI, orquesta el flujo completo y maneja la escritura de resultados.

Personalización y Extensiones

Hiperparámetros: Ajusta nTrees, maxDepth y seed en RandomForestTrainer.kt.

Proporción de división: Cambia la proporción de entrenamiento en DataSplitter.kt (por defecto 80/20).

Nuevos modelos: Añade clases en src/model/ que implementen otros algoritmos y registra en Main.kt.

Comparativas: Integra Weka o DL4J para comparar el rendimiento con otros frameworks.

Resultados de Ejemplo

Al ejecutar el proyecto, en results/metrics.csv encontrarás:

Métrica,Valor
Accuracy,0.89
Precision,0.88
Recall,0.85
F1-score,0.86

Y en results/feature_importance.png una gráfica similar a:



