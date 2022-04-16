const RegistrationRequests = { template: '<registration-requests></registration-requests>'};
const RegistrationRequestsRow = {template: '<registration-requests-row> </registration-requests-row>'};
const NavFishingInstructor = {template: '<nav-fishing-instructor> </nav-fishing-instructor>'};
const ProfilePageFishingInstructor = {template: '<profile-page-fishing-instructor> </profile-page-fishing-instructor>'}
const PersonalInfoFishingInstructor = {template: '<personal-info-fishing-instructor> </personal-info-fishing-instructor>'}

const router = new VueRouter({
    mode: 'hash',
    routes: [
        {path: "/registration-requests", component: RegistrationRequests},
        {path: "/registration-requests-row", component: RegistrationRequestsRow},
        {path: "/profile-page-fishing-instructor", component: ProfilePageFishingInstructor},
        {path: "/nav-fishing-instructor", component: NavFishingInstructor},
        {path: "/personal-info-fishing-instructor", component: PersonalInfoFishingInstructor},
    ]
});

var app = new Vue({
    router,
    el: "#wrapper",
});