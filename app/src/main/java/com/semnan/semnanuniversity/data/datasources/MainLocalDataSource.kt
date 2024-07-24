package com.semnan.semnanuniversity.data.datasources

import android.content.Context
import com.semnan.semnanuniversity.R
import com.semnan.semnanuniversity.data.model.MainItems
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MainLocalDataSource @Inject constructor(@ApplicationContext private val mContext: Context) {

    fun getMainItems(): List<MainItems>{
        return listOf(
            MainItems("tour", R.drawable.virtual_tour_logo, mContext.getString(R.string.virtual_tour_semnan)),
            MainItems("intro_to_semnan_uni", R.drawable.semnan_intro, mContext.getString(R.string.intro_semnan)),
            MainItems("intro_to_semnan_city", R.drawable.semnan_city_intro, mContext.getString(R.string.intro_semnan_city)),
            MainItems("daneshkade_ha", R.drawable.c1, mContext.getString(R.string.daneshkade_ha)),
            MainItems("moavenat_ha", R.drawable.i1, mContext.getString(R.string.moavenat_ha)),
            MainItems("ostad_ha", R.drawable.profesor2, mContext.getString(R.string.ostad_ha)),
            MainItems("reshte_ha", R.drawable.majors, mContext.getString(R.string.majors)),
            MainItems("news", R.drawable.news, mContext.getString(R.string.news)),
            MainItems("shmare_ha", R.drawable.phone, mContext.getString(R.string.phones)),
            MainItems("dastavard", R.drawable.achiv, mContext.getString(R.string.achievement)),
            MainItems("pardis_map", R.drawable.pardis_logo, mContext.getString(R.string.pardis_map)),
            MainItems("city_map", R.drawable.city_map, mContext.getString(R.string.city_map)),
            MainItems("my", R.drawable.lms, mContext.getString(R.string.my)),
            MainItems("book", R.drawable.simorgh, mContext.getString(R.string.simorgh))

        )
    }


}