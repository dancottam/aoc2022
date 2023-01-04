fun main() {
    fun part1(input: List<String>): String {
        val crateMover = CrateMover9000(input)
        crateMover.runAllSteps()
        return crateMover.topCrates()
    }

    fun part2(input: List<String>): String {
        val crateMover = CrateMover9001(input)
        crateMover.runAllSteps()
        return crateMover.topCrates()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}

data class Step (
    val amount: Int,
    val from: Int,
    val to: Int
)

abstract class CrateMover(input: List<String>) {

    val stacks = calculateStacks(input)
    val steps = parseSteps(input)

    private fun calculateStacks(input: List<String>): List<ArrayDeque<Char>> {
        val stackInput = mutableListOf<String>()
        for (line in input) {
            if (line.isEmpty()) break
            stackInput.add(line)
        }
        val numStacks = stackInput.last().chunked(4).size
        val stacks = List(numStacks) { ArrayDeque<Char>() }

        for (line in stackInput.reversed().drop(1)) {
            val layer = line.chunked(4)
            for ((stackIndex, crate) in layer.withIndex()) {
                val contents = crate[1]
                if (! contents.isWhitespace()) {
                    stacks[stackIndex].addFirst(contents)
                }
            }
        }
        return stacks
    }

    private fun parseSteps(input: List<String>): ArrayDeque<Step> {
        val steps = ArrayDeque<Step>()
        for (line in input.reversed()) {
            if (line.isEmpty()) break
            val parts = line.split(" ")
            steps.addFirst(Step(
                amount = parts[1].toInt(),
                from = parts[3].toInt(),
                to = parts[5].toInt()
            ))
        }
        return steps
    }

    fun topCrates(): String {
        val topCrates = StringBuilder()
        for (stack in stacks) {
            topCrates.append(stack.first())
        }
        return topCrates.toString()
    }

    fun runAllSteps() {
        runSteps(steps.size)
    }

    fun runSteps(num: Int) {
        repeat(num) {
            runNextStep()
        }
    }

    private fun runNextStep() {
        val nextStep = steps.removeFirst()
        moveCrates(nextStep.amount, nextStep.from, nextStep.to)
    }

    abstract fun moveCrates(amount: Int, from: Int, to: Int)
}

class CrateMover9000(input: List<String>) : CrateMover(input) {
    override fun moveCrates(amount: Int, from: Int, to: Int) {
        repeat(amount) {
            val crate = stacks[from - 1].removeFirst()
            stacks[to - 1].addFirst(crate)
        }
    }
}

class CrateMover9001(input: List<String>) : CrateMover(input) {
    override fun moveCrates(amount: Int, from: Int, to: Int) {
        val intermediate = mutableListOf<Char>()
        repeat(amount) {
            intermediate.add(stacks[from - 1].removeFirst())
        }
        for (crate in intermediate.reversed()) {
            stacks[to - 1].addFirst(crate)
        }
    }

}