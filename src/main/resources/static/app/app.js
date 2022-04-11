const NotFoundComponent = { template: "<not-found></not-found>" };

const MainScreen = { template: "<main-screen></main-screen>" };
const SignIn = { template: "<sign-in></sign-in>" };
const SignUp = { template: "<sign-up></sign-up>" };

const router = new VueRouter({
    mode: 'hash',
    routes: [
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
})