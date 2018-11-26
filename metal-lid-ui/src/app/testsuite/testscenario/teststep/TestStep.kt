package app.scenario

import material.Button
import material.Card
import material.TextField
import react.*
import react.dom.p

fun testStep(rBuilder: RBuilder): ReactElement = rBuilder.child(TestStep::class) {}

class TestStep : RComponent<RProps, RState>() {

    override fun RBuilder.render() {
        Card {
            attrs {
                className = "testStepCard"
            }
            Button {
                attrs {
                    className = "removeButton"
                    color = "primary"
                }
                +"x"
            }
            p {
                +"Test Step"
            }
            TextField {
                attrs {
                    id = "standard-uncontrolled"
                    label = "Name"
                    className = "textField"
                    margin = "normal"
                    variant = "filled"
                }
            }
            TextField {
                attrs {
                    id = "standard-uncontrolled"
                    label = "Class"
                    className = "textField"
                    margin = "normal"
                    variant = "filled"
                }
            }
            TextField {
                attrs {
                    id = "standard-uncontrolled"
                    label = "Method"
                    className = "textField"
                    margin = "normal"
                    variant = "filled"
                }
            }
            TextField {
                attrs {
                    id = "standard-uncontrolled"
                    label = "Value"
                    className = "textField"
                    margin = "normal"
                    variant = "filled"
                }
            }
            TextField {
                attrs {
                    id = "standard-uncontrolled"
                    label = "Output"
                    className = "textField"
                    margin = "normal"
                    variant = "filled"
                }
            }
        }
    }

}