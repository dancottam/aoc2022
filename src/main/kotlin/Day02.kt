import RockPaperScissors.Hand.*

fun main() {
    fun part1(input: List<String>): Int {
        val rps = RockPaperScissors(input)
        return rps.part1TotalScore()
    }

    fun part2(input: List<String>): Int {
        val rps = RockPaperScissors(input)
        return rps.part2TotalScore()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

class RockPaperScissors(input: List<String>) {

    val part1RoundScores = calculatePart1RoundScores(input)

    val part2RoundScores = calculatePart2RoundScores(input)

    private fun calculatePart1RoundScores(input: List<String>): List<Int> {
        val roundScores = mutableListOf<Int>()
        for (round in input) {
            val parts = round.split(" ")
            val them = Hand.parse(parts[0])
            val me = Hand.parse(parts[1])
            roundScores.add(roundScore(them, me))
        }
        return roundScores
    }

    private fun calculatePart2RoundScores(input: List<String>): List<Int> {
        val roundScores = mutableListOf<Int>()
        for (round in input) {
            val parts = round.split(" ")
            val them = Hand.parse(parts[0])
            val outcome = parts[1]
            val me = determinePlay(them, outcome)
            roundScores.add(roundScore(them, me))
        }
        return roundScores
    }

    private fun determinePlay(them: Hand, outcome: String): Hand {
        return when (outcome) {
            "X" -> when (them) {
                    ROCK -> SCISSORS
                    PAPER -> ROCK
                    SCISSORS -> PAPER
                }
            "Z" -> when (them) {
                    ROCK -> PAPER
                    PAPER -> SCISSORS
                    SCISSORS-> ROCK
                }
            else -> them
        }
    }

    private fun roundScore(them: Hand, me: Hand): Int {
        val score = mapOf(ROCK to 1, PAPER to 2, SCISSORS to 3)
        return score[me]?.plus(outcomeScore(them, me)) ?: 0
    }

    private fun outcomeScore(them: Hand, me: Hand): Int {
        return if (win(them, me)) 6
        else if (draw(them, me)) 3
        else 0
    }

    private fun win(them: Hand, me: Hand): Boolean {
        return (them == ROCK && me == PAPER) ||
                (them == PAPER && me == SCISSORS) ||
                (them == SCISSORS && me == ROCK)
    }

    private fun draw(them: Hand, me: Hand): Boolean {
        return them == me
    }

    fun part1TotalScore(): Int {
        return part1RoundScores.sum()
    }

    fun part2TotalScore(): Int {
        return part2RoundScores.sum()
    }

    enum class Hand {
        ROCK,
        PAPER,
        SCISSORS;

        companion object {
            fun parse(input: String): Hand {
                return when(input) {
                    "A", "X" -> ROCK
                    "B", "Y" -> PAPER
                    "C", "Z" -> SCISSORS
                    else -> throw IllegalArgumentException("")
                }
            }
        }

    }
}