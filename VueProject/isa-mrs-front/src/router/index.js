import Vue from 'vue'
import VueRouter from 'vue-router'
import MainView from '@/views/MainView'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'main-screen',
    component: MainView
  },
  {
    path: '/main-screen',
    name: 'main-screen',
    component: MainView
  },
  {
    path: '/sign-in',
    name: 'sign-in',
    component: () => import('../views/SignInView.vue')
  },
  {
    path: '/sign-up',
    name: 'sign-up',
    component: () => import('../views/SignUpView.vue')
  },
  {
    path: '/ships',
    name: 'ships',
    component: () => import('../views/ShipsView.vue')
  },
  {
    path: '/retreat/:id',
    name: 'retreat',
    component: () => import('../views/RetreatView.vue')
  },
  {
    path: '/registration-requests',
    name: 'registration-requests',
    component: () => import('../views/RegistrationsRequestsAdminView.vue')
  },
  {
    path: '/profile-page-client',
    name: 'profile-page-client',
    component: () => import('../views/ProfilePageClientView.vue')
  },
  {
    path: '/profile-page-fishing-instructor',
    name: 'profile-page-fishing-instructor',
    component: () => import('../views/ProfilePageFishingInstructorView.vue')
  },
  {
    path: '/profile-page-admin',
    name: 'profile-page-admin',
    component: () => import('../views/ProfilePageAdminView.vue')
  },
  {
    path: '/account-termination-requests',
    name: 'account-termination-requests',
    component: () => import('../views/AccountTerminationRequestsView.vue')
  },
  {
    path: '/change-info',
    name: 'change-info',
    component: () => import('../views/ChangeInfoView.vue')
  },
  {
    path: '/add-ship',
    name: 'add-ship',
    component: () => import('../views/AddShipView.vue')
  },
  {
    path: '/add-retreat',
    name: 'add-retreat',
    component: () => import('../views/AddRetreatView.vue')
  },
  {
    path: '/add-adventure',
    name: 'add-adventure',
    component: () => import('../views/AddAdventureView.vue')
  },
  {
    path: '/fishing-instructors',
    name: 'fishing-instructors',
    component: () => import('../views/FishingInstructorsView.vue')
  },
  {
    path: '/retreats',
    name: 'retreats',
    component: () => import('../views/RetreatsView.vue')
  },
  {
    path: '/service-availability',
    name: '/service-availability',
    component: () => import('../views/ServiceAvailabilityView.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
