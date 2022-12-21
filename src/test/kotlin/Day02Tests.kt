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
    fun `Calculate first round score`() {
        assertEquals(8, rockPaperScissors.roundScores[0])
    }

    @Test
    fun `Calculate second round score`() {
        assertEquals(1, rockPaperScissors.roundScores[1])
    }

    @Test
    fun `Calculate third round score`() {
        assertEquals(6, rockPaperScissors.roundScores[2])
    }

    @Test
    fun `Calculate total score`() {
        assertEquals(15, rockPaperScissors.totalScore())
    }
}