<script setup>
import {onMounted, ref} from "vue"
import axios from "axios"
import {ElMessage} from "element-plus";

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000
})

const newTodo = ref('')
const todos = ref([])

const fetchTodos = async () => {
  try {
    const response = await axiosInstance.get('/api/todos')
    todos.value = response.data
  } catch (error) {
    ElMessage.error("查询待办事项失败")
    console.error(error)
  }
}

const addTodo = async () => {
  if (newTodo.value.trim() === '') return
  try {
    const response = await axiosInstance.post('/api/todos', {
      title: newTodo.value,
      completed:false
    })
    todos.value.push(response.data)
    newTodo.value = ''
    ElMessage.success('待办事项创建成功')
  } catch (error) {
    ElMessage.error("查询待办事项失败")
    console.error(error)
  }
}

const toggleComplete = async (todo) => {
  try {
    todo.completed = !todo.completed
    await axiosInstance.put(`/api/todos/${todo.id}`, todo)
    ElMessage.success('待办事项状态更新成功')
  } catch (error) {
    ElMessage.error("查询待办事项失败")
    console.error(error)
    todo.completed = !todo.completed
  }
}

const deleteTodo = async (todo) => {
  try {
    await axiosInstance.delete(`/api/todos/${todo.id}`)
    await fetchTodos()  //  重新获取待办事项
    ElMessage.success('待办事项删除成功')
  } catch (error) {
    ElMessage.error("待办事项删除失败")
    console.error(error)
  }
}






onMounted(fetchTodos)


</script>

<template>
<div class = "todo-app">
  <el-card class="todo-card">
    <template #header>
      <div class="todo-header">
        待办事项
      </div>
    </template>

    <div class="todo-input">
      <el-input v-model="newTodo" placeholder="新建待办事项..."></el-input>
      <el-button type="primary" @click="addTodo">添加</el-button>
    </div>


    <div v-if="todos.length" class="todo-list">
      <el-card  v-for="todo in todos" :key="todo.id" class="todo-item">
        <div class="todo-item-actions">
          <div class="todo-item-title">{{todo.title}}</div>
          <el-button class="todo-button" @click="toggleComplete(todo)"
            :type="todo.completed ? 'success' : 'info'">
            {{todo.completed ? '已完成' : '未完成'}}
          </el-button>
          <el-button type="danger" @click="deleteTodo(todo)">
            删除
          </el-button>
        </div>
      </el-card>
    </div>
    <div v-else class="no-todos">暂无待办事项</div>


  </el-card>
</div>
</template>

<style scoped>
.todo-app {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
  padding: 20px;
  box-sizing: border-box;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue",
  Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
}

.todo-card {
  width: 100%;
  max-width: 500px;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
  background-color: #ffffff;
}

.todo-header {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  padding: 16px;
  background-color: #409eff;
  color: #fff;
  border-radius: 8px 8px 0 0;
  margin: 0;
}

.todo-input {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 20px;
  background-color: #fff;
  border-bottom: 1px solid #ebeef5;
}

.el-input {
  flex: 1;
}

.todo-list {
  padding: 20px;
  background-color: #fff;
}

.todo-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 15px;
  margin-bottom: 10px;
  border: 1px solid #ebeef5;
  border-radius: 8px;
  background-color: #f9f9f9;
  transition: background-color 0.3s, transform 0.3s;
}

.todo-item:hover {
  background-color: #e6f7ff;
  transform: translateY(-2px);
}

.todo-item-title {
  font-weight: bold;
  flex: 1;
  margin-right: 20px;
  word-wrap: break-word;
  width: 160px;
}

.completed .todo-item-title {
  text-decoration: line-through;
  color: #909399;
}

.todo-item-actions {
  display: flex;
  align-items: center;
}

.no-todos {
  text-align: center;
  padding: 20px;
  color: #909399;
  font-size: 18px;
}



</style>
