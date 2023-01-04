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

    private val shipCargo = ShipCargo(input)

    @Test
    fun `There are 3 stacks`() {
        assertEquals(3, shipCargo.stacks.size)
    }

    @Test
    fun `First stack contains crates N, Z`() {
        assertEquals(dequeOf('N', 'Z'), shipCargo.stacks[0])
    }

    @Test
    fun `Second stack contains crates D, C, M`() {
        assertEquals(dequeOf('D', 'C', 'M'), shipCargo.stacks[1])
    }

    @Test
    fun `Third stack contains crates P`() {
        assertEquals(dequeOf('P'), shipCargo.stacks[2])
    }

    @Test
    fun `First step yields D, N, Z in first stack`() {
        shipCargo.runStepsWithCrateMover9000(1)
        assertEquals(dequeOf('D', 'N', 'Z'), shipCargo.stacks[0])
    }

    @Test
    fun `Second step yields Z, N, D, P in third stack`() {
        shipCargo.runStepsWithCrateMover9000(2)
        assertEquals(dequeOf('Z', 'N', 'D', 'P'), shipCargo.stacks[2])
    }

    @Test
    fun `Running all steps yields C, M, ZNDP`() {
        shipCargo.runAllStepsWithCrateMover9000()
        assertEquals(dequeOf('C'), shipCargo.stacks[0])
        assertEquals(dequeOf('M'), shipCargo.stacks[1])
        assertEquals(dequeOf('Z', 'N', 'D', 'P'), shipCargo.stacks[2])
    }

    @Test
    fun `Crates at top of each stack after running all steps with CrateMover 9000 are CMZ`() {
        shipCargo.runAllStepsWithCrateMover9000()
        assertEquals("CMZ", shipCargo.topCrates())
    }

    @Test
    fun `Crates at top of each stack after running all steps with CrateMover 9001 are MCD`() {
        shipCargo.runAllStepsWithCrateMover9001()
        assertEquals("MCD", shipCargo.topCrates())
    }

    private fun dequeOf(vararg chars: Char): ArrayDeque<Char> {
        return ArrayDeque(chars.toList())
    }
}
