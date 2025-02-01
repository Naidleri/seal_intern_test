package com.haris.core.injection

import com.haris.core.data.network.ApiConfig
import com.haris.core.data.network.PeopleRepository
import com.haris.core.domain.repository.IPeopleRepository
import org.koin.dsl.module

val networkModule = module {
    single { ApiConfig.getApiService() }
}

val repositoryModule = module {
    single <IPeopleRepository> { PeopleRepository(get()) }
}