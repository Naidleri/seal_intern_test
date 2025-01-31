package com.haris.core.utils

import com.haris.core.data.response.ResultsItem
import com.haris.core.domain.model.People


object PeopleMapper {

    fun mapResultsItemToPeople(resultsItem: ResultsItem): People {
        return People(
            films = resultsItem.films,
            homeworld = resultsItem.homeworld,
            gender = resultsItem.gender,
            skinColor = resultsItem.skinColor,
            edited = resultsItem.edited,
            created = resultsItem.created,
            mass = resultsItem.mass,
            vehicles = resultsItem.vehicles,
            url = resultsItem.url,
            hairColor = resultsItem.hairColor,
            birthYear = resultsItem.birthYear,
            eyeColor = resultsItem.eyeColor,
            species = resultsItem.species,
            starships = resultsItem.starships,
            name = resultsItem.name,
            height = resultsItem.height
        )
    }

    fun mapPeopleToResultsItem(people: People): ResultsItem {
        return ResultsItem(
            films = people.films,
            homeworld = people.homeworld,
            gender = people.gender,
            skinColor = people.skinColor,
            edited = people.edited,
            created = people.created,
            mass = people.mass,
            vehicles = people.vehicles,
            url = people.url,
            hairColor = people.hairColor,
            birthYear = people.birthYear,
            eyeColor = people.eyeColor,
            species = people.species,
            starships = people.starships,
            name = people.name,
            height = people.height
        )
    }
}