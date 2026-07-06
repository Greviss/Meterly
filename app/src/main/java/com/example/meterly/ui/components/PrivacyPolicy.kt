package com.example.meterly.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrivacyPolicyScreen(
    onBackClick: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 4.dp)
                        .clickable { onBackClick() }
                )

                Text(
                    text = "◄ПОЛІТИКА КОНФЕДЕНЦІЙНОСТІ►",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text =  "Дата набрання чинності: 06.07.2026\n\n" +

                        "1. Загальні положення\n\n" +

                        "Додаток Meterly поважає право кожного користувача на конфіденційність та захист персональних даних. Ця Політика конфіденційності пояснює, які дані збираються, як вони використовуються, зберігаються та захищаються під час користування додатком.\n\n" +

                        "Користуючись Meterly, ви погоджуєтесь із цією Політикою конфіденційності.\n\n" +

                        "2. Які дані ми збираємо\n\n" +

                        "Для роботи додатка можуть збиратися та зберігатися такі дані:\n\n" +

                        "• ПІБ користувача;\n" +
                        "• номер телефону;\n" +
                        "• адреси нерухомості, додані користувачем;\n" +
                        "• поточна вибрана адреса;\n" +
                        "• технічна інформація, необхідна для роботи сервісу.\n\n" +

                        "3. Для чого використовуються дані\n\n" +

                        "Зібрана інформація використовується виключно для:\n\n" +

                        "• авторизації користувача;\n" +
                        "• відображення персонального профілю;\n" +
                        "• роботи з адресами нерухомості;\n" +
                        "• забезпечення стабільної роботи додатка;\n" +
                        "• покращення функціоналу та усунення помилок.\n\n" +

                        "4. Зберігання інформації\n\n" +

                        "Дані користувачів зберігаються у Firebase Firestore та Firebase Authentication. Google забезпечує сучасні механізми захисту інформації відповідно до власних стандартів безпеки.\n\n" +

                        "5. Передача даних третім особам\n\n" +

                        "Meterly не продає та не передає персональні дані третім особам, за винятком випадків, передбачених законодавством або необхідних для роботи сервісів Google Firebase.\n\n" +

                        "6. Захист інформації\n\n" +

                        "Ми застосовуємо сучасні методи захисту даних, однак жоден спосіб передачі інформації через Інтернет не може гарантувати абсолютну безпеку.\n\n" +

                        "7. Права користувача\n\n" +

                        "Користувач має право:\n\n" +

                        "• переглядати свої персональні дані;\n" +
                        "• змінювати інформацію;\n" +
                        "• видаляти додані адреси;\n" +
                        "• припинити використання додатка у будь-який момент.\n\n" +

                        "8. Cookies та аналітика\n\n" +

                        "Додаток може використовувати технічні засоби Firebase для забезпечення роботи сервісу та аналізу його стабільності. Ці інструменти не використовуються для продажу або передачі ваших персональних даних.\n\n" +

                        "9. Зміни до Політики\n\n" +

                        "Політика конфіденційності може змінюватися у разі оновлення функціоналу або вимог законодавства. Актуальна версія завжди доступна в додатку.\n\n" +

                        "10. Контактна інформація\n\n" +

                        "Якщо у вас виникли питання щодо цієї Політики конфіденційності або обробки персональних даних, зв'яжіться з розробником через контактні дані, зазначені в описі додатка.",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        }
    }
}