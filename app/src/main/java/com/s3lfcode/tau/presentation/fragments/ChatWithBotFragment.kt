package com.s3lfcode.tau.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.s3lfcode.tau.MainActivity
import com.s3lfcode.tau.R
import com.s3lfcode.tau.databinding.FragmentChatwithbotBinding
import com.s3lfcode.tau.domain.model.Message
import com.s3lfcode.tau.presentation.adapter.MessagingAdapter
import com.s3lfcode.tau.presentation.utils.BotResponse
import com.s3lfcode.tau.presentation.utils.Constants.OPEN_GOOGLE
import com.s3lfcode.tau.presentation.utils.Constants.OPEN_SEARCH
import com.s3lfcode.tau.presentation.utils.Constants.RECEIVE_ID
import com.s3lfcode.tau.presentation.utils.Constants.SEND_ID
import com.s3lfcode.tau.presentation.utils.Constants.bookkeeping
import com.s3lfcode.tau.presentation.utils.Constants.canteen
import com.s3lfcode.tau.presentation.utils.Constants.pool
import com.s3lfcode.tau.presentation.utils.Time
import kotlinx.android.synthetic.main.fragment_chatwithbot.*
import kotlinx.coroutines.*

class ChatWithBotFragment : Fragment(R.layout.fragment_chatwithbot) {
    var messagesList = mutableListOf<Message>()
    private lateinit var binding: FragmentChatwithbotBinding
    private lateinit var adapter: MessagingAdapter
    companion object{
        fun newInstance() = ChatWithBotFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatwithbotBinding.bind(view)
        requireActivity().window.setBackgroundDrawableResource(R.drawable.chat_background)
        binding.menu.setOnClickListener{
            (activity as MainActivity).navigateToFragment(MenuFragment.newInstance())
        }
        recyclerView()
        clickEvents()
        customBotMessage("Привет! Меня зовут TaU. Что хотелось бы узнать? Пиши '!' перед сообщением, если тебе нужна информация.")
    }

    private fun clickEvents() {

        //Отправка сообщения
        send.setOnClickListener {
            sendMessage()
        }

        //Возвращение назад, когда пользователь нажимает TextView
        enter_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        binding.rvMessages.layoutManager = LinearLayoutManager(context)

    }

    override fun onStart() {
        super.onStart()
        //Прокрутка сообщений вниз, после повторного открытия клавиатуры
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun sendMessage() {
        val message = enter_message.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            //Добавление в локальный лист
            messagesList.add(Message(message, SEND_ID, timeStamp))
            enter_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            rv_messages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            //Задержка ответа
            delay(1000)

            withContext(Dispatchers.Main) {
                //Получение ответа
                val response = BotResponse.basicResponses(message)

                //Добавление сообщения в локальный лист
                messagesList.add(Message(response, RECEIVE_ID, timeStamp))

                //Вставить сообщение в адаптер
                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))

                //Прокрутка позиции до последнего собщения
                rv_messages.scrollToPosition(adapter.itemCount - 1)

                //Открытие браузера
                when (response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val msg = message.lowercase()
                        val site = Intent(Intent.ACTION_VIEW)
                        val arr = msg.split(" ")
                        val key = arr[0]
                            val searchTerm: String? = msg.substringAfterLast(key)
                            site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                            startActivity(site)
                    }

                    canteen -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://i.onthe.io/smngoz7d6od1i6bkv.9e52cb90.jpg")
                        startActivity(site)
                    }
                    pool -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://i.onthe.io/smngoz4urqrtamdqt.53c93ec3.jpg")
                        startActivity(site)
                    }
                    bookkeeping -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://i.onthe.io/smngoz3fbdbu2tk73g.09604805.jpg")
                        startActivity(site)
                    }

                }
            }
        }
    }

    private fun customBotMessage(message: String) {

        GlobalScope.launch {
            delay(200)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                messagesList.add(Message(message, RECEIVE_ID, timeStamp))
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}