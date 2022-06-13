package com.s3lfcode.tau.presentation.utils

import android.annotation.SuppressLint
import com.s3lfcode.tau.presentation.utils.Constants.OPEN_GOOGLE
import com.s3lfcode.tau.presentation.utils.Constants.OPEN_SEARCH
import com.s3lfcode.tau.presentation.utils.Constants.bookkeeping
import com.s3lfcode.tau.presentation.utils.Constants.canteen
import com.s3lfcode.tau.presentation.utils.Constants.pool
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
                "Буфеты ФМиИТ.\n-> Место: 1,2 и 9 этажи\n-> Время работы: Пн-Пт 9.00-17.00\nВыходной: Сб-Вс"
            }
            m.contains("!") && m.contains("деканат") -> {
                "Деканат ФМиИТ. Место: "
            }
            m.contains("!") && m.contains("бухгалтер") -> {
                "Бухгалтерия МГУ.\n-> Адрес: ул.Демократическая, 69\n" +
                        "-> Место: за главным корпусом\n" +
                        "-> Время работы: Пн-Сб 9.00-18.00\nПерерыв: 13.00-14.00\nВыходной: Вс\n" +
                        "Телефоны:\n- расчёты стипендии: 29-06-87\n" +
                        "- банковские операции: 29-06-88"
            }
            m.contains("!") && (m.contains("интернет центр") || m.contains("печат")) -> {
                "Пока не знаю..."
            }
            m.contains("!") && m.contains("бассейн") -> {
                "Бассейн - Студенческий.\n-> Адрес: ул.Пролетарская, 61а\n" +
                        "-> Место: за главным корпусом\n-> Время работы: Пн-Вс 6.30-21.30\n-> Телефон: 24-28-32"
            }
            m.contains("!") && m.contains("библиотек") -> {
                "Научная библиотека МГУ.\n-> Место: Корпус АБК\n-> Время работы: Пн-Сб 9.00-18.00\nВыходной: Вс"
            }
            m.contains("!") && (m.contains("столов") || m.contains("молодёжн")) -> {
                "Студенческий комбинат питания - Молодёжный.\n-> Адрес: ул.Большевистская, 68в\n-> Место: за главным корпусом\n-> Время работы: Пн-Пт 9.00-17.00\nВыходной: Сб-Вс"
            }


            //Открыть гугл
            m.contains("открой гугл") || m.contains("открой браузер") || m.contains("открой google")-> {
                OPEN_GOOGLE
            }

            //Поиск в браузере
            m.contains("найди") || m.contains("поиск") || m.contains("найти")
                    || m.contains("загугли")|| m.contains("искать") || m.contains("поиск") -> {
                OPEN_SEARCH
            }

            //фото столовой
            (m.contains("фото") || m.contains("картинка") || m.contains("покажи")) && (m.contains("столов") || m.contains("молодёжн")) -> {
                canteen
            }
            //фото студенческого бассейна
            (m.contains("фото") || m.contains("картинка") || m.contains("покажи")) && m.contains("бассейн") -> {
                pool
            }
            //фото бухгалтерия
            (m.contains("фото") || m.contains("картинка") || m.contains("покажи")) && m.contains("бухгалтер") -> {
                bookkeeping
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