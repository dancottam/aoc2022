fun main() {
    fun part1(input: List<String>): Int {
        val rucksacks = Rucksacks(input)
        return rucksacks.sumPrioritiesForCommonItemTypes()
    }

    fun part2(input: List<String>): Int {
        val rucksacks = Rucksacks(input)
        return rucksacks.sumGroupBadgePriorities()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}

class Rucksacks(input: List<String>) {

    val groupBadges = findGroupBadges(input)

    val commonItemTypes = findCommonItems(input)

    private fun findCommonItems(input: List<String>): List<Char> {
        val commonItemTypes = mutableListOf<Char>()
        for (rucksack in input) {
            commonItemTypes.add(findCommonItem(rucksack))
        }
        return commonItemTypes
    }

    private fun findCommonItem(rucksack: String): Char {
        val middle = rucksack.length / 2
        val firstCompartment = rucksack.substring(0, middle)
        val secondCompartment = rucksack.substring(middle)
        for (firstItem in firstCompartment) {
            if (secondCompartment.contains(firstItem)) {
                return firstItem
            }
        }
        return ' '
    }

    private fun findGroupBadges(input: List<String>): List<Char> {
        val groupBadges = mutableListOf<Char>()
        val groups = input.chunked(3)
        for (group in groups) {
            groupBadges.add(findGroupBadge(group))
        }
        return groupBadges
    }

    private fun findGroupBadge(group: List<String>): Char {
        for (item in group.first()) {
            if (group[1].contains(item) && group[2].contains(item)) {
                return item
            }
        }
        return ' '
    }

    fun sumPrioritiesForCommonItemTypes(): Int {
        return commonItemTypes.sumOf { PRIORITIES.indexOf(it) + 1 }
    }

    fun sumGroupBadgePriorities(): Int {
        return groupBadges.sumOf { PRIORITIES.indexOf(it) + 1 }
    }

    companion object {
        val PRIORITIES = CharRange('a', 'z').toList() + CharRange('A', 'Z').toList()
    }
}