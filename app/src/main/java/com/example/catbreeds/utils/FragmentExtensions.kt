package com.example.catbreeds.utils

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

inline fun <reified T : Fragment> instanceOf(vararg params: Pair<String, Any?>): T =
    T::class.java.newInstance().apply { arguments = bundleOf(*params) }
