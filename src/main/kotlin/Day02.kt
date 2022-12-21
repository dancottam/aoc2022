fun main() {
    fun part1(input: List<String>): Int {
        val rps = RockPaperScissors(input)
        return rps.totalScore()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

class RockPaperScissors(input: List<String>) {

    val roundScores = calculateRoundScores(input)

    private fun calculateRoundScores(input: List<String>): List<Int> {
        val roundScores = mutableListOf<Int>()
        for (round in input) {
            roundScores.add(roundScore(round))
        }
        return roundScores
    }

    private fun roundScore(input: String): Int {
        val parts = input.split(" ")
        val them = parts[0]
        val me = parts[1]

        val score = mapOf("X" to 1, "Y" to 2, "Z" to 3)

        return score[me]?.plus(outcomeScore(them, me)) ?: 0
    }

    private fun outcomeScore(them: String, me: String): Int {
        return if (win(them, me)) 6
        else if (draw(them, me)) 3
        else 0
    }

    private fun win(them: String, me: String): Boolean {
        return (them == "A" && me == "Y") ||
                (them == "B" && me == "Z") ||
                (them == "C" && me == "X")
    }

    private fun draw(them: String, me: String): Boolean {
        return (them == "A" && me == "X") ||
                (them == "B" && me == "Y") ||
                (them == "C" && me == "Z")
    }

    fun totalScore(): Int {
        return roundScores.sum()
    }
}