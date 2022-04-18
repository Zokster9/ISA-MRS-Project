const RegistrationRequests = { template: '<registration-requests></registration-requests>'};
const RegistrationRequestsRow = {template: '<registration-requests-row> </registration-requests-row>'};
const NotFoundComponent = { template: "<not-found></not-found>" };
const MainScreen = { template: "<main-screen></main-screen>" };
const SignIn = { template: "<sign-in></sign-in>" };
const SignUp = { template: "<sign-up></sign-up>" };
const Retreats = { template: "<retreats></retreats>" };
const Ships = { template: "<ships></ships>" };
const FishingInstructors = { template: "<fishing-instructors></fishing-instructors>" };
const AddRetreat = { template: '<add-retreat></add-retreat>'};
const AddShip = { template: '<add-ship></add-ship>'};
const AddAdventure = { template: "<add-adventure></add-adventure>"}
const NavFishingInstructor = {template: '<nav-fishing-instructor> </nav-fishing-instructor>'};
const ProfilePageFishingInstructor = {template: '<profile-page-fishing-instructor> </profile-page-fishing-instructor>'}
const ProfilePageClient = { template: '<profile-page-client></profile-page-client>' }
const RetreatPage = {template: '<retreat-page></retreat-page>'};

const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: "/registration-requests", component: RegistrationRequests },
        { path: "/registration-requests-row", component: RegistrationRequestsRow },
        { path: "/", component: MainScreen },
        { path: "/main-screen", component: MainScreen },
        { path: "/sign-in", component: SignIn },
        { path: "/sign-up", component: SignUp },
        { path: "/retreats", component: Retreats },
        { path: "/ships", component: Ships },
        { path: "/fishing-instructors", component: FishingInstructors },
        { path: '*', component: NotFoundComponent },
        { path: "/add-retreat", component: AddRetreat },
        { path: "/add-ship", component: AddShip },
        { path: "/add-adventure", component: AddAdventure },
        {path: "/registration-requests", component: RegistrationRequests},
        {path: "/registration-requests-row", component: RegistrationRequestsRow},
        {path: "/profile-page-fishing-instructor", component: ProfilePageFishingInstructor},
        { path: "/profile-page-client", component: ProfilePageClient },
        {path: "/nav-fishing-instructor", component: NavFishingInstructor},
        {path: "/retreat/:id", component: RetreatPage}
    ]
});

var app = new Vue({
    router,
    el: "#wrapper",
});
