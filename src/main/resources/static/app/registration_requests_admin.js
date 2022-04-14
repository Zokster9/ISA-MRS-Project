Vue.use(vuelidate.default)

Vue.component("registration-requests", {
    template:`
    
    `,
    data(){
        return {
            unactivatedUsers: [],
        }
    },
    mounted(){
        axios.get("/users").then((response) => {
            this.unactivatedUsers = response.data;
            console.log(this.unactivatedUsers);
        })
    }
})