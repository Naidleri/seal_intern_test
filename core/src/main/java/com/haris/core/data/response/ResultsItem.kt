package com.haris.core.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResultsItem(

	@field:SerializedName("films")
	val films: List<String?>? = null,

	@field:SerializedName("homeworld")
	val homeworld: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("skin_color")
	val skinColor: String? = null,

	@field:SerializedName("edited")
	val edited: String? = null,

	@field:SerializedName("created")
	val created: String? = null,

	@field:SerializedName("mass")
	val mass: String? = null,

	@field:SerializedName("vehicles")
	val vehicles: List<String?>? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("hair_color")
	val hairColor: String? = null,

	@field:SerializedName("birth_year")
	val birthYear: String? = null,

	@field:SerializedName("eye_color")
	val eyeColor: String? = null,

	@field:SerializedName("species")
	val species: List<String?>? = null,

	@field:SerializedName("starships")
	val starships: List<String?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("height")
	val height: String? = null
) : Parcelable