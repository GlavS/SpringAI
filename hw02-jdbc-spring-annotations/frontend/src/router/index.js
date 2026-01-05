import { createRouter, createWebHistory } from 'vue-router'
import WorksView from '@/views/WorksView.vue'
import Test from "@/scratches/Test.vue";
import MiniWorks from "@/scratches/TestWorksView.vue"
import WorkDetailsView from "@/views/WorkDetailsView.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', redirect: '/works' },
        { path: '/works', component: WorksView },
        { path: '/test', component: Test},
        { path: '/mini', component: MiniWorks},
        { path: '/works/:id', component: WorkDetailsView}
    ]
})

export default router
