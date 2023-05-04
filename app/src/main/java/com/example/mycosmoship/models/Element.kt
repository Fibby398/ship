package com.example.mycosmoship.models

import com.example.mycosmoship.enums.Material

data class Element (
    val viewId: Int,
    val material: Material,
    val coordinate: Coordinate
        ){
}