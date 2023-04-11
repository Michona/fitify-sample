package com.michona.fitify.domain.data.dtos

import com.google.gson.annotations.SerializedName

/* Network DTOs, should only be used in the network layer. */

data class Instructions(
    @SerializedName("hints")
    val hints: List<String>,
    @SerializedName("breathing")
    val breathing: List<String>,
)
