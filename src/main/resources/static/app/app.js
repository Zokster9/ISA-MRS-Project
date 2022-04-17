const AddShip = { template: '<add-ship></add-ship>'};

const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: "/add-ship", component: AddShip }

    ]
});

var app = new Vue({
    router,
    el: "#wrapper",
});