package com.github.radtin.metallid.action

import com.github.radtin.metallid.domain.Output
import com.github.radtin.metallid.domain.TestConfiguration


class System {

    fun printlnMessage(config: TestConfiguration, input: Any): Output {
        println(input)
        return Output(input)
    }

}