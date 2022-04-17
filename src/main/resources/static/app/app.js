const NotFoundComponent = { template: "<not-found></not-found>"};

const AddAdventure = { template: "<add-adventure></add-adventure>"}
const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: "/add-adventure", component: AddAdventure },

        { path: '*', component: NotFoundComponent },
    ]
});

var app = new Vue({
    router,
    el: "#wrapper",
})