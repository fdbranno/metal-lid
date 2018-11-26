package app.navigation

import material.AppBar
import material.Toolbar
import material.Typography
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

fun navigation(rBuilder: RBuilder) = rBuilder.child(Navigation::class) {}

class Navigation : RComponent<RProps, RState>() {

    override fun RBuilder.render() {
        AppBar {
            attrs {
                className = "appBar"
                color = "primary"
                position = "static"
            }
            Toolbar {
                Typography {
                    attrs {
                        className = "menuText"
                        color = "inherit"
                        variant = "h6"
                    }
                    +"Metal-Lid"
                }
            }
        }
    }

}