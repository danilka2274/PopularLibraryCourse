package com.example.popularlibrarycourse.presenter.convert

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

/****
Project PopularLibrary
Package softing.ubah4ukdev.popularlibrary.presenter.convert

Created by Ivan Sheynmaer

2021.08.14
v1.0
 */
class ConvertScreen() : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        ConvertFragment.newInstance()
}