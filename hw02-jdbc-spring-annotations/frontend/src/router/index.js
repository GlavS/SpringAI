import { createRouter, createWebHistory } from 'vue-router'
import WorksView from '../views/WorksView1.vue'
import Test from "@/scratches/Test.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', redirect: '/works' },
        { path: '/works', component: WorksView },
        { path: '/test', component: Test}
    ]
})

export default router
