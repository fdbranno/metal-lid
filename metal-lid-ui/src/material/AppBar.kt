@file:JsModule("@material-ui/core/AppBar")

package material

import react.RClass
import react.RProps

@JsName("default")
external val AppBar: RClass<AppBarProps>

external interface AppBarProps : RProps {
    var className: String?
    var position: String
    var color: String
}