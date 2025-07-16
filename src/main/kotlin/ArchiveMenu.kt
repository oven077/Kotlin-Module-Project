import java.util.Scanner

class SelectMenu() {
    private val scanner: Scanner = Scanner(System.`in`)


    fun addArchive(archives: HashMap<String, MutableList<String>>) {
        print("Введите название архива: ")
        val archiveName = scanner.nextLine()
        if (!archives.containsKey(archiveName)) {
            archives[archiveName] = mutableListOf()
            println("Архив '$archiveName' создан.")
        } else {
            println("Архив с таким именем уже существует.")
        }
    }


    fun selectArchive(archives: HashMap<String, MutableList<String>>) {
        println("Список архивов:")
        archives.keys.forEachIndexed { index, key -> println("${index + 1}. $key") }
        println("0. Вернуться назад")
        print("Ваш выбор: ")
        val choice = scanner.nextLine()

        if (choice == "0") {
            return
        } else {
            try {
                val selectedArchiveIndex = choice.toInt() - 1
                if (selectedArchiveIndex in 0 until archives.size) {
                    val selectedArchive = archives.keys.toList()[selectedArchiveIndex]
                    println("Вы выбрали архив $selectedArchive.")
                    selectArchiveActions(archives[selectedArchive]!!)
                } else {
                    println("Некорректный выбор. Попробуйте снова.")
                }
            } catch (e: NumberFormatException) {
                println("Пожалуйста, введите целое число.")
            }
        }
    }


    fun selectArchiveActions(notes: MutableList<String>) {
        println("Выберите действие:")
        println("1. Просмотр заметки")
        println("2. Создать заметку")
        println("3. Выйти на верхний уровень меню")

        print("Ваш выбор: ")
        val choice = scanner.nextLine()

        when (choice) {
            "1" -> selectNote(notes)
            "2" -> createNote(notes)
            "3" -> return
            else -> println("Некорректный выбор. Попробуйте снова.")
        }
    }

    fun selectNote(notes: MutableList<String>) {
        println("Список заметок в архиве:")
        notes.forEachIndexed { index, note -> println("\"${index + 1}. $note\"") }
        println("0. Вернуться назад")
        print("Ваш выбор: ")
        val choice = scanner.nextLine()

        if (choice == "0") {
            return
        } else {
            println("Вы выбрали заметку ${notes[choice.toInt()]}.")
            showNote(notes[choice.toInt()])
        }
    }

    fun createNote(notes: MutableList<String>) {
        print("Введите текст заметки: ")
        val noteText = scanner.nextLine()
        notes.add(noteText)
        println("Заметка '$noteText' создана.")
    }

    fun showNote(noteText: String) {
        println("Текст заметки:")
        println(noteText)
        println("Нажмите Enter, чтобы вернуться назад.")
        scanner.nextLine()
    }
}