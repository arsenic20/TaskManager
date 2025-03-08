package com.example.taskManager.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskManager.model.TaskEntity
import com.example.taskManager.networks.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksScreenViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    // Directly expose the Flow from Room so UI updates automatically
    private val _tasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val tasks: StateFlow<List<TaskEntity>> = _tasks.asStateFlow()

    private val _allTasks = MutableStateFlow<List<TaskEntity>>(emptyList())

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _filter = MutableStateFlow("All")
    private val _sortOrder = MutableStateFlow("Due Date (Farthest First)")

    fun setFilter(filter: String) {
        _filter.value = filter
        _tasks.value = applyFilter()
    }

    fun setSortOrder(sortOrder: String) {
        _sortOrder.value = sortOrder
        _tasks.value = applySort()
    }

    init {
        refreshTasks()
    }

    fun refreshTasks() {
        viewModelScope.launch {
            repository.getAllTasks().collectLatest { fetchedTasks ->
                _tasks.value = fetchedTasks
                _allTasks.value = fetchedTasks // Ensure UI updates when new task is added
            }
        }
    }

    private fun applyFilter(tasksList: List<TaskEntity> = _allTasks.value): List<TaskEntity> {
        val filteredTasks = when (_filter.value) {
            "Pending" -> tasksList.filter { it.status == "Pending" }
            "Completed" -> tasksList.filter { it.status == "Completed" }
            else -> tasksList
        }
        return filteredTasks
    }

    private fun applySort(tasksList: List<TaskEntity> = _allTasks.value): List<TaskEntity> {
        val priorityOrder = mapOf("High" to 3, "Medium" to 2, "Low" to 1)
        val sortedData = when (_sortOrder.value) {
            "Due Date (Farthest First)" -> tasksList.sortedByDescending { it.dueDate }
            "Due Date (Closest First)" -> tasksList.sortedBy { it.dueDate }
            "Priority (High-Low)" -> tasksList.sortedByDescending {
                priorityOrder[it.priority] ?: 0
            }

            "Priority (Low-High)" -> tasksList.sortedBy { priorityOrder[it.priority] ?: 0 }
            "Title" -> tasksList.sortedBy { it.title }
            else -> tasksList
        }
        return sortedData
    }

}
