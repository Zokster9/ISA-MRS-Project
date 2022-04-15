const RegistrationRequests = { template: '<registration-requests></registration-requests>'};
const RegistrationRequestsRow = {template: '<registration-requests-row> </registration-requests-row>'};

const router = new VueRouter({
    mode: 'hash',
    routes: [
        {path: "/registration-requests", component: RegistrationRequests},
        {path: "/registration-requests-row", component: RegistrationRequestsRow}

    ]
});

var app = new Vue({
    router,
    el: "#wrapper",
});