import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import CertDetail from '../views/CertDetail.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/cert/:id',
    name: 'certDetail',
    component: CertDetail
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
