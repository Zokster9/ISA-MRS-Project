import Vue from 'vue'
import VueRouter from 'vue-router'
import MainView from '@/views/MainView'

Vue.use(VueRouter)

const routes = [{
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
        component: () =>
            import ('../views/SignInView.vue')
    },
    {
        path: '/sign-up',
        name: 'sign-up',
        component: () =>
            import ('../views/SignUpView.vue')
    },
    {
        path: '/ships',
        name: 'ships',
        component: () =>
            import ('../views/ShipsView.vue')
    },
    {
        path: '/retreat/:id',
        name: 'retreat',
        component: () =>
            import ('../views/RetreatView.vue')
    },
    {
        path: '/registration-requests',
        name: 'registration-requests',
        component: () =>
            import ('../views/RegistrationsRequestsAdminView.vue')
    },
    {
        path: '/profile-page-client',
        name: 'profile-page-client',
        component: () =>
            import ('../views/ProfilePageClientView.vue')
    },
    {
        path: '/profile-page-fishing-instructor',
        name: 'profile-page-fishing-instructor',
        component: () =>
            import ('../views/ProfilePageFishingInstructorView.vue')
    },
    {
        path: '/profile-page-admin',
        name: 'profile-page-admin',
        component: () =>
            import ('../views/ProfilePageAdminView.vue')
    },
    {
        path: '/account-termination-requests',
        name: 'account-termination-requests',
        component: () =>
            import ('../views/AccountTerminationRequestsView.vue')
    },
    {
        path: '/change-info',
        name: 'change-info',
        component: () =>
            import ('../views/ChangeInfoView.vue')
    },
    {
        path: '/add-ship',
        name: 'add-ship',
        component: () =>
            import ('../views/AddShipView.vue')
    },
    {
        path: '/add-retreat',
        name: 'add-retreat',
        component: () =>
            import ('../views/AddRetreatView.vue')
    },
    {
        path: '/add-adventure',
        name: 'add-adventure',
        component: () =>
            import ('../views/AddAdventureView.vue')
    },
    {
        path: '/fishing-instructors',
        name: 'fishing-instructors',
        component: () =>
            import ('../views/FishingInstructorsView.vue')
    },
    {
        path: '/retreats',
        name: 'retreats',
        component: () =>
            import ('../views/RetreatsView.vue')
    },
    {
        path: '/ship/:id',
        name: 'ship',
        component: () =>
            import ('../views/ShipView.vue')
    },
    {
        path: '/adventure/:id',
        name: 'adventure',
        component: () =>
            import ('../views/AdventurePageView.vue')
    },
    {
        path: '/service-availability',
        name: 'service-availability',
        component: () =>
            import ('../views/ServiceAvailabilityView.vue')
    },
    {
        path: '/service-crud',
        name: 'service-crud',
        component: () =>
            import ('../views/ServiceCrudView.vue')
    },
    {
        path: '/activate-account/:id',
        name: 'activate-account',
        component: () =>
            import ('../views/ActivateAccountView.vue')
    },
    {
        path: '/profile-page-retreat-owner',
        name: 'profile-page-retreat-owner',
        component: () =>
            import ('../views/ProfilePageRetreatOwnerView.vue')
    },
    {
        path: '/profile-page-ship-owner',
        name: 'profile-page-ship-owner',
        component: () =>
            import ('../views/ProfilePageShipOwnerView.vue')
    },
    {
        path: '/retreat-info/:id',
        name: 'retreat-info',
        component: () =>
            import ('../views/RetreatInfoChangeView.vue')
    },
    {
        path: '/ship-info/:id',
        name: 'ship-info',
        component: () =>
            import ('../views/ShipInfoChangeView.vue')
    },
    {
        path: '/admin-sign-up',
        name: 'admin-sign-up',
        component: () =>
            import ('../views/AdminSignUpView.vue')
    },
    {
        path: '/update-adventure/:id',
        name: 'update-adventure',
        component: () =>
            import ('../views/UpdateAdventureView.vue')
    },
    {
        path: '/home-page-client',
        name: 'home-page-client',
        component: () =>
            import ('../views/HomepageClientView.vue')
    },
    {
        path: '/admin-services-view',
        name: 'admin-services-view',
        component: () =>
            import ('../views/AdminServicesView.vue')
    },
    {
        path: '/admin-users-view',
        name: 'admin-users-view',
        component: () =>
            import ('../views/AdminUsersView.vue')
    },
    {
        path: '/add-action/:id',
        name: 'add-action',
        component: () =>
            import ('../views/AddActionView.vue')
    },
    {
        path: '/reservation-history-privileged-user',
        name: 'reservation-history-privileged-user',
        component: () =>
            import ('../views/ReservationHistoryPrivilegedUserView.vue')
    },
    {
        path: '/service-calendar/:id',
        name: 'service-calendar',
        component: () =>
            import ('../views/CalendarView.vue')
    },
    {
        path: '/penalty-points',
        name: 'penalty-points',
        component: () =>
            import('../views/PenaltyPointsView.vue')
    },
    {
        path: '/client-subscriptions',
        name: 'client-subscriptions',
        component: () =>
            import('../views/ClientSubscriptionsView.vue')
    },
    {
        path: '/make-a-reservation',
        name: 'make-a-reservation',
        component: () =>
            import('../views/ClientReservationView.vue')
    },
    {
        path: '/write-report/:id',
        name: 'write-report',
        component: () =>
            import('../views/WriteReportView.vue')
    },
    {
        path: '/admin-reports',
        name: 'admin-reports',
        component: () =>
            import('../views/AdminReportsView.vue')
    },
    {
        path: '/owner-reserve/:id',
        name: 'owner-reserve',
        component: () =>
            import ('../views/OwnerReservationView.vue')
    },
    {
        path: '/admin-complaints',
        name: 'admin-complaints',
        component: () =>
            import('../views/AdminComplaintsView.vue')
    },
    {
        path: '/write-a-complaint',
        name: 'write-a-complaint',
        component: () =>
            import('../views/ClientComplaintsView.vue')
    },
    {
        path: '/client-ship-reservation-history',
        name: 'client-ship-reservation-history',
        component: () =>
            import('../views/ClientShipReservationHistoryView.vue')
    },
    {
        path: '/client-retreat-reservation-history',
        name: 'client-retreat-reservation-history',
        component: () =>
            import('../views/ClientRetreatReservationHistoryView.vue')
    },
    {
        path: '/client-adventure-reservation-history',
        name: 'client-adventure-reservation-history',
        component: () =>
            import('../views/ClientAdventureReservationHistoryView.vue')
    },
    {
        path: '/review-reservation/:id',
        name: 'review-reservation',
        component: () =>
            import('../views/ClientRevisionView.vue')
    },
    {
        path: '/pending-reservations',
        name: 'pending-reservations',
        component: () =>
            import('../views/ClientPendingReservationsView.vue')
    },
    {
        path: '/admin-revisions',
        name: 'admin-revisions',
        component: () =>
            import('../views/AdminRevisionsView.vue')
    },
    {
        path: '/admin-business-income',
        name: 'admin-business-income',
        component: () =>
            import('../views/AdminBusinessIncomeView.vue')
    },
    {
        path: '/admin-loyalty-program',
        name: 'admin-loyalty-program',
        component: () =>
            import('../views/AdminLoyaltyProgramView.vue')
    },
    {
        path: '/reports',
        name: 'reports',
        component: () => 
            import('../views/ReportView.vue')
    },
    {
        path: '/fast-reservations/:id',
        name: 'fast-reservations',
        component: () =>
            import('../views/ClientFastReservationView.vue')
    },
    {
        path: '/adventures/:id',
        name: 'adventures',
        component: () =>
            import('../views/AdventuresView.vue')
    },
]

const router = new VueRouter({
    routes
})

export default router