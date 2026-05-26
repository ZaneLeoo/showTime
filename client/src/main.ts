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
  auth.fetchUser().catch(() => {
    // fetchUser 失败不删 token —— 可能是网络问题，下次导航时再试
  })
}

app.mount('#app')
