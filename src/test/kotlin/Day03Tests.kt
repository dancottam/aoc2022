import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day03Tests {

    private val input = """
        vJrwpWtwJgWrhcsFMMfFFhFp
        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
        PmmdzqPrVvPwwTWBwg
        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        ttgJtRGJQctTZtZT
        CrZsJsPPZsGzwwsLwLmpwMDw
    """.trimIndent().split("\n")

    private val rucksacks = Rucksacks(input)

    @Test
    fun `First rucksack compartments share item type p`() {
        assertEquals('p', rucksacks.commonItemTypes[0])
    }

    @Test
    fun `Second rucksack compartments share item type L`() {
        assertEquals('L', rucksacks.commonItemTypes[1])
    }

    @Test
    fun `Third rucksack compartments share item type P`() {
        assertEquals('P', rucksacks.commonItemTypes[2])
    }

    @Test
    fun `Fourth rucksack compartments share item type v`() {
        assertEquals('v', rucksacks.commonItemTypes[3])
    }

    @Test
    fun `Fifth rucksack compartments share item type t`() {
        assertEquals('t', rucksacks.commonItemTypes[4])
    }

    @Test
    fun `Sixth rucksack compartments share item type s`() {
        assertEquals('s', rucksacks.commonItemTypes[5])
    }

    @Test
    fun `Sum of priorities for common item types is 157`() {
        assertEquals(157, rucksacks.sumPrioritiesForCommonItemTypes())
    }

    @Test
    fun `First group badge is item type r`() {
        assertEquals('r', rucksacks.groupBadges[0])
    }

    @Test
    fun `Second group badge is item type Z`() {
        assertEquals('Z', rucksacks.groupBadges[1])
    }

    @Test
    fun `Sum of group badge priorities is 70`() {
        assertEquals(70, rucksacks.sumGroupBadgePriorities())
    }
}

