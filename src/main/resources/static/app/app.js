const RegistrationRequests = { template: '<registration-requests></registration-requests>'};
const RegistrationRequestsRow = {template: '<registration-requests-row> </registration-requests-row>'};
const NotFoundComponent = { template: "<not-found></not-found>" };
const MainScreen = { template: "<main-screen></main-screen>" };
const SignIn = { template: "<sign-in></sign-in>" };
const SignUp = { template: "<sign-up></sign-up>" };

const router = new VueRouter({
    mode: 'hash',
    routes: [

        {path: "/registration-requests", component: RegistrationRequests},
        {path: "/registration-requests-row", component: RegistrationRequestsRow},
        { path: "/", component: MainScreen },
        { path: "/main-screen", component: MainScreen },
        { path: "/sign-in", component: SignIn },
        { path: "/sign-up", component: SignUp },

        { path: '*', component: NotFoundComponent },
    ]
});

var app = new Vue({
    router,
    el: "#wrapper",
});
