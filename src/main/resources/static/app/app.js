const RegistrationRequests = { template: '<registration-requests></registration-requests>'};
const RegistrationRequestsRow = {template: '<registration-requests-row> </registration-requests-row>'};
const RetreatPage = {template: '<retreat-page></retreat-page>'};

const router = new VueRouter({
    mode: 'hash',
    routes: [
        {path: "/registration-requests", component: RegistrationRequests},
        {path: "/registration-requests-row", component: RegistrationRequestsRow},
        {path: "/retreat/:id", component: RetreatPage}
    ]
});

var app = new Vue({
    router,
    el: "#wrapper",
});