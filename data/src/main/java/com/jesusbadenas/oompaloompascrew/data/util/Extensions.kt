package com.jesusbadenas.oompaloompascrew.data.util

import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompa
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompaDetail
import com.jesusbadenas.oompaloompascrew.data.entities.response.OompaLoompaData

fun OompaLoompaData.toOompaLoompa() = OompaLoompa(
    id = id,
    firstName = firstName,
    lastName = lastName,
    profession = profession,
    image = image,
    gender = gender
)

fun OompaLoompaData.toOompaLoompaDetail() = OompaLoompaDetail(
    id = id,
    firstName = firstName,
    lastName = lastName,
    profession = profession,
    image = image,
    gender = gender,
    height = height,
    country = country,
    age = age,
    email = email,
    description = description
)
