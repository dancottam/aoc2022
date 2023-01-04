import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day05Tests {

    private val input = """
    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2
    """.trimIndent().split("\n")

    private val crateMover9000 = CrateMover9000(input)

    @Test
    fun `There are 3 stacks`() {
        assertEquals(3, crateMover9000.stacks.size)
    }

    @Test
    fun `First stack contains crates N, Z`() {
        assertEquals(dequeOf('N', 'Z'), crateMover9000.stacks[0])
    }

    @Test
    fun `Second stack contains crates D, C, M`() {
        assertEquals(dequeOf('D', 'C', 'M'), crateMover9000.stacks[1])
    }

    @Test
    fun `Third stack contains crates P`() {
        assertEquals(dequeOf('P'), crateMover9000.stacks[2])
    }

    @Test
    fun `First step yields D, N, Z in first stack`() {
        crateMover9000.runSteps(1)
        assertEquals(dequeOf('D', 'N', 'Z'), crateMover9000.stacks[0])
    }

    @Test
    fun `Second step yields Z, N, D, P in third stack`() {
        crateMover9000.runSteps(2)
        assertEquals(dequeOf('Z', 'N', 'D', 'P'), crateMover9000.stacks[2])
    }

    @Test
    fun `Running all steps yields C, M, ZNDP`() {
        crateMover9000.runAllSteps()
        assertEquals(dequeOf('C'), crateMover9000.stacks[0])
        assertEquals(dequeOf('M'), crateMover9000.stacks[1])
        assertEquals(dequeOf('Z', 'N', 'D', 'P'), crateMover9000.stacks[2])
    }

    @Test
    fun `Crates at top of each stack after running all steps with CrateMover 9000 are CMZ`() {
        crateMover9000.runAllSteps()
        assertEquals("CMZ", crateMover9000.topCrates())
    }

    @Test
    fun `Crates at top of each stack after running all steps with CrateMover 9001 are MCD`() {
        val crateMover9001 = CrateMover9001(input)
        crateMover9001.runAllSteps()
        assertEquals("MCD", crateMover9001.topCrates())
    }

    private fun dequeOf(vararg chars: Char): ArrayDeque<Char> {
        return ArrayDeque(chars.toList())
    }
}
