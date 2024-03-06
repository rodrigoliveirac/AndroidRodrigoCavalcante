package com.rodcollab.androidrodrigocavalcante.database

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonArray
import org.json.JSONArray
import org.json.JSONObject

class LegendaTypeConverter {
    @TypeConverter
    fun fromString(value: String?): ArrayList<String>? {
        val gson = Gson()
        return value?.let {

            val jsonObject = gson.fromJson(it, JsonArray::class.java)

            ArrayList(jsonObject.map { element -> element.asJsonObject["legenda"].asString })
        }
    }

    @TypeConverter
    fun legendasToString(legendas: ArrayList<String>?): String {
        val array = JSONArray()
        legendas?.map { legenda ->
            var jsonObject = JSONObject()
            jsonObject = legenda.run {
                jsonObject.put("legenda", this)
                jsonObject
            }
            array.put(jsonObject)
        }
        Log.d("check_legendas_jsonArray", array.toString())
        return array.toString()
    }
}