import java.util.Scanner

class NoteMenu(scanner: Scanner, private val archives: HashMap<String, HashMap<String, String>>) : Menu(scanner) {
    init {
        items.add(Item(0, "Вернуться назад", ::goBack)) // Добавляем пункт "Вернуться назад"
        items.add(Item(1, "Создать заметку", ::createNote))
        items.add(Item(2, "Просмотр заметки", ::viewNotes))
    }

    private fun createNote(archives: HashMap<String, HashMap<String, String>>) {
        print("Введите название архива: ")
        val archiveName = scanner.nextLine()
        if (archives.containsKey(archiveName)) {
            print("Введите заголовок заметки: ")
            val noteTitle = scanner.nextLine()
            print("Введите текст заметки: ")
            val noteContent = scanner.nextLine()
            if (noteTitle.isNotEmpty() && noteContent.isNotEmpty()) {
                archives[archiveName]!![noteTitle] = noteContent
                println("Заметка '$noteTitle' создана.")
            } else {
                println("Заголовок или текст заметки не могут быть пустыми.")
            }
        } else {
            println("Архив с таким именем не найден.")
        }
    }

    private fun viewNotes(archives: HashMap<String, HashMap<String, String>>) {
        print("Введите название архива: ")
        val archiveName = scanner.nextLine()
        if (archives.containsKey(archiveName)) {
            viewNotesInArchive(archiveName, archives)
        } else {
            println("Архив с таким именем не найден.")
        }
    }

    private fun viewNotesInArchive(archiveName: String, archives: HashMap<String, HashMap<String, String>>) {
        if (archives.containsKey(archiveName)) {
            val notes = archives[archiveName]!!
            println("Список заметок в архиве $archiveName:")
            notes.forEach { (title, content) -> println("Заголовок: $title, Содержание: $content") }
        } else {
            println("Архив с таким именем не найден.")
        }
    }
}
