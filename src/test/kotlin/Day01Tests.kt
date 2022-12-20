import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01Tests {

    val input = """
        1000
        2000
        3000

        4000

        5000
        6000

        7000
        8000
        9000

        10000
    """.trimIndent().split("\n")

    private val calorieCounter = CalorieCounter(input)

    @Test
    fun `First elf has 6000 calories`() {
        val elves = calorieCounter.elfTotals
        assertEquals(6000, elves[0])
    }

    @Test
    fun `Second elf has 4000 calories`() {
        val elves = calorieCounter.elfTotals
        assertEquals(4000, elves[1])
    }

    @Test
    fun `Most calories carried by an elf is 24000`() {
        assertEquals(24000, calorieCounter.mostCaloriesCarriedByAnElf())
    }

    @Test
    fun `Calories carried by top 3 elves`() {
        assertEquals(45000, calorieCounter.caloriesCarriedByTopThreeElves())
    }
}