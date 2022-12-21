fun main() {
    fun part1(input: List<String>): Int {
        val calorieCounter = CalorieCounter(input)
        return calorieCounter.mostCaloriesCarriedByAnElf()
    }

    fun part2(input: List<String>): Int {
        val calorieCounter = CalorieCounter(input)
        return calorieCounter.caloriesCarriedByTopThreeElves()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

class CalorieCounter(input: List<String>) {

    val elfTotals = calculateTotals(input)

    private fun calculateTotals(input: List<String>): List<Int> {
        if (input.isEmpty()) {
            return emptyList()
        }
        val totals = mutableListOf(0)
        for (cals in input) {
            if (cals.isBlank()) {
                totals.add(0)
            } else {
                totals[totals.size - 1] += cals.toInt()
            }
        }
        return totals
    }

    fun mostCaloriesCarriedByAnElf(): Int {
        return elfTotals.max()
    }

    fun caloriesCarriedByTopThreeElves(): Int {
        return elfTotals.sortedDescending().subList(0, 3).sum()
    }

}
