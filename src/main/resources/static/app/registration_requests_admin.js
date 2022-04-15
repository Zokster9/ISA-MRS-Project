Vue.use(vuelidate.default)

Vue.component("registration-requests", {
    template:`
        <div class="row justify-content-center">
         <div class="col-auto">
         <br>
         <br>
            <table class="table table-striped table-responsive table-bordered mb-5"">
                <thead>
                    <th colspan="100%" class="text-center h2"> Registration Requests </th>
                </thead>
                <tbody>
                    <registration-requests-row v-for="user in unactivatedUsers" :user="user"></registration-requests-row>
                </tbody>
            </table>
         </div>
        </div>
    `,
    data(){
        return {
            unactivatedUsers: [],
        }
    },
    mounted(){
        axios.get("/users/inactive").then((response) => {
            this.unactivatedUsers = response.data;
        })
    },
})