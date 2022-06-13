package com.s3lfcode.tau.presentation.utils

import android.annotation.SuppressLint
import com.s3lfcode.tau.presentation.utils.Constants.OPEN_GOOGLE
import com.s3lfcode.tau.presentation.utils.Constants.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object BotResponse {

    @SuppressLint("SimpleDateFormat")
    fun basicResponses(message: String): String {

        val random = (0..2).random()
        val m = message.lowercase(Locale.getDefault())
        //message.lowercase()
        return when {

            //Подбросить монетку
            m.contains("подбрось") && m.contains("монетку") -> {
                val r = (0..1).random()
                val result = if (r == 0) "Орёл" else "Решка"

                "Я подбрасываю монетку и выпадает...  $result!"
            }

            //Математические вычисления
            m.contains("реши") -> {
                val equation: String? = m.substringAfterLast("реши")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Извините, я не могу это посчитать"
                }
            }

            //Привет
            m.contains("привет") -> {
                when (random) {
                    0 -> "Привет!"
                    1 -> "Здравствуйте!"
                    2 -> "Добрый день!"
                    else -> "error" }
            }

            //Как дела?
            m.contains("как") && message.contains("дела?") -> {
                when (random) {
                    0 -> "Всё хорошо, спасибо!"
                    1 -> "Устал от одиночества... Пообщаемся?"
                    2 -> "Прекрасно!"
                    else -> "Ошибка"
                }
            }

            //Сколько времени?
            m.contains("сколько времени?") || m.contains("время?") ||
                    m.contains("который час?") || m.contains("дата?") -> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }
            ////////////-------------------------
            //Балят 2
            m.contains("в чём сила балят?") -> {
                "В бабухах"
            }
            m.contains("все говорят, что в бабухах") -> {
                "..."
            }
            m.contains("а я думаю, что сила в анжуманиях") -> {
                "...\n Да, а ещё бегат и прес качат балят"
            }
            ////////////-------------------------

            //Команды для информации по университету
            m.contains("!") && m.contains("буфет") -> {
                "Буфет МГУ Огарёва.\n-> Место: главный корпус 1,2 и 9 этаж.\n-> Время работы: ПН-ПТ 9.00-17.00\nСБ-ВС выходной"
            }
            m.contains("а я думаю, что сила в анжуманиях") -> {
                "...\n Да, а ещё бегат и прес качат балят"
            }
            m.contains("а я думаю, что сила в анжуманиях") -> {
                "...\n Да, а ещё бегат и прес качат балят"
            }
            m.contains("а я думаю, что сила в анжуманиях") -> {
                "...\n Да, а ещё бегат и прес качат балят"
            }
            m.contains("а я думаю, что сила в анжуманиях") -> {
                "...\n Да, а ещё бегат и прес качат балят"
            }
            m.contains("а я думаю, что сила в анжуманиях") -> {
                "...\n Да, а ещё бегат и прес качат балят"
            }

            //Open Google
            m.contains("открой гугл") || m.contains("open google") || m.contains("открой google")-> {
                OPEN_GOOGLE
            }

            //Search on the internet
            message.contains("найти")-> {
                OPEN_SEARCH
            }

            //Ответ
            m.contains("окей") || m.contains("конечно") || m.contains("понятно") -> {
                ":D"
            }

            //Когда бот не понимает, что делать...
            else -> {
                when (random) {
                    0 -> "Не понимаю..."
                    1 -> "Попробуй спросить иначе..."
                    2 -> "Этого не умею..."
                    else -> "ошибка"
                }
            }
        }
    }
}