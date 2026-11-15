package com.jorlui.ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Bg = Color(0xFF070B14)
private val Panel = Color(0xFF111827)
private val Cyan = Color(0xFF00E5FF)
private val TextMain = Color(0xFFEAF4FF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { JorluiApp() }
    }
}

data class ChatMessage(val text: String, val fromUser: Boolean)

@Composable
fun JorluiApp() {
    var input by remember { mutableStateOf("") }
    var messages by remember {
        mutableStateOf(listOf(ChatMessage("Hola. Soy Jorlui AI. La interfaz está lista; conecta tu servidor para activar respuestas inteligentes.", false)))
    }
    MaterialTheme {
        Column(Modifier.fillMaxSize().background(Bg).padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("JORLUI", color = Cyan, fontSize = 25.sp, fontWeight = FontWeight.Black)
                Spacer(Modifier.width(8.dp))
                Text("AI", color = TextMain, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.weight(1f))
                Surface(color = Color(0xFF09242B), shape = RoundedCornerShape(50.dp)) {
                    Text("● ONLINE UI", color = Cyan, modifier = Modifier.padding(horizontal=10.dp, vertical=6.dp), fontSize=11.sp)
                }
            }
            Text("Asistente personal", color = Color(0xFF91A4B8), fontSize = 13.sp)
            Spacer(Modifier.height(18.dp))
            LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(messages) { msg ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = if (msg.fromUser) Arrangement.End else Arrangement.Start) {
                        Surface(color = if (msg.fromUser) Color(0xFF073344) else Panel,
                            shape = RoundedCornerShape(16.dp), modifier = Modifier.widthIn(max=300.dp)) {
                            Text(msg.text, color = TextMain, modifier = Modifier.padding(14.dp), fontSize = 15.sp)
                        }
                    }
                }
            }
            Row(Modifier.fillMaxWidth().background(Panel, RoundedCornerShape(18.dp)).padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                TextField(value=input, onValueChange={input=it}, placeholder={Text("Escribe un mensaje...", color=Color.Gray)},
                    modifier=Modifier.weight(1f), colors=TextFieldDefaults.colors(
                        focusedContainerColor=Panel, unfocusedContainerColor=Panel,
                        focusedTextColor=TextMain, unfocusedTextColor=TextMain,
                        focusedIndicatorColor=Color.Transparent, unfocusedIndicatorColor=Color.Transparent
                    ), maxLines=4)
                Spacer(Modifier.width(6.dp))
                Button(onClick={
                    val text=input.trim()
                    if(text.isNotEmpty()) {
                        messages = messages + ChatMessage(text,true) + ChatMessage("Todavía no estoy conectado al servidor IA. El siguiente paso es configurar la API de forma segura.",false)
                        input=""
                    }
                }, colors=ButtonDefaults.buttonColors(containerColor=Cyan, contentColor=Bg), shape=RoundedCornerShape(12.dp)) {
                    Text("➤", fontSize=18.sp)
                }
            }
            Spacer(Modifier.height(8.dp))
            Text("Jorlui AI • versión inicial", color=Color(0xFF536579), fontSize=11.sp, modifier=Modifier.align(Alignment.CenterHorizontally))
        }
    }
}
