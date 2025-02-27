package com.kpass.newentry.shared

interface NewEntryFeatureCommunicator {

    fun getFeature(newEntryArgs: NewEntryArgs)

    companion object{
        const val newEntryNavKey = "newEntryNavKey"
    }

    data class NewEntryArgs(
        val destination: String
    )
}