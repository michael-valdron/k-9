package com.fsck.k9.resources

import com.fsck.k9.CoreResourceProvider
import com.fsck.k9.autocrypt.AutocryptStringProvider
import org.koin.dsl.module

val resourcesModule = module {
    single<CoreResourceProvider> {
        K9CoreResourceProvider(
            context = get(),
            appNameProvider = get(),
        )
    }
    single<AutocryptStringProvider> {
        K9AutocryptStringProvider(
            context = get(),
        )
    }
}
