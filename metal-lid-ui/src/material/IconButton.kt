@file:JsModule("@material-ui/core/IconButton")

package material

import react.RClass
import react.RProps

@JsName("default")
external val IconButton: RClass<IconButtonProps>

external interface IconButtonProps : RProps {
    var className: String?
    var ariaLabel: String
    var color: String
}