const NotFoundComponent = { template: "<not-found></not-found>" };

const MainScreen = { template: "<main-screen></main-screen>" };
const SignIn = { template: "<sign-in></sign-in>" };
const SignUp = { template: "<sign-up></sign-up>" };
const Retreats = { template: "<retreats></retreats>" };
const Ships = { template: "<ships></ships>" };
const FishingInstructors = { template: "<fishing-instructors></fishing-instructors>" };

const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: "/", component: MainScreen },
        { path: "/main-screen", component: MainScreen },
        { path: "/sign-in", component: SignIn },
        { path: "/sign-up", component: SignUp },
        { path: "/retreats", component: Retreats },
        { path: "/ships", component: Ships },
        { path: "/fishing-instructors", component: FishingInstructors },

        { path: '*', component: NotFoundComponent },
    ]
});

var app = new Vue({
    router,
    el: "#wrapper",
})