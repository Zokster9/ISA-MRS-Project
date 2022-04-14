const RegistrationRequests = { template: '<registration-requests></registration-requests>'};

const router = new VueRouter({
    mode: 'hash',
    routes: [
        {path: "/registration-requests", component: RegistrationRequests},

    ]
});

var app = new Vue({
    router,
    el: "#wrapper",
});