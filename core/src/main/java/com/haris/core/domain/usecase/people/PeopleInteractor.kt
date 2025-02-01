package com.haris.core.domain.usecase.people

import com.haris.core.domain.repository.IPeopleRepository

class PeopleInteractor (
    private val peopleRepository: IPeopleRepository
):PeopleUseCase {
    override fun getPeople() = peopleRepository.getPeople()
}