package com.dave.pay

import com.google.gson.annotations.SerializedName

data class Transfer (
    @SerializedName("account_bank") val accountBank: String?,
    @SerializedName("account_number") val accountNumber: String?,
    @SerializedName("amount") val amount: Int?,
    @SerializedName("currency") val currency: String?
)