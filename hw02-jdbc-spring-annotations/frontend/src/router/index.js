import { createRouter, createWebHistory } from 'vue-router'
import WorksView from '../views/WorksView1.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', redirect: '/works' },
        { path: '/works', component: WorksView }
    ]
})

export default router
