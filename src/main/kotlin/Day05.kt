fun main() {
    fun part1(input: List<String>): String {
        val shipCargo = ShipCargo(input)
        shipCargo.runAllStepsWithCrateMover9000()
        return shipCargo.topCrates()
    }

    fun part2(input: List<String>): String {
        val shipCargo = ShipCargo(input)
        shipCargo.runAllStepsWithCrateMover9001()
        return shipCargo.topCrates()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}

class ShipCargo(input: List<String>) {

    val stacks = calculateStacks(input)
    private val steps = parseSteps(input)

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

    fun runAllStepsWithCrateMover9000() {
        runStepsWithCrateMover9000(steps.size)
    }

    fun runStepsWithCrateMover9000(num: Int) {
        repeat(num) {
            runNextStep(true)
        }
    }

    fun runAllStepsWithCrateMover9001() {
        runStepsWithCrateMover9001(steps.size)
    }

    fun runStepsWithCrateMover9001(num: Int) {
        repeat(num) {
            runNextStep(false)
        }
    }

    private fun runNextStep(moveCratesIndividually: Boolean) {
        val nextStep = steps.removeFirst()
        if (moveCratesIndividually) {
            repeat(nextStep.amount) {
                val crate = stacks[nextStep.from - 1].removeFirst()
                stacks[nextStep.to - 1].addFirst(crate)
            }
        } else {
            val intermediate = mutableListOf<Char>()
            repeat(nextStep.amount) {
                intermediate.add(stacks[nextStep.from - 1].removeFirst())
            }
            for (crate in intermediate.reversed()) {
                stacks[nextStep.to - 1].addFirst(crate)
            }
        }
    }

    fun topCrates(): String {
        val topCrates = StringBuilder()
        for (stack in stacks) {
            topCrates.append(stack.first())
        }
        return topCrates.toString()
    }

}

data class Step (
    val amount: Int,
    val from: Int,
    val to: Int
)