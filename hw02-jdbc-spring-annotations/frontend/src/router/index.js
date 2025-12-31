import { createRouter, createWebHistory } from 'vue-router'
import WorksView from '../views/WorksView.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', redirect: '/works' },
        { path: '/works', component: WorksView }
    ]
})

export default router
