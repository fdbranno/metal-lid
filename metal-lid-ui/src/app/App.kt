package app

import app.navigation.navigation
import app.scenario.testSuite
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div

class App : RComponent<RProps, RState>() {

    override fun RBuilder.render() {

        div {
            attrs["className"] = "root"
            navigation(this)
            testSuite(this)
        }
    }
}

fun RBuilder.app() = child(App::class) {}
