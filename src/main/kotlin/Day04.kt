fun main() {
    fun part1(input: List<String>): Int {
        val sectionAssignments = SectionAssignments(input)
        return sectionAssignments.numFullyOverlappingPairs()
    }

    fun part2(input: List<String>): Int {
        val sectionAssignments = SectionAssignments(input)
        return sectionAssignments.numOverlappingPairs()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

class SectionAssignments(input: List<String>) {

    val pairs = parsePairs(input)

    private fun parsePairs(input: List<String>): List<Pair<IntRange, IntRange>> {
        val pairs = mutableListOf<Pair<IntRange, IntRange>>()
        for (line in input) {
            val elves = line.split(",")
            val assignments = mutableListOf<IntRange>()
            for (elf in elves) {
                val sections = elf.split("-")
                val assignment = IntRange(sections.first().toInt(), sections.last().toInt())
                assignments.add(assignment)
            }
            pairs.add(Pair(assignments.first(), assignments.last()))
        }
        return pairs
    }

    fun numFullyOverlappingPairs(): Int {
        var numPairs = 0
        for (pair in pairs) {
            if (rangesFullyOverlap(pair.first, pair.second)) {
                numPairs++
            }
        }
        return numPairs
    }

    private fun rangesFullyOverlap(first: IntRange, second: IntRange): Boolean {
        return (first.contains(second.min()) && first.contains(second.max())) ||
                (second.contains(first.min()) && second.contains(first.max()))
    }

    fun numOverlappingPairs(): Int {
        var numPairs = 0
        for (pair in pairs) {
            if (rangesOverlap(pair.first, pair.second)) {
                numPairs++
            }
        }
        return numPairs
    }

    private fun rangesOverlap(first: IntRange, second: IntRange): Boolean {
        return first.contains(second.min()) || first.contains(second.max()) ||
                second.contains(first.min()) || second.contains(first.max())
    }

}