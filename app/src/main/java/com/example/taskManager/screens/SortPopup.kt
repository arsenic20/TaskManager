package com.example.taskManager.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskManager.viewModel.TasksScreenViewModel

@Composable
fun SortPopup(onDismiss: () -> Unit) {
    val viewModel = hiltViewModel<TasksScreenViewModel>()

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Sort by", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                val sortOptions = listOf(
                    "Title",
                    "Due Date (Farthest First)",
                    "Due Date (Closest First)",
                    "Priority (High-Low)",
                    "Priority (Low-High)"
                )
                sortOptions.forEach { sort ->
                    Text(
                        text = sort,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.setSortOrder(sort)
                                /* Handle sort selection */
                                onDismiss()
                            }
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}
