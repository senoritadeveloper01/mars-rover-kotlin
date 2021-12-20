package com.nils.marsrover.model.type

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertFailsWith

@RunWith(SpringRunner::class)
internal class CommandTypeUnitTest {

    @Test
    fun test_get_command_by_key_null() {
        assertFailsWith<NoSuchElementException>(
            block = { CommandType.getCommandByKey('X') }
        )
    }
}