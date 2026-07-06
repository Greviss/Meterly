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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TermsOfUse(
    onBack: () -> Unit
) {
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
                        .clickable { onBack() }
                )

                Text(
                    text = "◄УМОВИ ВИКОРИСТАННЯ►",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text =      "Дата набрання чинності: 06.07.2026\n\n" +

                            "1. Загальні положення\n\n" +

                            "Ці Умови використання регулюють порядок користування мобільним додатком Meterly. Використовуючи додаток, користувач підтверджує, що ознайомився з цими умовами та погоджується їх дотримуватися.\n\n" +

                            "Якщо користувач не погоджується з будь-яким положенням цих Умов, він повинен припинити використання додатка.\n\n" +

                            "2. Призначення додатка\n\n" +

                            "Meterly призначений для зберігання та керування інформацією про об'єкти нерухомості, адреси та пов'язані дані користувача.\n\n" +

                            "Функціонал додатка може змінюватися, доповнюватися або оновлюватися без попереднього повідомлення.\n\n" +

                            "3. Реєстрація та обліковий запис\n\n" +

                            "Для використання окремих функцій користувач повинен пройти авторизацію за допомогою номера телефону.\n\n" +

                            "Користувач несе відповідальність за достовірність наданої інформації та безпеку свого облікового запису.\n\n" +

                            "4. Обов'язки користувача\n\n" +

                            "Користувач зобов'язується:\n\n" +

                            "• використовувати додаток відповідно до законодавства України;\n" +
                            "• не здійснювати спроб несанкціонованого доступу до системи;\n" +
                            "• не використовувати додаток для протиправної діяльності;\n" +
                            "• не поширювати шкідливе програмне забезпечення через сервіс;\n" +
                            "• не порушувати права інших користувачів.\n\n" +

                            "5. Інтелектуальна власність\n\n" +

                            "Усі права на дизайн, програмний код, логотипи, текстові матеріали та інші елементи Meterly належать розробнику або використовуються на законних підставах.\n\n" +

                            "Копіювання, модифікація, декомпіляція або поширення додатка без дозволу правовласника забороняються.\n\n" +

                            "6. Обмеження відповідальності\n\n" +

                            "Додаток надається за принципом «як є». Розробник не гарантує безперебійну роботу сервісу та не несе відповідальності за можливі технічні збої, втрату даних або інші наслідки використання додатка.\n\n" +

                            "Користувач самостійно несе відповідальність за збереження важливої інформації та створення резервних копій за необхідності.\n\n" +

                            "7. Тимчасове припинення доступу\n\n" +

                            "Розробник має право тимчасово обмежити доступ до сервісу для проведення технічних робіт, оновлень або усунення несправностей.\n\n" +

                            "8. Припинення використання\n\n" +

                            "Користувач може припинити використання додатка в будь-який час.\n\n" +

                            "Розробник має право обмежити доступ до сервісу у випадку порушення цих Умов використання.\n\n" +

                            "9. Зміни до Умов використання\n\n" +

                            "Розробник залишає за собою право змінювати ці Умови використання в будь-який час. Оновлена версія набирає чинності з моменту її публікації в додатку.\n\n" +

                            "10. Контактна інформація\n\n" +

                            "З усіх питань, пов'язаних з роботою додатка, користувач може звернутися до розробника через контактні дані, зазначені в описі додатка.",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        }
    }
}