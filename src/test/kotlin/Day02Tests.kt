import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day02Tests {

    private val input = """
        A Y
        B X
        C Z
    """.trimIndent().split("\n")

    private val rockPaperScissors = RockPaperScissors(input)

    @Test
    fun `Calculate part 1 first round score`() {
        assertEquals(8, rockPaperScissors.part1RoundScores[0])
    }

    @Test
    fun `Calculate part 1 second round score`() {
        assertEquals(1, rockPaperScissors.part1RoundScores[1])
    }

    @Test
    fun `Calculate part 1 third round score`() {
        assertEquals(6, rockPaperScissors.part1RoundScores[2])
    }

    @Test
    fun `Calculate part 1 total score`() {
        assertEquals(15, rockPaperScissors.part1TotalScore())
    }

    @Test
    fun `Calculate part 2 first round score`() {
        assertEquals(4, rockPaperScissors.part2RoundScores[0])
    }

    @Test
    fun `Calculate part 2 second round score`() {
        assertEquals(1, rockPaperScissors.part2RoundScores[1])
    }

    @Test
    fun `Calculate part 2 third round score`() {
        assertEquals(7, rockPaperScissors.part2RoundScores[2])
    }

    @Test
    fun `Calculate part 2 total score`() {
        assertEquals(12, rockPaperScissors.part2TotalScore())
    }
}