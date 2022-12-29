import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day04Tests {
    private val input = """
        2-4,6-8
        2-3,4-5
        5-7,7-9
        2-8,3-7
        6-6,4-6
        2-6,4-8
    """.trimIndent().split("\n")

    private val sectionAssignments = SectionAssignments(input)

    @Test
    fun `First pair is range 2-4 and 6-8`() {
        assertEquals(Pair(
            IntRange(2, 4),
            IntRange(6, 8)
        ), sectionAssignments.pairs[0])
    }

    @Test
    fun `2 pairs have fully overlapping ranges`() {
        assertEquals(2, sectionAssignments.numFullyOverlappingPairs())
    }

    @Test
    fun `4 pairs have partially overlapping ranges`() {
        assertEquals(4, sectionAssignments.numOverlappingPairs())
    }

}
