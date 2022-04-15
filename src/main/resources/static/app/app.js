const RegistrationRequests = { template: '<registration-requests></registration-requests>'};
const RegistrationRequestsRow = {template: '<registration-requests-row> </registration-requests-row>'};
const NavFishingInstructor = {template: '<nav-fishing-instructor> </nav-fishing-instructor>'};
const ProfilePageFishingInstructor = {template: '<profile-page-fishing-instructor> </profile-page-fishing-instructor>'}

const router = new VueRouter({
    mode: 'hash',
    routes: [
        {path: "/registration-requests", component: RegistrationRequests},
        {path: "/registration-requests-row", component: RegistrationRequestsRow},
        {path: "/profile-page-fishing-instructor", component: ProfilePageFishingInstructor},
        {path: "/nav-fishing-instructor", component: NavFishingInstructor},
    ]
});

var app = new Vue({
    router,
    el: "#wrapper",
});