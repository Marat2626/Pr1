// Класс для хранения информации о расходах
class Expense(
    val amount: Double,
    val category: String,
    val date: String,
    val action: (Int, Int) -> Unit // Функциональный тип с двумя целыми параметрами
) {
    // Метод для вывода информации о конкретном расходе
    fun displayInfo() {
        println("Сумма: $amount, Категория: $category, Дата: $date")
    }
}

// Класс для управления списком всех расходов
class ExpenseTracker {
    private val expenses = mutableListOf<Expense>()

    // Метод для добавления нового расхода в список
    fun addExpense(expense: Expense) {
        expenses.add(expense)
    }

    // Метод для вывода всех расходов
    fun displayAllExpenses() {
        println("Список всех расходов:")
        for (expense in expenses) {
            expense.displayInfo()
        }
    }

    // Метод для подсчета суммы всех расходов по каждой категории
    fun calculateTotalByCategory() {
        val categoryTotals = mutableMapOf<String, Double>()
        for (expense in expenses) {
            categoryTotals[expense.category] = categoryTotals.getOrDefault(expense.category, 0.0) + expense.amount
        }
        println("Сумма расходов по категориям:")
        for ((category, total) in categoryTotals) {
            println("Категория: $category, Сумма: $total")
        }
    }
}

// Пример использования
fun main() {
    val tracker = ExpenseTracker()

    // Создание функционального типа для действия
    val action: (Int, Int) -> Unit = { x, y -> println("Сумма: ${x + y}") }
    val action: (Int,Int) -> Unit =
        fun(x: Int, y: Int) {
            println("Сумма: ${x + y}")
        }

    // Добавляем расходы с функциональным типом
    tracker.addExpense(Expense(100.0, "Еда", "2023-10-01", action))
    tracker.addExpense(Expense(200.0, "Транспорт", "2023-10-02", action))
    tracker.addExpense(Expense(150.0, "Еда", "2023-10-03", action))

    // Отображаем все расходы
    tracker.displayAllExpenses()

    // Подсчитываем сумму расходов по категориям
    tracker.calculateTotalByCategory()

    // Вызываем функциональный тип для каждого расхода
    tracker.expenses.forEach { expense ->
        expense.action(10, 20) // Пример вызова функции с параметрами
    }
}