package app.scenario

import material.Button
import material.Card
import react.*
import react.dom.p

fun testSuite(rBuilder: RBuilder): ReactElement = rBuilder.child(TestSuite::class) {}

class TestSuite : RComponent<RProps, RState>() {

    override fun RBuilder.render() {
        Card {
            attrs {
                className = "appCard"
            }
            p {
                +"Test Suite"
            }
            testScenario(this)

            Card {
                attrs {
                    className = "buttonCard"
                }
                Button {
                    attrs {
                        className = "addButton"
                        color = "primary"
                        variant = "raised"
                    }
                    +"+"
                }
            }
        }
    }

}