package app.scenario

import material.Button
import material.Card
import react.*
import react.dom.p

fun testScenario(rBuilder: RBuilder): ReactElement = rBuilder.child(TestScenario::class) {}

class TestScenario : RComponent<RProps, RState>() {

    override fun RBuilder.render() {
        Card {
            attrs {
                className = "testScenarioCard"
            }
            Button {
                attrs {
                    className = "removeButton"
                    color = "primary"
                }
                +"x"
            }
            p {
                +"Test Scenario"
            }
            testStep(this)

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