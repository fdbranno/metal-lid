@file:JsModule("@material-ui/core/Card")

package material

import react.RClass
import react.RProps

@JsName("default")
external val Card: RClass<CardProps>

external interface CardProps : RProps {
    var className: String?
}