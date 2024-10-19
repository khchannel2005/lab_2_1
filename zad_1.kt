//Маємо масив (1, 2, 3, 4, 5). Отримуємо і шукаємо список квадратів

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5)
    
    // Отримуємо список квадратів
    val squares = numbers.map { it * it }
    println("Список квадратів: $squares")
    
    // Обчислюємо суму квадратів
    val sumOfSquares = squares.sum()
    println("Сума квадратів: $sumOfSquares")
}
