package com.example.taskManager.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskManager.model.TaskEntity
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun TaskItem(
    task: TaskEntity,
    onNavigateToDetails: (TaskEntity) -> Unit,
    index: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = { onNavigateToDetails(task) }),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = task.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 5.dp)
            )
            Text(
                text = task.description ?: "No description",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(5.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Due: ${
                        SimpleDateFormat(
                            "dd/MM/yyyy",
                            Locale.getDefault()
                        ).format(task.dueDate)
                    }",
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 5.dp, 10.dp)
                )
                Text(
                    text = "Priority: ${task.priority}",
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 5.dp, 10.dp)
                )
                Text(
                    text = "Status: ${task.status}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (task.status == "Pending") Color.Red else Color(0xFF33623D),
                    modifier = Modifier
                        .background(
                            color = if (task.status == "Pending") Color(0xFFFFCDD2) else Color(
                                0xFFB0D6B8
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                )
            }
        }
    }
}
