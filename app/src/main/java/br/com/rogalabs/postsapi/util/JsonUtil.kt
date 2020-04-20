package br.com.rogalabs.postsapi.util

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import java.lang.reflect.Type

class JsonUtil {

    companion object{
        fun <T> getListFromJson(jsonString: String?, type: Type?): MutableList<T>? {
            return if (!isValid(jsonString)) {
                null
            } else Gson().fromJson(jsonString, type)
        }

        private fun isValid(json: String?): Boolean {
            return try {
                JsonParser().parse(json)
                true
            } catch (jse: JsonSyntaxException) {
                false
            }
        }
    }
}