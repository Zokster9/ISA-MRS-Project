
const AddRetreat = { template: '<add-retreat></add-retreat>'};

const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: "/", component: AddRetreat }

    ]
});

var app = new Vue({
    router,
    el: "#wrapper",
});