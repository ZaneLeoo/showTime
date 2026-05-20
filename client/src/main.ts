import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import { useAuthStore } from './stores/auth'
import './styles/main.css'

const app = createApp(App)
app.use(createPinia())
app.use(router)

// 刷新后恢复用户信息
const auth = useAuthStore()
if (auth.isLoggedIn) {
  auth.fetchUser()
}

app.mount('#app')
