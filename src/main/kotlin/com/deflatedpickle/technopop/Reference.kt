package com.deflatedpickle.technopop

object Reference {
    const val MOD_ID = "technopop"
    const val NAME = "Technopop"
    // Versions follow this format: MCVERSION-MAJORMOD.MAJORAPI.MINOR.PATCH.
    const val VERSION = "1.12.2-0.0.1.0"
    const val ACCEPTED_VERSIONS = "[1.12.1, 1.12.2]"

    const val CLIENT_PROXY_CLASS = "com.deflatedpickle.technopop.proxy.ClientProxy"
    const val SERVER_PROXY_CLASS = "com.deflatedpickle.technopop.proxy.ServerProxy"

    const val DEPENDENCIES = "required-after:forgelin;required-after:glasspane"
    const val ADAPTER = "net.shadowfacts.forgelin.KotlinAdapter"
}