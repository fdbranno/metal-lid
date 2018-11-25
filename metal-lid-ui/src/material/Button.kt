@file:JsModule("@material-ui/core/Button")

package material

import react.RClass
import react.RProps

@JsName("default")
external val Button: RClass<ButtonProps>

external interface ButtonProps : RProps {
    var disabled: Boolean
    var onClick:()-> Unit
    var color: String
    var className: String?
    var variant: String
}