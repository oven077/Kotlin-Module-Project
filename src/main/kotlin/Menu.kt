import java.util.Scanner

abstract class MainMenu(private val scanner: Scanner) {
    protected val items = mutableListOf<MainMenu.Item>()

    fun show() {
        while (true) {
            items.forEach { item -> println("${item.index}. ${item.title}") }
            print("Ваш выбор: ")
            val choice = scanner.nextLine()

            try {
                val selectedIndex = choice.toInt() - 1
                if (selectedIndex in 0 until items.size) {
                    items[selectedIndex].action()
                    break
                } else {
                    println("Некорректный выбор. Попробуйте снова.")
                }
            } catch (e: NumberFormatException) {
                println("Пожалуйста, введите целое число.")
            }
        }
    }

    data class Item(val index: Int, val title: String, val action: () -> Unit)
}