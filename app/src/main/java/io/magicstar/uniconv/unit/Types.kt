package io.magicstar.uniconv.unit

val length_reference: HashMap<String, Double> = hashMapOf(
    "mm" to .001,
    "cm" to .01,
    "dm" to .1,
    "m" to 1.0,
    "dam" to 10.0,
    "hm" to 100.0,
    "km" to 1000.0,

    "in" to .0254,
    "yd" to .9144,
    "ft" to .3048,
    "mi" to 1609.344,
    "nm" to 1852.0,
)
val speed_reference: HashMap<String, Double> = hashMapOf(
    "km/h" to .27777778,
    "m/s" to 1.0,
    "mi/h" to .44704,
    "nu" to .51444444,
    "ft/s" to .3048,
    "mach" to 343.0
)
val elec_current_reference: HashMap<String, Double> = hashMapOf(
    "μA" to .000001,
    "mA" to .001,
    "A" to 1.0,
    "kA" to 1000.0,
    "MA" to 1_000_000.0,
    "GA" to 1.0000E+9,
    "TA" to 1.0000E+12
)
val power_reference: HashMap<String, Double> = hashMapOf(
    "W" to 1.0,
    "kW" to 1000.0,
    "MW" to 1_000_000.0,
    "HP" to 745.699872,
    "CV" to 735.5,
    "kcal/h" to 1.1630556,
    "cal/h" to (1.1630556 / 1000)
)
val heat_reference: HashMap<String, Double> = hashMapOf(
    "ºC" to 1.0,
    "ºF" to 32.4,
    "ºK" to 273.15
)
val surface_reference: HashMap<String, Double> = hashMapOf(
    "km2" to 1_000_000.0,
    "ha" to 10_000.0,
    "a" to 100.0,
    "m2" to 1.0,
    "dm2" to .01,
    "cm2" to .0001,
    "mm2" to .000001,

    "mi2" to 2.5900E+6,
    "acre" to 4046.85642,
    "yd2" to .83612736,
    "ft2" to .09290304,
    "in2" to .00064516
)
val time_reference: HashMap<String, Double> = hashMapOf(
    "ms" to .001,
    "s" to 1.0,
    "min" to 60.0,
    "h" to 3600.0,
    "day" to 86400.0,
    "week" to 604800.0,
    "month" to 2628000.0,
    "year" to 31536000.0,
    "decade" to 315360000.0,
    "century" to 3.1536E+9,
    "millenium" to 3.1536E+10
)
val volume_reference: HashMap<String, Double> = hashMapOf(
    "km3" to 1E+9,
    "m3" to 1.0,
    "dm3" to .001,
    "cm3" to .000001,
    "mm3" to 1.0E-9,

    "kl" to 1.0,
    "hl" to .1,
    "dal" to .01,
    "l" to .001,
    "dl" to .0001,
    "cl" to .00001,
    "ml" to .000001,

    "gal (US)" to .00378541,
    "gal" to .00454609,
    "qt (US)" to .946352946 * .001,
    "qt" to 1.1365225 * .001,
    "oz (fluid) (US)" to 29.5735295625 * .000001,
    "oz (fluid)" to 28.4130642624 * .000001,

    "ft3" to .02831685,
    "in3" to .00001639,
    "yd3" to .76455486,
    "mi3" to 4.1682E+9
)
val weight_reference: HashMap<String, Double> = hashMapOf(
    "ton" to 1_000_000.0,
    "kg" to 1000.0,
    "hg" to 100.0,
    "dag" to 10.0,
    "g" to 1.0,
    "dg" to .1,
    "cg" to .01,
    "mg" to .001,

    "lb" to 453.59237,
    "drac" to 1.771845195309973,
    "oz" to 28.3495231
)
