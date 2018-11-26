@file:JsModule("@material-ui/core/Typography")

package material

import react.RClass
import react.RProps

@JsName("default")
external val Typography: RClass<TypographyProps>

external interface TypographyProps : RProps {
    var className: String?
    var variant: String
    var color: String
}